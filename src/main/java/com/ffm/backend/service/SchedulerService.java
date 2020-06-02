package com.ffm.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final NotificationSenderService notificationSenderService;

    @Scheduled(fixedRateString = "${notifications.interval-milliseconds}")
    public void sendNotifications() {
        notificationSenderService.sendOrSkipAllNotifications();
    }
}
