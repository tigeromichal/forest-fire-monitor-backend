package com.ffm.backend.data.openweather;

import com.ffm.backend.data.FireHazardDataProvider;
import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.data.openweather.providers.CurrentWeatherFireHazardProvider;

import java.util.List;

public class OpenWeatherFireHazardDataProvider implements FireHazardDataProvider {

    private final List<FireHazardDataProvider> openWeatherFireHazardDataProviders = List.of(
            new CurrentWeatherFireHazardProvider()
    );

    @Override
    public List<FireHazardData> getFireHazardData(QueryArea queryArea) {
        return openWeatherFireHazardDataProviders.stream()
                .map(provider -> provider.getFireHazardData(queryArea))
                .findFirst().orElseThrow();
    }
}
