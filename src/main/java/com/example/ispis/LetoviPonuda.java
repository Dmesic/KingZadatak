package com.example.ispis;

import javax.persistence.*;

@Entity
@Table(name = "letovi_ponuda")
public class LetoviPonuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "polazni_aerodrom")
    private String polazniAerodrom;

    @Column(name = "odredisnji_aerodrom")
    private String odredisnjiAerodrom;

    @Column(name = "vrijeme_polaska")
    private String vrijemePolaska;

    @Column(name = "vrijeme_dolaska")
    private String vrijemeDolaska;

    @Column(name = "broj_presjedanja")
    private int brojPresjedanja;

    @Column(name = "broj_putnika")
    private int brojPutnika;

    @Column(name = "ukupna_cijena")
    private String ukupnaCijena;

    @Column(name = "random")
    private String random;

    public LetoviPonuda() {
    }


    public LetoviPonuda(String polazniAerodrom, String odredisnjiAerodrom, String vrijemePolaska, String vrijemeDolaska,
                        int brojPresjedanja, int brojPutnika, String ukupnaCijena) {
        this.polazniAerodrom = polazniAerodrom;
        this.odredisnjiAerodrom = odredisnjiAerodrom;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemeDolaska;
        this.brojPresjedanja = brojPresjedanja;
        this.brojPutnika = brojPutnika;
        this.ukupnaCijena = ukupnaCijena;
    }

    public LetoviPonuda(String polazniAerodrom, String odredisnjiAerodrom, String vrijemePolaska,
                        String vrijemeDolaska, int brojPresjedanja, int brojPutnika, String ukupnaCijena,
                        String random) {
        this.id = id;
        this.polazniAerodrom = polazniAerodrom;
        this.odredisnjiAerodrom = odredisnjiAerodrom;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemeDolaska;
        this.brojPresjedanja = brojPresjedanja;
        this.brojPutnika = brojPutnika;
        this.ukupnaCijena = ukupnaCijena;
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

    public String getPolazniAerodrom() {
        return polazniAerodrom;
    }

    public void setPolazniAerodrom(String polazniAerodrom) {
        this.polazniAerodrom = polazniAerodrom;
    }

    public String getOdredisnjiAerodrom() {
        return odredisnjiAerodrom;
    }

    public void setOdredisnjiAerodrom(String odredisnjiAerodrom) {
        this.odredisnjiAerodrom = odredisnjiAerodrom;
    }

    public String getVrijemePolaska() {
        return vrijemePolaska;
    }

    public void setVrijemePolaska(String vrijemePolaska) {
        this.vrijemePolaska = vrijemePolaska;
    }

    public String getVrijemeDolaska() {
        return vrijemeDolaska;
    }

    public void setVrijemeDolaska(String vrijemeDolaska) {
        this.vrijemeDolaska = vrijemeDolaska;
    }

    public int getBrojPresjedanja() {
        return brojPresjedanja;
    }

    public void setBrojPresjedanja(int brojPresjedanja) {
        this.brojPresjedanja = brojPresjedanja;
    }

    public int getBrojPutnika() {
        return brojPutnika;
    }

    public void setBrojPutnika(int brojPutnika) {
        this.brojPutnika = brojPutnika;
    }

    public String getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(String ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }
}
