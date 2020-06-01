package com.ffm.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffm.backend.config.transaction.RequiresNewTransactions;
import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireData;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.entity.Device;
import com.ffm.backend.geo.GeoUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiresNewTransactions
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private static final double FIRE_HAZARD_THRESHOLD = 50.0;
    private static final int SQUARE_DIAGONAL_METERS = 500000;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    private final DeviceService deviceService;
    private final FireDataService fireDataService;

    public void sendNotifications() {
        deviceService.findAll().forEach(this::sendNotification);
    }

    private void sendNotification(Device device) {
        List<QueryPoint> areaAroundDevice = getAreaAroundDevice(device);
        FireData fireData = filterHazardByThreshold(fireDataService.getFireData(areaAroundDevice));

        if (fireData.getCurrentFires().isEmpty() && fireData.getHazard().isEmpty()) {
            return;
        }

        try {
            Message message = prepareFirebaseMessage(fireData, device.getFcmToken());
            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            log.error("An error occurred when sending firebase notification:", e);
        }
    }

    private List<QueryPoint> getAreaAroundDevice(Device device) {
        Coordinate deviceCoordinate = getDeviceCoordinate(device);
        return GeoUtils.getSquareAroundCoordinate(deviceCoordinate, SQUARE_DIAGONAL_METERS);
    }

    private Coordinate getDeviceCoordinate(Device device) {
        return new CoordinateXY(
            device.getLongitude().doubleValue(),
            device.getLatitude().doubleValue()
        );
    }

    private Message prepareFirebaseMessage(FireData fireData, String fcmToken) throws JsonProcessingException {
        return Message.builder()
            .setToken(fcmToken)
            .putData("data", objectMapper.writeValueAsString(fireData))
            .build();
    }

    private FireData filterHazardByThreshold(FireData fireData) {
        List<FireHazardData> filteredHazard = fireData.getHazard().stream()
            .filter(fireHazardData -> fireHazardData.getHazard() > FIRE_HAZARD_THRESHOLD)
            .collect(Collectors.toUnmodifiableList());

        return new FireData(fireData.getCurrentFires(), filteredHazard);
    }

}
