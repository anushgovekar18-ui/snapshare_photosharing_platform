package com.example.springapp.service;

import com.example.springapp.model.Comment;
import com.example.springapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Get all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Get comment by ID
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Get all comments for a specific post (JPQL)
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // Get all comments made by a specific user (JPQL)
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    // Create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Update an existing comment
    public Comment updateComment(Long id, Comment updatedComment) {
        return commentRepository.findById(id)
                .map(existingComment -> {
                    existingComment.setContent(updatedComment.getContent());
                    return commentRepository.save(existingComment);
                })
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    // Delete a comment by ID
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }

    // Count comments on a post (JPQL)
    public long countCommentsOnPost(Long postId) {
        return commentRepository.countByPostId(postId);
    }
}
