package com.example.ispis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Arrival {

    private String iataCode;
    private String terminal;
    private String at;

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "iataCode='" + iataCode + '\'' +
                ", terminal='" + terminal + '\'' +
                ", at='" + at + '\'' +
                '}';
    }
}
