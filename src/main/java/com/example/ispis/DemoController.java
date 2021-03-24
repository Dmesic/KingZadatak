package com.example.ispis;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.Response;
import com.amadeus.exceptions.ResponseException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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
    public String pretragaSubmit(@ModelAttribute PretragaLetova pretragaLetova, Model model) throws ResponseException {

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

            Amadeus amadeus = Amadeus
                    .builder("WYoPou1e4ItOYbA7bff4nxlbSAbdk1j4", "o08RnsblBHlOr7g8")
                    .build();

            Response response = amadeus.get("/v2/shopping/flight-offers",
                    Params.with("originLocationCode", sifraPolaznogAerodroma)
                            .and("destinationLocationCode", sifraOdredisnjegAerodroma)
                            .and("departureDate", datumPolaska)
                            .and("returnDate", datumDolaska)
                            .and("adults", brojPutnika)
                            .and("currencyCode", sifraValute)
                            .and("max", max));

            Type flightListType = new TypeToken<ArrayList<Data>>(){}.getType();
            ArrayList<Data> dataArrayList = gson.fromJson(response.getData(), flightListType);

            PretragaLetova temp2 = new PretragaLetova(pretragaLetova.getBrojPutnika(),pretragaLetova.getSifraPolaznogAerodroma(),
                    pretragaLetova.getSifraOdredisnjegAerodroma(), pretragaLetova.getDatumPolaska(), pretragaLetova.getDatumDolaska(),
                    pretragaLetova.getSifraValute(), generatedString);

            pretragaLetovaRepository.save(temp2);

            for (Data data : dataArrayList){
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
