package com.snapshare.controller;

import com.snapshare.security.UserPrincipal;
import com.snapshare.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/post/{postId}/toggle")
    public ResponseEntity<?> toggleLike(@PathVariable Long postId,
                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        boolean liked = likeService.toggleLike(postId, userPrincipal.getUsername());
        return ResponseEntity.ok(new LikeResponse(liked, likeService.getLikesCount(postId)));
    }

    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Integer> getLikesCount(@PathVariable Long postId) {
        int count = likeService.getLikesCount(postId);
        return ResponseEntity.ok(count);
    }

    // Inner class for response
    public static class LikeResponse {
        private boolean liked;
        private int count;

        public LikeResponse(boolean liked, int count) {
            this.liked = liked;
            this.count = count;
        }

        public boolean isLiked() { return liked; }
        public void setLiked(boolean liked) { this.liked = liked; }

        public int getCount() { return count; }
        public void setCount(int count) { this.count = count; }
    }
}
