package com.snapshare.controller;

import com.snapshare.dto.PostRequest;
import com.snapshare.dto.PostResponse;
import com.snapshare.security.UserPrincipal;
import com.snapshare.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest,
                                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {
        PostResponse createdPost = postService.createPost(postRequest, userPrincipal.getUsername());
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostResponse>> getFeed(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<PostResponse> posts = postService.getAllPosts(userPrincipal.getUsername());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponse>> getUserPosts(@PathVariable String username,
                                                          @AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<PostResponse> posts = postService.getUserPosts(username);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id,
                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        postService.deletePost(id, userPrincipal.getUsername());
        return ResponseEntity.ok().build();
    }
}
