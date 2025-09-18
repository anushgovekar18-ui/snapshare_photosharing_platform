package com.snapshare.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyNewPost(Object postData) {
        messagingTemplate.convertAndSend("/topic/posts", postData);
    }

    public void notifyNewComment(Long postId, Object commentData) {
        messagingTemplate.convertAndSend("/topic/posts/" + postId + "/comments", commentData);
    }

    public void notifyNewLike(Long postId, Object likeData) {
        messagingTemplate.convertAndSend("/topic/posts/" + postId + "/likes", likeData);
    }
}
