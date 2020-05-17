package com.ffm.backend.service;

import com.ffm.backend.data.CurrentFireProvider;
import com.ffm.backend.data.FireHazardDataProvider;
import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.CurrentFire;
import com.ffm.backend.data.model.output.FireData;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.data.nasa.NasaCurrentFireProvider;
import com.ffm.backend.data.openweather.OpenWeatherFireHazardDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireDataAggregateService {

    private final List<CurrentFireProvider> currentFireProviders = List.of(
        new NasaCurrentFireProvider()
    );

    private final FireHazardDataProvider fireHazardDataProvider = new OpenWeatherFireHazardDataProvider();

    public FireData getAggregatedData(QueryArea queryArea) {
        List<CurrentFire> currentFires = collectCurrentFires(queryArea);
        List<FireHazardData> fireHazardData = collectFireHazardData(queryArea);
        return new FireData(currentFires, fireHazardData);
    }

    private List<CurrentFire> collectCurrentFires(QueryArea queryArea) {
        return currentFireProviders.stream()
            .map(provider -> provider.getCurrentFires(queryArea))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private List<FireHazardData> collectFireHazardData(QueryArea queryArea) {
        return fireHazardDataProvider.getFireHazardData(queryArea);
    }
}
