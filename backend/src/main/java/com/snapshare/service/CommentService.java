package com.snapshare.service;

import com.snapshare.dto.CommentRequest;
import com.snapshare.dto.CommentResponse;
import com.snapshare.entity.Comment;
import com.snapshare.entity.Post;
import com.snapshare.entity.User;
import com.snapshare.repository.CommentRepository;
import com.snapshare.repository.PostRepository;
import com.snapshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;

    public CommentResponse addComment(Long postId, CommentRequest commentRequest, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        Comment comment = new Comment(post, user, commentRequest.getText());
        Comment savedComment = commentRepository.save(comment);

        return mapToCommentResponse(savedComment);
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        List<Comment> comments = commentRepository.findByPostOrderByCreatedAtAsc(post);
        return comments.stream()
                .map(this::mapToCommentResponse)
                .collect(Collectors.toList());
    }

    private CommentResponse mapToCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getUsername(),
                comment.getUser().getProfilePic(),
                comment.getText(),
                comment.getCreatedAt()
        );
    }
}
