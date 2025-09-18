package com.example.springapp.service;

import com.example.springapp.model.Like;
import com.example.springapp.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Get all likes
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    // Get like by ID
    public Optional<Like> getLikeById(Long id) {
        return likeRepository.findById(id);
    }

    // Get all likes for a specific post
    public List<Like> getLikesByPost(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    // Get all likes made by a specific user
    public List<Like> getLikesByUser(Long userId) {
        return likeRepository.findByUserId(userId);
    }

    // Add a like (prevents duplicate)
    public Like addLike(Like like) {
        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(
                like.getUser().getId(), like.getPost().getId()
        );

        if (existingLike.isPresent()) {
            throw new RuntimeException("User already liked this post");
        }

        return likeRepository.save(like);
    }

    // Remove a like by ID
    public void removeLike(Long id) {
        if (!likeRepository.existsById(id)) {
            throw new RuntimeException("Like not found with id: " + id);
        }
        likeRepository.deleteById(id);
    }

    // Count total likes on a post
    public long countLikesOnPost(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
