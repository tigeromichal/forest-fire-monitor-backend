package com.ffm.backend.data.model.input;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import java.util.Collections;
import java.util.List;

@Getter
@EqualsAndHashCode
public class QueryArea {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    private final Polygon polygon;
    private final List<QueryPoint> queryPoints;

    public QueryArea(List<QueryPoint> queryPoints) {
        this.queryPoints = Collections.unmodifiableList(queryPoints);

        Coordinate[] coordinates = queryPoints.stream()
            .map(QueryPoint::toJtsCoordinate)
            .toArray(Coordinate[]::new);
        LinearRing linearRing = geometryFactory.createLinearRing(coordinates);
        this.polygon = geometryFactory.createPolygon(linearRing, null);
    }
}
