package com.sin.wschatapp.repositories;

import com.sin.wschatapp.chat.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository <ChatMessage, String> {
}
