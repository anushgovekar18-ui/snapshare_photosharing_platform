package com.snapshare.dto;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String username;
    private String userProfilePic;
    private String text;
    private LocalDateTime createdAt;

    // Constructors
    public CommentResponse() {}

    public CommentResponse(Long id, String username, String userProfilePic, String text, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.userProfilePic = userProfilePic;
        this.text = text;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getUserProfilePic() { return userProfilePic; }
    public void setUserProfilePic(String userProfilePic) { this.userProfilePic = userProfilePic; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
