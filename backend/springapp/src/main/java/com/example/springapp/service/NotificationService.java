package com.example.springapp.service;

import com.example.springapp.model.Notification;
import com.example.springapp.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // Constructor injection
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notification by ID
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    // Get notifications for a specific user
    public List<Notification> getNotificationsByUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Get unread notifications for a user
    public List<Notification> getUnreadNotificationsByUser(Long userId) {
        return notificationRepository.findUnreadByUserId(userId);
    }

    // Create a new notification
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Mark a notification as read
    public Notification markAsRead(Long id) {
        return notificationRepository.findById(id)
                .map(existingNotification -> {
                    existingNotification.setRead(true);
                    return notificationRepository.save(existingNotification);
                })
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    // Delete a notification
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found with id: " + id);
        }
        notificationRepository.deleteById(id);
    }

    // Delete all notifications for a user
    public void deleteAllNotificationsByUser(Long userId) {
        List<Notification> userNotifications = notificationRepository.findByUserId(userId);
        notificationRepository.deleteAll(userNotifications);
    }
}
