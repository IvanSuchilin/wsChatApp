package com.sin.wschatapp.controllers;

import com.sin.wschatapp.chat.ChatMessage;
import com.sin.wschatapp.chat.ChatNotification;
import com.sin.wschatapp.services.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage storedMessage = chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(), "/queue/messages",
                ChatNotification.builder()
                        .id(storedMessage.getId())
                        .senderId(storedMessage.getSenderId())
                        .receiverId(storedMessage.getReceiverId())
                        .text(storedMessage.getText())
                        .build());
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> getAllChatMessages(@PathVariable String senderId, @PathVariable String receiverId) {
        return ResponseEntity.ok(chatMessageService.getAllChatMessages(senderId, receiverId));
    }
}
