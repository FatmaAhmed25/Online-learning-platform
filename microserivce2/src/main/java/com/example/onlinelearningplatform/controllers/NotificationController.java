package com.example.onlinelearningplatform.controllers;

import com.example.onlinelearningplatform.models.Notification;
import com.example.onlinelearningplatform.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{studentId}")
    public List<Notification> getNotifications(@PathVariable Long studentId) {
        // Fetch notifications sorted by timestamp (newest first)
        List<Notification> notifications = notificationService.getAllNotificationsSortedByTimestampDesc(studentId);
        return notifications;
    }
}

