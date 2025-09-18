package com.snapshare.service;

import com.snapshare.dto.PostRequest;
import com.snapshare.dto.PostResponse;
import com.snapshare.entity.Post;
import com.snapshare.entity.User;
import com.snapshare.repository.CommentRepository;
import com.snapshare.repository.LikeRepository;
import com.snapshare.repository.PostRepository;
import com.snapshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private LikeRepository likeRepository;

    public PostResponse createPost(PostRequest postRequest, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        Post post = new Post(user, postRequest.getCaption(), postRequest.getImageUrl());
        Post savedPost = postRepository.save(post);

        return mapToPostResponse(savedPost, username);
    }

    public List<PostResponse> getAllPosts(String username) {
        List<Post> posts = postRepository.findAllOrderByCreatedAtDesc();
        return posts.stream()
                .map(post -> mapToPostResponse(post, username))
                .collect(Collectors.toList());
    }

    public List<PostResponse> getUserPosts(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        List<Post> posts = postRepository.findByUserOrderByCreatedAtDesc(user);
        return posts.stream()
                .map(post -> mapToPostResponse(post, username))
                .collect(Collectors.toList());
    }

    public void deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only delete your own posts");
        }

        postRepository.delete(post);
    }

    private PostResponse mapToPostResponse(Post post, String currentUsername) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setUsername(post.getUser().getUsername());
        response.setUserProfilePic(post.getUser().getProfilePic());
        response.setCaption(post.getCaption());
        response.setImageUrl(post.getImageUrl());
        response.setCreatedAt(post.getCreatedAt());
        
        response.setLikesCount(likeRepository.countByPost(post));
        response.setCommentsCount(commentRepository.countByPost(post));
        
        if (currentUsername != null) {
            User currentUser = userRepository.findByUsername(currentUsername).orElse(null);
            if (currentUser != null) {
                response.setLikedByCurrentUser(likeRepository.existsByPostAndUser(post, currentUser));
            }
        }
        
        return response;
    }
}
