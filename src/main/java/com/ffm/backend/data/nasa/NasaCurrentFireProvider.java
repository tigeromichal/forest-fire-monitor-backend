package com.ffm.backend.data.nasa;

import com.ffm.backend.data.CurrentFireProvider;
import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.CurrentFire;
import com.ffm.backend.data.nasa.providers.J1ViirsCurrentFireProvider;
import com.ffm.backend.data.nasa.providers.ModisCurrentFireProvider;
import com.ffm.backend.data.nasa.providers.SuomiViirsCurrentFireProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class NasaCurrentFireProvider implements CurrentFireProvider {

    private final List<CurrentFireProvider> nasaCurrentFireProviders = List.of(
        new J1ViirsCurrentFireProvider(),
        new SuomiViirsCurrentFireProvider(),
        new ModisCurrentFireProvider()
    );

    @Override
    public List<CurrentFire> getCurrentFires(QueryArea queryArea) {
        return collectCurrentFires(queryArea);
    }

    private List<CurrentFire> collectCurrentFires(QueryArea queryArea) {
        return nasaCurrentFireProviders.stream()
            .map(provider -> provider.getCurrentFires(queryArea))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

}
