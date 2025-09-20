package com.Project.ChatApplication.Service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;

@Service
public class ChatSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatSubscriber(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // Decode body as UTF-8 to avoid garbled characters
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        messagingTemplate.convertAndSend("/topic/message", msg);
    }
}
