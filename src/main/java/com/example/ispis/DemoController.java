package com.example.ispis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private PonudaLetovaRepository ponudaLetovaRepository;

    @Autowired
    private PretragaLetovaRepository pretragaLetovaRepository;

    @GetMapping("/pretraga")
    public String pretragaForm(Model model){
        model.addAttribute("pretraga", new PretragaLetova());
        return "pretraga";
    }

    @PostMapping("/pretraga")
    public String pretragaSubmit(@ModelAttribute PretragaLetova pretragaLetova, Model model) throws UnirestException {

        model.addAttribute("pretraga", pretragaLetova);

        String tempString ="";

        Gson gson = new Gson();

        int brojPutnika = pretragaLetova.getBrojPutnika();
        String sifraPolaznogAerodroma = pretragaLetova.getSifraPolaznogAerodroma();
        String sifraOdredisnjegAerodroma = pretragaLetova.getSifraOdredisnjegAerodroma();
        String datumPolaska = pretragaLetova.getDatumPolaska();
        String datumDolaska = pretragaLetova.getDatumDolaska();
        String sifraValute = pretragaLetova.getSifraValute();
        int max = 3;

        ArrayList<LetoviPonuda> listaPonuda = new ArrayList<>();

        boolean check = pretragaLetovaRepository.
                existsByBrojPutnikaAndSifraPolaznogAerodromaAndSifraOdredisnjegAerodromaAndDatumPolaskaAndDatumDolaskaAndSifraValute
                        (brojPutnika,sifraPolaznogAerodroma,sifraOdredisnjegAerodroma,datumPolaska,datumDolaska,sifraValute);

        if (check==true){
            tempString="postoji";

            PretragaLetova tempP = pretragaLetovaRepository
                    .findByBrojPutnikaAndSifraPolaznogAerodromaAndSifraOdredisnjegAerodromaAndDatumPolaskaAndDatumDolaskaAndSifraValute
                            (brojPutnika,sifraPolaznogAerodroma,sifraOdredisnjegAerodroma,datumPolaska,datumDolaska,sifraValute);

            List<LetoviPonuda> ponudaList = ponudaLetovaRepository.findByRandom(tempP.getRandom());
            model.addAttribute("listaLetova",ponudaList);

        }
        else {

            tempString= "ne postoji";

            String generatedString = RandomStringUtils.randomAlphabetic(6);
            model.addAttribute("generatedString", generatedString);

            // ====================== POCETAK NOVOG =========================

            HttpResponse<JsonNode> httpResponse = Unirest.post("https://test.api.amadeus.com/v1/security/oauth2/token")
                    .header("content-type", "application/x-www-form-urlencoded")
                    .body("grant_type=client_credentials&client_id=sPYacOMUn6UyK6yKO6xbUgqTtuPSZ9Kq&client_secret=04Z6LoOdaZ7vweYV")
                    .asJson();

            String access_token = (String) httpResponse.getBody().getObject().get("access_token");

            HttpResponse<JsonNode> response =
                    Unirest.get("https://test.api.amadeus.com/v2/shopping/flight-offers?" +
                            "originLocationCode="+ sifraPolaznogAerodroma +
                            "&destinationLocationCode="+ sifraOdredisnjegAerodroma +
                            "&departureDate="+ datumPolaska +
                            "&returnDate="+ datumDolaska +
                            "&adults="+ brojPutnika +
                            "&max="+ max +
                            "&currencyCode=" + sifraValute)
                            .header("content-type", "application/json")
                            .header("authorization", "Bearer " + access_token)
                            .asJson();

            JSONObject jsonObjectData = new JSONObject(response.getBody());

            JSONArray jsonArrayData = jsonObjectData.getJSONArray("array");

            Type flightListType = new TypeToken<ArrayList<ArrayDTO>>() {}.getType();
            ArrayList<ArrayDTO> arrayDTOArrayList = gson.fromJson(String.valueOf(jsonArrayData), flightListType);

            // ====================== KRAJ NOVOG =========================

            PretragaLetova temp2 = new PretragaLetova(pretragaLetova.getBrojPutnika(),pretragaLetova.getSifraPolaznogAerodroma(),
                    pretragaLetova.getSifraOdredisnjegAerodroma(), pretragaLetova.getDatumPolaska(), pretragaLetova.getDatumDolaska(),
                    pretragaLetova.getSifraValute(), generatedString);

            pretragaLetovaRepository.save(temp2);

                for (Data data : arrayDTOArrayList.get(0).getData()){
                    for (Itinerary itinerary: data.getItineraries()){
                        for (Segment segment: itinerary.getSegments()){
                            LetoviPonuda temp = new LetoviPonuda(segment.getDeparture().getIataCode(),segment.getArrival().getIataCode(),
                                    segment.getDeparture().getAt(), segment.getArrival().getAt(),
                                    segment.getNumberOfStops(),brojPutnika,
                                    data.getPrice().getGrandTotal()+" "+data.getPrice().getCurrency(),generatedString);
                            listaPonuda.add(temp);
                            ponudaLetovaRepository.save(temp);
                        }
                    }
                }

            model.addAttribute("listaLetova",listaPonuda);
        }

        model.addAttribute("provjera",tempString);

        return "pretraga";
    }
}