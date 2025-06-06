package com.user_service.service;

import com.user_service.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserService {
User registerUser(User user);

    Optional<User> getUserDetails(Long id);

}
