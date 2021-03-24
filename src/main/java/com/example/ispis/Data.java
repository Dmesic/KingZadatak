package com.example.ispis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class Data {

    private String type;
    private String id;
    private String source;
    private String lastTicketingDate;
    private List<Itinerary> itineraries;
    private int numberOfBookableSeats;
    private Price price;
    private List<TravelPricing> travelPricings;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLastTicketingDate() {
        return lastTicketingDate;
    }

    public void setLastTicketingDate(String lastTicketingDate) {
        this.lastTicketingDate = lastTicketingDate;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public int getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    public void setNumberOfBookableSeats(int numberOfBookableSeats) {
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<TravelPricing> getTravelPricings() {
        return travelPricings;
    }

    public void setTravelPricings(List<TravelPricing> travelPricings) {
        this.travelPricings = travelPricings;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
