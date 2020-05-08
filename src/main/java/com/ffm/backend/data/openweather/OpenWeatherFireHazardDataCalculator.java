package com.ffm.backend.data.openweather;

import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.data.openweather.model.AbstractOpenWeatherResponse;

import java.util.Map;

public class OpenWeatherFireHazardDataCalculator {

    public FireHazardData calculate(Map<QueryPoint, ? extends AbstractOpenWeatherResponse> openWeatherResponses) {
        openWeatherResponses.forEach((queryPoint, openWeatherResponse) -> {
            openWeatherResponse.getMain().getTemp();
            openWeatherResponse.getMain().getTemp_max();
            openWeatherResponse.getMain().getHumidity();
        });
        return new FireHazardData();// TODO implement
    }
}
