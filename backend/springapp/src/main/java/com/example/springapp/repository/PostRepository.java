package com.example.springapp.repository;

import com.example.springapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Find all posts by a specific user (JPQL)
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.createdAt DESC")
    List<Post> findPostsByUser(@Param("userId") Long userId);

    // Find posts containing a keyword in the caption (JPQL)
    @Query("SELECT p FROM Post p WHERE LOWER(p.caption) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Post> searchPostsByCaption(@Param("keyword") String keyword);

    // Count posts by user (JPQL)
    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    long countPostsByUser(@Param("userId") Long userId);
}
