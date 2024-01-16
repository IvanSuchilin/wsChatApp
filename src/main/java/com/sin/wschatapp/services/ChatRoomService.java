package com.sin.wschatapp.services;

import com.sin.wschatapp.chatroom.ChatRoom;
import com.sin.wschatapp.repositories.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatroomRepository chatroomRepository;

    public Optional<String> getChatRoomId(String senderId, String receiverId, boolean isNewRoomNeed) {
        return chatroomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (isNewRoomNeed) {
                        var chatId = createChatId(senderId, receiverId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String receiverId) {
        var chatId = String.format("%s_%s", senderId, receiverId);
        ChatRoom senderReceiver = ChatRoom.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .chatId(chatId)
                .build();

        ChatRoom receiverSender = ChatRoom.builder()
                .senderId(receiverId)
                .receiverId(senderId)
                .chatId(chatId)
                .build();
        chatroomRepository.save(senderReceiver);
        chatroomRepository.save(receiverSender);
        return chatId;
    }
}
