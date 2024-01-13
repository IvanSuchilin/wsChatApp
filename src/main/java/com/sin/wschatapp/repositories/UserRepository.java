package com.sin.wschatapp.repositories;

import com.sin.wschatapp.user.Status;
import com.sin.wschatapp.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository <User, String>{
    List<User> findAllByStatus(Status status);
}
