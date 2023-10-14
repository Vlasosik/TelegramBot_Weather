package org.example.telegram_bot_weather.weatherAPI;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static javacore.Task13.telegram_bot_weather.weatherAPI.WeatherConstants.API_KEY;

public class WeatherServiceAPI implements WeatherService{
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final Gson GSON = new Gson();
    @Override
    @SneakyThrows
    public String getRate(WeatherCity weatherCity) {
        String city = weatherCity.getCityName();
        String uri = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid="+ API_KEY + "&units=metric";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            WeatherData weatherData = GSON.fromJson(json, WeatherData.class);
        return getInformationOnTheWeather(weatherCity, weatherData);
    }
    private static String getInformationOnTheWeather(WeatherCity weatherCity, WeatherData weatherData) {
        String cityName = weatherCity.name();
        Double temperature = weatherData.getMain().getTemp();
        Integer humidity = weatherData.getMain().getHumidity();
        Integer pressure = weatherData.getMain().getPressure();
        Integer visibility = weatherData.getVisibility() / 10;

        String weatherInfo = "Місто: " + cityName + "\n"
                + "Температура: " + temperature + "°C\n"
                + "Видімість : " + visibility + " метрів" + "\n"
                + "Вологість: " + humidity + "%\n"
                + "Тиск: " + pressure + " гПа" + "\n"
                + "Дякую за те, що скористались ботом!\n"
                + "СЛАВА УКРАЇНІ🫡🇺🇦";
        return weatherInfo;
    }
}
