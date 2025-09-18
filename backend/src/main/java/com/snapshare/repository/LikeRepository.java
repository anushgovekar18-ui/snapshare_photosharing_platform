package com.snapshare.repository;

import com.snapshare.entity.Like;
import com.snapshare.entity.Post;
import com.snapshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
    int countByPost(Post post);
    boolean existsByPostAndUser(Post post, User user);
    void deleteByPostAndUser(Post post, User user);
}
