package com.cesarferreira.quickutils.sample.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cesarferreira on 27/10/14.
 */
public class WeatherEntity {
    @SerializedName("coord")
    public Coordinates coordinates;

    private class Coordinates {
        @SerializedName("lon")
        public long longitude;
        @SerializedName("lat")
        public long latitude;
    }

    @SerializedName("weather")
    public List<Weather> weather;

    @Override
    public String toString() {
        return weather.get(0).description;
    }

    private class Weather {
        @SerializedName("description")
        public String description;
    }
}
