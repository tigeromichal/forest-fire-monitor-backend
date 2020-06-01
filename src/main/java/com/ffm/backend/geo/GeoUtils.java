package com.ffm.backend.geo;

import com.ffm.backend.data.model.input.QueryPoint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.geotools.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;

import java.awt.geom.Point2D;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeoUtils {

    public static List<QueryPoint> getSquareAroundCoordinate(Coordinate center, double diagonalMeters) {
        QueryPoint coord1 = QueryPoint.fromJtsCoordinate(
            getCoordinateAway(center, 45, diagonalMeters / 2)
        );
        QueryPoint coord2 = QueryPoint.fromJtsCoordinate(
            getCoordinateAway(center, 135, diagonalMeters / 2)
        );
        QueryPoint coord3 = QueryPoint.fromJtsCoordinate(
            getCoordinateAway(center, 225, diagonalMeters / 2)
        );
        QueryPoint coord4 = QueryPoint.fromJtsCoordinate(
            getCoordinateAway(center, 315, diagonalMeters / 2)
        );

        return List.of(coord1, coord2, coord3, coord4, coord1);
    }

    private static Coordinate getCoordinateAway(Coordinate start, int azimuth, double distanceMeters) {
        GeodeticCalculator geodeticCalculator = new GeodeticCalculator();

        geodeticCalculator.setStartingGeographicPoint(start.x, start.y);
        geodeticCalculator.setDirection(azimuth, distanceMeters);
        Point2D dest = geodeticCalculator.getDestinationGeographicPoint();

        return new CoordinateXY(dest.getX(), dest.getY());
    }

}
