package com.snapshare.service;

import com.snapshare.entity.Like;
import com.snapshare.entity.Post;
import com.snapshare.entity.User;
import com.snapshare.repository.LikeRepository;
import com.snapshare.repository.PostRepository;
import com.snapshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeService {
    
    @Autowired
    private LikeRepository likeRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;

    public boolean toggleLike(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        boolean exists = likeRepository.existsByPostAndUser(post, user);
        
        if (exists) {
            likeRepository.deleteByPostAndUser(post, user);
            return false; // unliked
        } else {
            Like like = new Like(post, user);
            likeRepository.save(like);
            return true; // liked
        }
    }

    public int getLikesCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        
        return likeRepository.countByPost(post);
    }

    public boolean isLikedByUser(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        return likeRepository.existsByPostAndUser(post, user);
    }
}
