package com.example.springapp.controller;

import com.example.springapp.model.Like;
import com.example.springapp.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Get all likes
    @GetMapping
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    // Get like by ID
    @GetMapping("/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable Long id) {
        return likeService.getLikeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all likes for a specific post
    @GetMapping("/post/{postId}")
    public List<Like> getLikesByPost(@PathVariable Long postId) {
        return likeService.getLikesByPost(postId);
    }

    // Get all likes by a specific user
    @GetMapping("/user/{userId}")
    public List<Like> getLikesByUser(@PathVariable Long userId) {
        return likeService.getLikesByUser(userId);
    }

    // Add a like
    @PostMapping
    public ResponseEntity<Like> addLike(@RequestBody Like like) {
        Like createdLike = likeService.addLike(like);
        return ResponseEntity.ok(createdLike);
    }

    // Remove a like
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeLike(@PathVariable Long id) {
        likeService.removeLike(id);
        return ResponseEntity.noContent().build();
    }

    // Count likes on a post
    @GetMapping("/post/{postId}/count")
    public long countLikesOnPost(@PathVariable Long postId) {
        return likeService.countLikesOnPost(postId);
    }
}
