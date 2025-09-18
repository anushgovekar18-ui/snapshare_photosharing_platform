package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Follow;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    // JPQL: Find all followers of a specific user
    @Query("SELECT f FROM Follow f WHERE f.following.id = :userId ORDER BY f.createdAt DESC")
    List<Follow> findFollowers(@Param("userId") Long userId);

    // JPQL: Find all users that a specific user is following
    @Query("SELECT f FROM Follow f WHERE f.follower.id = :userId ORDER BY f.createdAt DESC")
    List<Follow> findFollowing(@Param("userId") Long userId);

    // JPQL: Check if a user already follows another user
    @Query("SELECT f FROM Follow f WHERE f.follower.id = :followerId AND f.following.id = :followingId")
    Optional<Follow> findFollowRelation(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

    // JPQL: Count followers of a user
    @Query("SELECT COUNT(f) FROM Follow f WHERE f.following.id = :userId")
    long countFollowers(@Param("userId") Long userId);

    // JPQL: Count how many users someone is following
    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :userId")
    long countFollowing(@Param("userId") Long userId);
}
