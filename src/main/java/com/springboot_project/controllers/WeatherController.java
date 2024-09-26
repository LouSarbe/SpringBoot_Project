package com.springboot_project.controllers;

/**
 * Weather controller
 * @see com.springboot_project.controllers.WeatherControllerImpl WeatherControllerImpl
 */
public interface WeatherController {
        
        /**
        * Get weather for a location
        * @param location the location
        * @return the weather
        */
        String getWeatherAsync(String location);
}
