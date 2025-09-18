package com.example.springapp.controller;

import com.example.springapp.model.Follow;
import com.example.springapp.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    // Get all follow relationships
    @GetMapping
    public List<Follow> getAllFollows() {
        return followService.getAllFollows();
    }

    // Get follow by ID
    @GetMapping("/{id}")
    public ResponseEntity<Follow> getFollowById(@PathVariable Long id) {
        return followService.getFollowById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all followers of a specific user (JPQL)
    @GetMapping("/followers/{userId}")
    public List<Follow> getFollowers(@PathVariable Long userId) {
        return followService.getFollowers(userId);
    }

    // Get all users a specific user is following (JPQL)
    @GetMapping("/following/{userId}")
    public List<Follow> getFollowing(@PathVariable Long userId) {
        return followService.getFollowing(userId);
    }

    // Follow a user
    @PostMapping
    public ResponseEntity<Follow> followUser(@RequestBody Follow follow) {
        Follow createdFollow = followService.followUser(follow);
        return ResponseEntity.ok(createdFollow);
    }

    // Unfollow a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long id) {
        followService.unfollowUser(id);
        return ResponseEntity.noContent().build();
    }

    // Count followers of a user (JPQL)
    @GetMapping("/followers/{userId}/count")
    public long countFollowers(@PathVariable Long userId) {
        return followService.countFollowers(userId);
    }

    // Count following of a user (JPQL)
    @GetMapping("/following/{userId}/count")
    public long countFollowing(@PathVariable Long userId) {
        return followService.countFollowing(userId);
    }
}
