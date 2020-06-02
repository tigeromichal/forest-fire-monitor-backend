package com.ffm.backend.api;

import com.ffm.backend.config.api.ApiRestController;
import com.ffm.backend.data.model.output.FireData;
import com.ffm.backend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@ApiRestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notifications/{fcmToken}")
    public FireData getNotificationFireData(@PathVariable String fcmToken) {
        return notificationService.findByFcmToken(fcmToken).getFireData();
    }
}
