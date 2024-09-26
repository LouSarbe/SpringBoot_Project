package com.springboot_project.services;

/**
 * Weather service
 * @see com.springboot_project.services.WeatherServiceImpl WeatherServiceImpl
 */
public interface WeatherService {
            
            /**
            * Get weather for a location
            * @param location the location
            * @return the weather
            */
            String getWeatherAsync(String location);
}
