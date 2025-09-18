package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Like;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    // Find all likes for a specific post (JPQL)
    @Query("SELECT l FROM Like l WHERE l.post.id = :postId")
    List<Like> findByPostId(Long postId);

    // Find all likes made by a specific user (JPQL)
    @Query("SELECT l FROM Like l WHERE l.user.id = :userId")
    List<Like> findByUserId(Long userId);

    // Check if a user already liked a post (JPQL)
    @Query("SELECT l FROM Like l WHERE l.user.id = :userId AND l.post.id = :postId")
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

    // Count likes on a post (JPQL)
    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.id = :postId")
    long countByPostId(Long postId);
}
