package com.sin.wschatapp.controllers;

import com.sin.wschatapp.services.UserService;
import com.sin.wschatapp.user.Status;
import com.sin.wschatapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserControler {
    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUser(@Payload User user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnect(@Payload User user) {
        user.setStatus(Status.OFFLINE);
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    ResponseEntity <List<User>> getAllConnectedUsers (){
        return  ResponseEntity.ok(userService.findConnectedUsers());
    }


}
