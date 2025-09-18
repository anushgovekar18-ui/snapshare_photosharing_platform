package com.snapshare.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponse {
    private Long id;
    private String username;
    private String userProfilePic;
    private String caption;
    private String imageUrl;
    private LocalDateTime createdAt;
    private int likesCount;
    private int commentsCount;
    private boolean likedByCurrentUser;
    private List<CommentResponse> comments;

    // Constructors
    public PostResponse() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getUserProfilePic() { return userProfilePic; }
    public void setUserProfilePic(String userProfilePic) { this.userProfilePic = userProfilePic; }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public int getLikesCount() { return likesCount; }
    public void setLikesCount(int likesCount) { this.likesCount = likesCount; }

    public int getCommentsCount() { return commentsCount; }
    public void setCommentsCount(int commentsCount) { this.commentsCount = commentsCount; }

    public boolean isLikedByCurrentUser() { return likedByCurrentUser; }
    public void setLikedByCurrentUser(boolean likedByCurrentUser) { this.likedByCurrentUser = likedByCurrentUser; }

    public List<CommentResponse> getComments() { return comments; }
    public void setComments(List<CommentResponse> comments) { this.comments = comments; }
}
