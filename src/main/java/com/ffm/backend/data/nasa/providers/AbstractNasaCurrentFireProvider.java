package com.ffm.backend.data.nasa.providers;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.ffm.backend.data.CurrentFireProvider;
import com.ffm.backend.data.model.input.QueryArea;
import com.ffm.backend.data.model.output.CurrentFire;
import com.ffm.backend.data.nasa.model.AbstractNasaRow;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractNasaCurrentFireProvider<ROW extends AbstractNasaRow> implements CurrentFireProvider {

    private static final String CACHE_KEY = "CACHE_KEY";
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    private final Class<ROW> clazz;
    private final URL url;
    private final ObjectReader objectReader;
    private final Cache<String, List<ROW>> rowCache;

    public AbstractNasaCurrentFireProvider(Class<ROW> clazz, URL url) {
        this.clazz = clazz;
        this.url = url;
        this.objectReader = new CsvMapper().readerWithSchemaFor(clazz);
        this.rowCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build();
    }

    @SneakyThrows
    static URL buildUrl(String url) {
        return new URL(url);
    }

    @Override
    public List<CurrentFire> getCurrentFires(QueryArea queryArea) {
        return fetchRows().stream()
            .filter(row -> isInQueryArea(row, queryArea))
            .map(AbstractNasaRow::mapToCurrentFire)
            .collect(Collectors.toList());
    }

    private List<ROW> fetchRows() {
        return Optional.ofNullable(rowCache.getIfPresent(CACHE_KEY)).orElseGet(this::downloadRows);
    }

    private List<ROW> downloadRows() {
        try {
            List<ROW> rows = objectReader.<ROW>readValues(url).readAll().stream().skip(1).collect(Collectors.toList());
            rowCache.put(CACHE_KEY, rows);
            return rows;
        } catch (IOException e) {
            log.error("Downloading {} data failed:", clazz.getSimpleName(), e);
            return Collections.emptyList();
        }
    }

    private boolean isInQueryArea(ROW row, QueryArea queryArea) {
        Coordinate coordinate = new CoordinateXY(
            Double.parseDouble(row.getLongitude()),
            Double.parseDouble(row.getLatitude())
        );
        Point point = geometryFactory.createPoint(coordinate);
        return queryArea.getPolygon().contains(point);
    }
}
