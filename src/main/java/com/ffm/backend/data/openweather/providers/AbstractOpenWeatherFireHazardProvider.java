package com.ffm.backend.data.openweather.providers;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ffm.backend.data.FireHazardDataProvider;
import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.data.openweather.OpenWeatherFireHazardDataCalculator;
import com.ffm.backend.data.openweather.model.AbstractOpenWeatherResponse;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractOpenWeatherFireHazardProvider<T extends AbstractOpenWeatherResponse> implements FireHazardDataProvider {

    protected static final String API_KEY = "8198e608417d136e28b2b194f559951c";

    private final Class<T> clazz;
    private final String urlBase;
    private final ObjectReader objectReader;
    private final Cache<QueryPoint, T> rowCache;
    private final OpenWeatherFireHazardDataCalculator fireHazardDataCalculator = new OpenWeatherFireHazardDataCalculator();

    public AbstractOpenWeatherFireHazardProvider(Class<T> clazz, String urlBase) {
        this.clazz = clazz;
        this.urlBase = urlBase;
        this.objectReader = new JsonMapper().readerFor(clazz);
        this.rowCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build();
    }

    @SneakyThrows
    static URL buildUrl(String urlBase, QueryPoint queryPoint) {
        return new URL(String.format(urlBase, queryPoint.getLatitude(), queryPoint.getLongitude()));
    }

    @Override
    public FireHazardData getFireHazardData(QueryArea queryArea) {
        Map<QueryPoint, T> rows = queryArea.getQueryPoints().stream()
                .map(queryPoint -> {
                    QueryPoint centroid = QueryPoint.fromJtsCoordinate(queryArea.getPolygon().getCentroid().getCoordinate());
                    return new AbstractMap.SimpleEntry<>(centroid, fetchRow(centroid));
                })
                .distinct()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return fireHazardDataCalculator.calculate(rows);
    }

    private T fetchRow(QueryPoint queryPoint) {
        return Optional.ofNullable(rowCache.getIfPresent(queryPoint)).orElseGet(() -> downloadRow(queryPoint));
    }

    private T downloadRow(QueryPoint queryPoint) {
        try {
            T row = objectReader.readValue(buildUrl(urlBase, queryPoint));
            rowCache.put(queryPoint, row);
            return row;
        } catch (IOException e) {
            log.error("Downloading {} data failed:", clazz.getSimpleName(), e);
            return null;
        }
    }
}
