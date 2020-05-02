package com.ffm.backend.data.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class QueryPoint {

    private final BigDecimal latitude;
    private final BigDecimal longitude;

    public Coordinate toJtsCoordinate() {
        return new CoordinateXY(longitude.doubleValue(), latitude.doubleValue());
    }
}
