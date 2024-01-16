package com.sin.wschatapp.repositories;

import com.sin.wschatapp.chat.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository <ChatMessage, String> {
    List<ChatMessage> findAllBySenderIdAndSenderId (String senderId, String receiverId);

    List<ChatMessage> findByChatId(String chatId);
}
