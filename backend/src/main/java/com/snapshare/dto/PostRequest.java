package com.snapshare.dto;

import jakarta.validation.constraints.NotBlank;

public class PostRequest {
    @NotBlank
    private String caption;

    private String imageUrl;

    // Constructors
    public PostRequest() {}

    public PostRequest(String caption, String imageUrl) {
        this.caption = caption;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
