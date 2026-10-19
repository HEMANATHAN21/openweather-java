package com.weather.api.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
	private Current current;
    private Astro astro;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    @Override
    public String toString() {
        return "WeatherData [current=" + current + ", astro=" + astro + "]";
    }
}@JsonIgnoreProperties(ignoreUnknown = true)
class Current {
    private int temperature;
    private int wind_speed;
    private int humidity;
    private int pressure;

    // Getters and Setters
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(int wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Current [temperature=" + temperature + ", wind_speed=" + wind_speed +
               ", humidity=" + humidity + ", pressure=" + pressure + "]";
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Astro {
    private String sunrise;
    private String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Astro [sunrise=" + sunrise + ", sunset=" + sunset + "]";
    }
}