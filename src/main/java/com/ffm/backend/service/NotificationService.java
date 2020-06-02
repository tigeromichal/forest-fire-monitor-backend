package com.ffm.backend.service;

import com.ffm.backend.config.transaction.RequiresNewTransactions;
import com.ffm.backend.entity.Notification;
import com.ffm.backend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiresNewTransactions
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveOrUpdate(Notification notification) {
        notificationRepository.save(notification);
    }

    public Notification findByFcmToken(String fcmToken) {
        return notificationRepository.findById(fcmToken).orElseThrow(
            () -> new RuntimeException("Notification not found."));
    }

    public boolean sameNotificationExists(Notification notification) {
        return notificationRepository.findById(notification.getId())
            .map(persisted -> persisted.equals(notification))
            .orElse(false);
    }
}
