package com.ffm.backend.api;

import com.ffm.backend.config.api.ApiRestController;
import com.ffm.backend.data.model.input.FcmTokenPayload;
import com.ffm.backend.data.model.output.FireData;
import com.ffm.backend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiRestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notifications")
    public FireData getNotificationFireData(@RequestBody FcmTokenPayload fcmToken) {
        return notificationService.findByFcmToken(fcmToken.getToken()).getFireData();
    }
}