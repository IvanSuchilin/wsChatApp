package com.sin.wschatapp.repositories;

import com.sin.wschatapp.chatroom.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatroomRepository extends MongoRepository <ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String reciverId);
}
