package com.example.springapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // the recipient of the notification

    @Column(nullable = false)
    private String type; // e.g. "LIKE", "COMMENT", "FOLLOW"

    @Column(length = 1000)
    private String message; // notification message

    @Column(name = "is_read")
    private boolean read = false; // status: read/unread

    private LocalDateTime createdAt = LocalDateTime.now();
}
