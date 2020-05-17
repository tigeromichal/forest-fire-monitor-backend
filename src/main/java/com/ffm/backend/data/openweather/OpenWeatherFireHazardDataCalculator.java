package com.ffm.backend.data.openweather;

import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.data.openweather.model.AbstractOpenWeatherResponse;

import java.util.Map;
import java.util.stream.Collectors;

public class OpenWeatherFireHazardDataCalculator {

    public FireHazardData calculate(Map<QueryPoint, ? extends AbstractOpenWeatherResponse> openWeatherResponses) {
        Map<QueryPoint, Float> hazard = openWeatherResponses.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> hazard(e.getValue())
                ));
        return new FireHazardData(hazard);
    }

    private Float hazard(AbstractOpenWeatherResponse openWeatherResponse) {
        openWeatherResponse.getMain().getTemp();
        openWeatherResponse.getMain().getTemp_max();
        openWeatherResponse.getMain().getHumidity();
        return (float) 0.5;
    }
}
