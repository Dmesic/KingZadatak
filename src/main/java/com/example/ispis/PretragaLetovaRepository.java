package com.example.ispis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PretragaLetovaRepository extends JpaRepository<PretragaLetova, Long> {

    boolean  existsByBrojPutnikaAndSifraPolaznogAerodromaAndSifraOdredisnjegAerodromaAndDatumPolaskaAndDatumDolaskaAndSifraValute
            (int brojPutnika, String sifraPolaznog, String sifraOdlaznog, String datumPolaska, String datumDolaska, String sifraValute);

    PretragaLetova findByBrojPutnikaAndSifraPolaznogAerodromaAndSifraOdredisnjegAerodromaAndDatumPolaskaAndDatumDolaskaAndSifraValute
            (int brojPutnika, String sifraPolaznog, String sifraOdlaznog, String datumPolaska, String datumDolaska, String sifraValute);

    PretragaLetova findByRandom(String random);
}
