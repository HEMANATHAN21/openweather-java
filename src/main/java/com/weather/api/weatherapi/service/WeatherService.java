package com.weather.api.weatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weather.api.weatherapi.dto.OpenWeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.api.weatherapi.config.WeatherProperties;
import com.weather.api.weatherapi.dto.WeatherData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherProperties weatherProperties;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();


    public OpenWeatherDto getCurrentWeather(String city) {

        try {
            String apiKey = weatherProperties.getApiKey();

            // Get latitude and longitude
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

            String geoUrl = "https://api.openweathermap.org/geo/1.0/direct" + "?q=" + encodedCity + "&limit=1" + "&appid=" + apiKey;

            String geoResponse = restTemplate.getForObject(geoUrl, String.class);

            log.info("geoResponse: " + geoResponse);

            JsonNode locations = objectMapper.readTree(geoResponse);

            if (!locations.isArray() || locations.isEmpty()) {
                throw new RuntimeException("City not found: " + city);
            }

            double lat = locations.get(0).get("lat").asDouble();
            double lon = locations.get(0).get("lon").asDouble();

            // Get current weather
            String weatherUrl = "https://api.openweathermap.org/data/2.5/weather" + "?lat=" + lat + "&lon=" + lon + "&units=metric" + "&appid=" + apiKey;

            String weatherResponse = restTemplate.getForObject(weatherUrl, String.class);

            log.info("getCurrentWeather response: " + weatherResponse);

            JsonNode weatherJson = objectMapper.readTree(weatherResponse);

            OpenWeatherDto data = objectMapper.readValue(weatherResponse, OpenWeatherDto.class);

            return data;

        } catch (Exception e) {
            throw new RuntimeException("Failed to get weather for " + city, e);
        }
    }


    public OpenWeatherDto getCurrentWeatherByCoordinates(double lat, double lon) {

        try {
            String apiKey = weatherProperties.getApiKey();

            String weatherUrl =
                    "https://api.openweathermap.org/data/2.5/weather"
                            + "?lat=" + lat
                            + "&lon=" + lon
                            + "&units=metric"
                            + "&appid=" + apiKey;

            String weatherResponse = restTemplate.getForObject(weatherUrl, String.class);

            log.info("Weather by coordinates response: {}", weatherResponse);

            return objectMapper.readValue(weatherResponse, OpenWeatherDto.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to get weather for coordinates: " + lat + ", " + lon, e);
        }
    }


}
