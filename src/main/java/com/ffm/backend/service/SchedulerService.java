package com.ffm.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private static final int INTERVAL_MINUTES = 5;

    private final NotificationService notificationService;

    @Scheduled(fixedRate = 10000)
//    @Scheduled(fixedRate = 1000 * 60 * INTERVAL_MINUTES)
    public void sendNotifications() {
        notificationService.sendNotifications();
    }
}
