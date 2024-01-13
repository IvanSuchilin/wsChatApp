package com.sin.wschatapp.services;

import com.sin.wschatapp.repositories.UserRepository;
import com.sin.wschatapp.user.Status;
import com.sin.wschatapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);

    }

    public void disconnect(User user) {
        var storedUser = userRepository.findById(user.getNickName())
                .orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }

}
