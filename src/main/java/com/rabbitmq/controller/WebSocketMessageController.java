package com.rabbitmq.controller;

import com.rabbitmq.model.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketMessageController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    public SocketMessage sendMessage(@Payload SocketMessage socketMessage) {
        return socketMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    public SocketMessage newUser(@Payload SocketMessage socketMessage,
                                        SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", socketMessage.getSender());
        return socketMessage;
    }

}
