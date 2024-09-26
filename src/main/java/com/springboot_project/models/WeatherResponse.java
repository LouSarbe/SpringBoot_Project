package com.springboot_project.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the response containing weather data, specifically UV index data.
 */
public class WeatherResponse {

    private List<UVIndexData> uvIndexDataList;

    /**
     * Default constructor initializing the UV index data list.
     */
    public WeatherResponse() {
        this.uvIndexDataList = new ArrayList<>();
    }

    /**
     * Adds a UV index data entry to the list.
     *
     * @param datetime the date and time of the UV index data
     * @param uvIndex the UV index value
     */
    public void addUVIndexData(String datetime, int uvIndex) {
        uvIndexDataList.add(new UVIndexData(datetime, uvIndex));
    }

    /**
     * Serializes the WeatherResponse object to a JSON string.
     *
     * @return the JSON string representation of the WeatherResponse object
     * @throws JsonProcessingException if an error occurs during serialization
     */
    public String serializeToJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    /**
     * Gets the list of UV index data.
     *
     * @return the list of UV index data
     */
    public List<UVIndexData> getUvIndexDataList() {
        return uvIndexDataList;
    }

    /**
     * Sets the list of UV index data.
     *
     * @param uvIndexDataList the list of UV index data to set
     */
    public void setUvIndexDataList(List<UVIndexData> uvIndexDataList) {
        this.uvIndexDataList = uvIndexDataList;
    }

    /**
     * Represents a single UV index data entry.
     */
    public static class UVIndexData {
        private String datetime;
        private int uvIndex;

        /**
         * Constructor initializing the UV index data entry.
         *
         * @param datetime the date and time of the UV index data
         * @param uvIndex the UV index value
         */
        public UVIndexData(String datetime, int uvIndex) {
            this.datetime = datetime;
            this.uvIndex = uvIndex;
        }

        /**
         * Gets the date and time of the UV index data.
         *
         * @return the date and time of the UV index data
         */
        public String getDatetime() {
            return datetime;
        }

        /**
         * Sets the date and time of the UV index data.
         *
         * @param datetime the date and time to set
         */
        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        /**
         * Gets the UV index value.
         *
         * @return the UV index value
         */
        public int getUvIndex() {
            return uvIndex;
        }

        /**
         * Sets the UV index value.
         *
         * @param uvIndex the UV index value to set
         */
        public void setUvIndex(int uvIndex) {
            this.uvIndex = uvIndex;
        }
    }
}
