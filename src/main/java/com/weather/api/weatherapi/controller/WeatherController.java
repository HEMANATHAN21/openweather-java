package com.weather.api.weatherapi.controller;

import com.weather.api.weatherapi.dto.OpenWeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.weather.api.weatherapi.dto.WeatherData;
import com.weather.api.weatherapi.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);
    @Autowired
		WeatherService weatherService;

	    @GetMapping("/current")
	    public OpenWeatherDto getCurrentWeather(@RequestParam String city) {

            log.info("getCurrentWeather");
            return weatherService.getCurrentWeather(city);
	    }


    @GetMapping("/current/location")
    public ResponseEntity<OpenWeatherDto> getWeatherByCoordinates(@RequestParam double lat, @RequestParam double lon) {

        OpenWeatherDto weather = weatherService.getCurrentWeatherByCoordinates(lat, lon);

        return ResponseEntity.ok(weather);
    }


}
