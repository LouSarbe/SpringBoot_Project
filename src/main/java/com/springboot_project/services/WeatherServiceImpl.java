package com.springboot_project.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.springboot_project.models.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementation of the {@link com.springboot_project.services.WeatherService
 * WeatherService} interface.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    String url = null;

    public WeatherServiceImpl() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/static/WEATHER_API_KEY.txt"))) {
            url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s/today?unitGroup=metric&key="
                    + reader.readLine() + "&contentType=json";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public String getWeatherAsync(String location) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, location)))
                .build();
        try {
            CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            WeatherResponse weatherResponse = MapResponseToWeatherResponse(responseFuture);
            return weatherResponse.serializeToJson();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    /** 
     * Maps the response to a WeatherResponse object.
     * @param responseFuture the response future
     * @return the WeatherResponse object
     * @see com.springboot_project.models.WeatherResponse WeatherResponse
    */
    private WeatherResponse MapResponseToWeatherResponse(CompletableFuture<HttpResponse<String>> responseFuture) {
        CompletableFuture<WeatherResponse> wCompletableFuture = responseFuture.thenApply(response -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode rootNode = objectMapper.readTree(response.body());
                JsonNode daysNode = rootNode.path("days");
            if (daysNode.isArray()) {
                WeatherResponse weatherResponse = MapDaysNodeToWeatherResponse(daysNode, new WeatherResponse());
                return weatherResponse;
            }
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        });
        return wCompletableFuture.join();
    }

    /** 
     * Maps the days node to a WeatherResponse object.
     * @param daysNode the days node
     * @param weatherResponse the weather response
     * @return the WeatherResponse object
     * @see com.springboot_project.models.WeatherResponse WeatherResponse
    */
    private WeatherResponse MapDaysNodeToWeatherResponse(JsonNode daysNode, WeatherResponse weatherResponse) {
        for (JsonNode dayNode : daysNode) {
            String dayDateTime = dayNode.path("datetime").asText();
            int dayUvIndex = dayNode.path("uvindex").asInt();
            weatherResponse.addUVIndexData(dayDateTime, dayUvIndex);

            // Access uvIndex and datetime from the hourly data
            JsonNode hoursNode = dayNode.path("hours");
            if (hoursNode.isArray()) {
                for (JsonNode hourNode : hoursNode) {
                    String hourDateTime = hourNode.path("datetime").asText();
                    int hourlyUvIndex = hourNode.path("uvindex").asInt();
                    weatherResponse.addUVIndexData(hourDateTime, hourlyUvIndex);
                }
            }
        }
        return weatherResponse;
    }
}
