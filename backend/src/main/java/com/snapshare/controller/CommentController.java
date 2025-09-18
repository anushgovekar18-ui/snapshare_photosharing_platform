package com.snapshare.controller;

import com.snapshare.dto.CommentRequest;
import com.snapshare.dto.CommentResponse;
import com.snapshare.security.UserPrincipal;
import com.snapshare.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long postId,
                                                     @Valid @RequestBody CommentRequest commentRequest,
                                                     @AuthenticationPrincipal UserPrincipal userPrincipal) {
        CommentResponse comment = commentService.addComment(postId, commentRequest, userPrincipal.getUsername());
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        List<CommentResponse> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }
}
