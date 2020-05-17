package com.ffm.backend.data.openweather;

import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.data.openweather.model.AbstractOpenWeatherResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenWeatherFireHazardDataCalculator {

    public List<FireHazardData> calculate(Map<QueryPoint, ? extends AbstractOpenWeatherResponse> openWeatherResponses) {
        return openWeatherResponses.entrySet().stream()
                .map(e -> new FireHazardData(e.getKey(), hazard(e.getValue())))
                .collect(Collectors.toList());
    }

    private Float hazard(AbstractOpenWeatherResponse openWeatherResponse) {
        openWeatherResponse.getMain().getTemp();
        openWeatherResponse.getMain().getTemp_max();
        openWeatherResponse.getMain().getHumidity();
        return (float) 0.5;
    }
}
