package com.Project.ChatApplication.Service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public ChatPublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String channel,String message) {
        redisTemplate.convertAndSend("chatroom", message);
    }

}

