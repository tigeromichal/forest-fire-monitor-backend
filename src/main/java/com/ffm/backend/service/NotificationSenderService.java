package com.ffm.backend.service;

import com.ffm.backend.config.transaction.RequiresNewTransactions;
import com.ffm.backend.data.model.input.QueryPoint;
import com.ffm.backend.data.model.output.FireData;
import com.ffm.backend.data.model.output.FireHazardData;
import com.ffm.backend.entity.Device;
import com.ffm.backend.entity.Notification;
import com.ffm.backend.geo.GeoUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiresNewTransactions
@Slf4j
@RequiredArgsConstructor
public class NotificationSenderService {

    @Value("${notifications.hazard-threshold}")
    private int FIRE_HAZARD_THRESHOLD;

    @Value("${notifications.diagonal-meters}")
    private int SQUARE_DIAGONAL_METERS;

    private final DeviceService deviceService;
    private final FireDataService fireDataService;
    private final NotificationService notificationService;

    public void sendOrSkipAllNotifications() {
        deviceService.findAll().forEach(this::sendOrSkipNotification);
    }

    private void sendOrSkipNotification(Device device) {

        FireData fireData = getFireDataForDevice(device);
        if (fireData.isEmpty()) {
            log.debug(
                "Notification for fcmToken: '{}' skipped because there is no fires or hazards.", device.getFcmToken());
            return;
        }

        Notification notification = new Notification(device.getFcmToken(), fireData);
        if (sameNotificationExists(notification)) {
            log.debug(
                "Notification for fcmToken: '{}' skipped because it is identical to last one.", device.getFcmToken());
            return;
        }

        sendAndPersistNotification(notification);
        log.debug("Notification for fcmToken: '{}' sent.", device.getFcmToken());
    }

    private FireData getFireDataForDevice(Device device) {
        List<QueryPoint> areaAroundDevice = getAreaAroundDevice(device);
        FireData fireData = fireDataService.getFireData(areaAroundDevice);
        return filterHazardByThreshold(fireData);
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

    private FireData filterHazardByThreshold(FireData fireData) {
        List<FireHazardData> filteredHazard = fireData.getHazard().stream()
            .filter(fireHazardData -> fireHazardData.getHazard() > FIRE_HAZARD_THRESHOLD)
            .collect(Collectors.toUnmodifiableList());

        return new FireData(fireData.getCurrentFires(), filteredHazard);
    }

    private boolean sameNotificationExists(Notification notification) {
        return notificationService.sameNotificationExists(notification);
    }

    private void sendAndPersistNotification(Notification notification) {
        try {
            Message message = prepareFirebaseMessage(notification);
            FirebaseMessaging.getInstance().send(message);
            persistNotification(notification);
        } catch (Exception e) {
            log.error("An error occurred when sending firebase notification:", e);
        }
    }

    private Message prepareFirebaseMessage(Notification notification) {
        return Message.builder()
            .setTopic("temacik")
//            .setToken(notification.getFcmToken())
            .putData("fireCount", String.valueOf(notification.getFireData().getCurrentFires().size()))
            .putData("hazardCount", String.valueOf(notification.getFireData().getHazard().size()))
            .build();
    }

    private void persistNotification(Notification notification) {
        notificationService.saveOrUpdate(notification);
    }

}
