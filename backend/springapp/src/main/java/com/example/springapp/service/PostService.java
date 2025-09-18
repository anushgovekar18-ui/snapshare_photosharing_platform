package com.example.springapp.service;

import com.example.springapp.model.Post;
import com.example.springapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    // Constructor injection
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get a post by ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Get all posts created by a specific user
    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findPostsByUser(userId);
    }

    // Search posts by keyword in caption
    public List<Post> searchPosts(String keyword) {
        return postRepository.searchPostsByCaption(keyword);
    }

    // Create a new post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Update an existing post
    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setCaption(updatedPost.getCaption());
                    existingPost.setImageUrl(updatedPost.getImageUrl());
                    existingPost.setUpdatedAt(updatedPost.getUpdatedAt() != null ? updatedPost.getUpdatedAt() : existingPost.getUpdatedAt());
                    return postRepository.save(existingPost);
                })
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    // Delete a post
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    // Count total posts by a user
    public long countPostsByUser(Long userId) {
        return postRepository.countPostsByUser(userId);
    }
}
