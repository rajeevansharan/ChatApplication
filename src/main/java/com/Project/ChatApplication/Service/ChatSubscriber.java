package com.Project.ChatApplication.Service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatSubscriber implements MessageListener {

private final SimpMessagingTemplate messagingTemplate;

    public ChatSubscriber(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody());
        messagingTemplate.convertAndSend("/topic/message", msg);


    }
}
