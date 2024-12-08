package com.example.besmsk.service;

import com.example.besmsk.model.Notification;
import com.example.besmsk.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Simulate sending a notification
    public void sendNotification(Notification notification) {
        System.out.println("Sending notification: " + notification.getContent());
        // Logic to send notification (e.g., send email, push notification, etc.)
    }
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }
    // Simulate retrieving notifications
    public List<Notification> getNotificationsByProductId(String productId) {
        return notificationRepository.findAllByProductId(productId);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}