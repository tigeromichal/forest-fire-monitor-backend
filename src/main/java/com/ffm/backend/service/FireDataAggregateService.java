package com.ffm.backend.service;

import com.ffm.backend.data.CurrentFireProvider;
import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.CurrentFire;
import com.ffm.backend.data.model.output.FireData;
import com.ffm.backend.data.nasa.NasaCurrentFireProvider;
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

    public FireData getAggregatedData(QueryArea queryArea) {
        List<CurrentFire> currentFires = collectCurrentFires(queryArea);
        // TODO implement and set hazard data
        return new FireData(currentFires, null);
    }

    private List<CurrentFire> collectCurrentFires(QueryArea queryArea) {
        return currentFireProviders.stream()
            .map(provider -> provider.getCurrentFires(queryArea))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
}
