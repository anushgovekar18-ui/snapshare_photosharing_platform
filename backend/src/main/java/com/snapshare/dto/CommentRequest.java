package com.snapshare.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {
    @NotBlank
    private String text;

    // Constructors
    public CommentRequest() {}

    public CommentRequest(String text) {
        this.text = text;
    }

    // Getters and Setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
