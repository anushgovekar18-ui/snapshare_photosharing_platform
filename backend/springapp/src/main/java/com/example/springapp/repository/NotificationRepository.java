package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Fetch all notifications for a specific user (JPQL)
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId ORDER BY n.createdAt DESC")
    List<Notification> findByUserId(Long userId);

    // Fetch unread notifications for a user (JPQL)
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.read = false ORDER BY n.createdAt DESC")
    List<Notification> findUnreadByUserId(Long userId);
}
