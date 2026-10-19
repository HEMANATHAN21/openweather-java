package com.weather.api.weatherapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("weather")
public class WeatherProperties {
	
	private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
	
}
