package com.example.ispis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class TravelPricing {

    private String travelId;
    private String travelType;
    private Price price;

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TravelPricing{" +
                "travelId='" + travelId + '\'' +
                ", travelType='" + travelType + '\'' +
                ", price=" + price +
                '}';
    }
}
