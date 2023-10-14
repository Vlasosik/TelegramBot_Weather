package org.example.telegram_bot_weather.weatherAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherData {
    private Coord coord;
    private Weather[] weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;

    @JsonProperty("dt")
    private long dateTime;

    private Sys sys;

    @JsonProperty("id")
    private int cityId;
    private String name;
    private int cod;
}

@Data
class Coord {
    private double lon;
    private double lat;
}

@Data
class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
}

@Data
class Main {
    private double temp;
    @JsonProperty("feelsLike")
    private double feelsLike;
    @JsonProperty("tempMin")
    private double tempMin;
    @JsonProperty("tempMax")
    private double tempMax;
    private int pressure;
    private int humidity;
}

@Data
class Wind {
    private double speed;
    private int deg;
    private double gust;
}

@Data
class Clouds {
    private int all;
}

@Data
class Sys {
    private int type;
    private int id;
    private String country;
    @JsonProperty("sunrise")
    private long sunriseTime;
    @JsonProperty("sunset")
    private long sunsetTime;
}
