package com.sin.wschatapp.services;

import com.sin.wschatapp.chat.ChatMessage;
import com.sin.wschatapp.repositories.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getReceiverId(), true)
                .orElseThrow(RuntimeException::new);
        chatMessage.setChatId(chatId);
        return chatMessage;
    }
}
