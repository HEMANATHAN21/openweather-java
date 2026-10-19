package com.weather.api.weatherapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherConfig {
	
	 	@Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	    @Bean
	    @ConfigurationProperties(prefix = "weather")
	    public WeatherProperties weatherProperties() {
	        return new WeatherProperties();
	    }
	
}
