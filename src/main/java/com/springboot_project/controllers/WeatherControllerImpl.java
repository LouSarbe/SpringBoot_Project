package com.springboot_project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot_project.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Implementation of the {@link com.springboot_project.controllers.WeatherController WeatherController} interface. 
 */
@RestController
@RequestMapping("/api/weather")
public class WeatherControllerImpl implements WeatherController {

    private final WeatherService weatherService;

    public WeatherControllerImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{location}")
    public String getWeatherAsync(@PathVariable String location) {
        return weatherService.getWeatherAsync(location);
    }
    
}
