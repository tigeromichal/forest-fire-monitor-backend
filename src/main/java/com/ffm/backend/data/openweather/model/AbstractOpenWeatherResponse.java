package com.ffm.backend.data.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonPropertyOrder({
        "coord", "weather", "base", "main", "visibility", "wind", "rain", "clouds", "dt", "sys", "timezone",
        "id", "name", "cod"
})
public abstract class AbstractOpenWeatherResponse {

    private Coord coord;
    private List<Weather> weather = new ArrayList<>();
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    @JsonProperty
    private Rain rain;
    private Clouds clouds;
    private String dt;
    private Sys sys;
    private String timezone;
    private String id;
    private String name;
    private String cod;

    @Getter
    @Setter
    public static class Coord {
        private String lon;
        private String lat;
    }

    @Getter
    @Setter
    public static class Weather {
        private String id;
        private String main;
        private String description;
        private String icon;
    }

    @Getter
    @Setter
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private double pressure;
        private int humidity;
    }

    @Getter
    @Setter
    public static class Wind {
        private double speed;
        private double deg;
        private String gust;
    }

    @Getter
    @Setter
    public static class Clouds {
        private int all;
    }

    @Getter
    @Setter
    public static class Sys {
        private int type;
        private String id;
        private double message;
        private String country;
        private int sunrise;
        private int sunset;
    }

    @Getter
    @Setter
    public static class Rain {
        private Map<String, String> values = new HashMap<>();
    }
}
