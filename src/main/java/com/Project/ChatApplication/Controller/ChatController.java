package com.Project.ChatApplication.Controller;

import com.Project.ChatApplication.Service.ChatPublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatPublisher chatPublisher;

    public ChatController(ChatPublisher chatPublisher) {
        this.chatPublisher = chatPublisher;
    }

    @MessageMapping("/chat")
    public void handleChat(String message) {

        chatPublisher.publish("chatroom", message); // send messages to Redis
    }
}