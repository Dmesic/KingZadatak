package com.example.ispis;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "letovi_pretraga")
public class PretragaLetova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "broj_putnika")
    private int brojPutnika;

    @Column(name = "sifra_polaznog_aerodroma")
    private String sifraPolaznogAerodroma;

    @Column(name = "sifra_odredisnjeg_aerodroma")
    private String sifraOdredisnjegAerodroma;

    @Column(name = "datum_polaska")
    private String datumPolaska;

    @Column(name = "datum_dolaska")
    private String datumDolaska;

    @Column(name = "sifra_valute")
    private String sifraValute;

    @Column(name = "random")
    private String random;

    public PretragaLetova() {
    }

    public PretragaLetova(int brojPutnika, String sifraPolaznogAerodroma, String sifraOdredisnjegAerodroma,
                          String datumPolaska, String datumDolaska, String sifraValute, String random) {
        this.brojPutnika = brojPutnika;
        this.sifraPolaznogAerodroma = sifraPolaznogAerodroma;
        this.sifraOdredisnjegAerodroma = sifraOdredisnjegAerodroma;
        this.datumPolaska = datumPolaska;
        this.datumDolaska = datumDolaska;
        this.sifraValute = sifraValute;
        this.random = random;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojPutnika() {
        return brojPutnika;
    }

    public void setBrojPutnika(int brojPutnika) {
        this.brojPutnika = brojPutnika;
    }

    public String getSifraPolaznogAerodroma() {
        return sifraPolaznogAerodroma;
    }

    public void setSifraPolaznogAerodroma(String sifraPolaznogAerodroma) {
        this.sifraPolaznogAerodroma = sifraPolaznogAerodroma;
    }

    public String getSifraOdredisnjegAerodroma() {
        return sifraOdredisnjegAerodroma;
    }

    public void setSifraOdredisnjegAerodroma(String sifraOdredisnjegAerodroma) {
        this.sifraOdredisnjegAerodroma = sifraOdredisnjegAerodroma;
    }

    public String getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(String datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public String getDatumDolaska() {
        return datumDolaska;
    }

    public void setDatumDolaska(String datumDolaska) {
        this.datumDolaska = datumDolaska;
    }

    public String getSifraValute() {
        return sifraValute;
    }

    public void setSifraValute(String sifraValute) {
        this.sifraValute = sifraValute;
    }

}
