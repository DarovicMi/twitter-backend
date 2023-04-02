package com.twitter.controllers;

import com.twitter.notification.Notification;
import com.twitter.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public List<Notification> getNotificationsForCurrentUser() {
        return notificationService.getNotificationsForCurrentUser();
    }
    @PutMapping("/notifications/mark-as-read")
    public ResponseEntity<Void> markNotificationsAsRead() {
        notificationService.markNotificationsAsRead();
        return ResponseEntity.ok().build();
    }
}
