package com.ffm.backend.data.openweather;

import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.data.openweather.model.AbstractOpenWeatherResponse;
import com.ffm.backend.service.FireHazardCalculator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenWeatherFireHazardDataCalculator {

    private final FireHazardCalculator fireHazardCalculator = new FireHazardCalculator();

    public List<FireHazardData> calculate(Map<QueryPoint, ? extends AbstractOpenWeatherResponse> openWeatherResponses) {
        return openWeatherResponses.entrySet().stream()
                .map(e -> new FireHazardData(e.getKey(), hazard(e.getValue())))
                .collect(Collectors.toList());
    }

    private Float hazard(AbstractOpenWeatherResponse openWeatherResponse) {
        double tempMax = openWeatherResponse.getMain().getTemp_max();
        int humidity = openWeatherResponse.getMain().getHumidity();
        return fireHazardCalculator.calculate(tempMax, humidity);
    }
}
