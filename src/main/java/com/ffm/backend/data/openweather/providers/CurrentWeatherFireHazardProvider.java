package com.ffm.backend.data.openweather.providers;

import com.ffm.backend.data.openweather.model.CurrentWeatherOpenWeatherResponse;

public class CurrentWeatherFireHazardProvider extends AbstractOpenWeatherFireHazardProvider<CurrentWeatherOpenWeatherResponse> {

    private static final String URL =
            "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=" + API_KEY;

    public CurrentWeatherFireHazardProvider() {
        super(CurrentWeatherOpenWeatherResponse.class, URL);
    }
}
