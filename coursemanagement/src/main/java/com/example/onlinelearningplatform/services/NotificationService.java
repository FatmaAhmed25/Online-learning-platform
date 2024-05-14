package com.example.onlinelearningplatform.services;

import com.example.onlinelearningplatform.models.Notification;
import com.example.onlinelearningplatform.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public void sendNotification(Notification notification) {

        notificationRepository.save(notification);

    }
    public List<Notification> getAllNotificationsSortedByTimestampDesc(Long studentId) {
        List<Notification> notifications = notificationRepository.findByStudentId(studentId);

        // Sort notifications by timestamp in descending order
        List<Notification> sortedNotifications = notifications.stream()
                .sorted(Comparator.comparing(Notification::getTimestamp).reversed())
                .collect(Collectors.toList());

        return sortedNotifications;
    }
}
