package com.example.tip_taxes.meteo.model;

public class Weather {

    public Location location;
    public Clouds clouds;
    public Wind wind;
    public Temperature temperature;
    public CurrentCondition currentCondition;

    public Weather() {
        location = new Location();
        clouds = new Clouds();
        wind = new Wind();
        temperature = new Temperature();
        currentCondition = new CurrentCondition();
    }
}
