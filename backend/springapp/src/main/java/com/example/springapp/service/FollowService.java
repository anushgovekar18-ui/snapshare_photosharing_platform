package com.example.springapp.service;

import com.example.springapp.model.Follow;
import com.example.springapp.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    // Get all follow relationships
    public List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    // Get follow relationship by ID
    public Optional<Follow> getFollowById(Long id) {
        return followRepository.findById(id);
    }

    // Get all followers of a specific user (JPQL)
    public List<Follow> getFollowers(Long userId) {
        return followRepository.findFollowers(userId);
    }

    // Get all users a specific user is following (JPQL)
    public List<Follow> getFollowing(Long userId) {
        return followRepository.findFollowing(userId);
    }

    // Follow a user
    public Follow followUser(Follow follow) {
        Optional<Follow> existingFollow = followRepository.findFollowRelation(
                follow.getFollower().getId(),
                follow.getFollowing().getId()
        );

        if (existingFollow.isPresent()) {
            throw new RuntimeException("User is already following this user");
        }

        return followRepository.save(follow);
    }

    // Unfollow a user by ID
    public void unfollowUser(Long id) {
        if (!followRepository.existsById(id)) {
            throw new RuntimeException("Follow relationship not found with id: " + id);
        }
        followRepository.deleteById(id);
    }

    // Count followers of a user (JPQL)
    public long countFollowers(Long userId) {
        return followRepository.countFollowers(userId);
    }

    // Count how many users a user is following (JPQL)
    public long countFollowing(Long userId) {
        return followRepository.countFollowing(userId);
    }
}
