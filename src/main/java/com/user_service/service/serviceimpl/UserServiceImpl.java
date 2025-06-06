package com.user_service.service.serviceimpl;

import com.user_service.entity.User;
import com.user_service.exception.UserNotFoundException;
import com.user_service.repository.UserRepository;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

//    public User registerUser(User user) {
//        logger.info("Service: registerUser method call");
//
//        try {
//            userRepository.save(user);
//        } catch (DataIntegrityViolationException e) {
//            throw new UserNotFoundException("Email is already taken");
//        }
//
//        return userRepository.save(user);
//
//
//    }

    public User registerUser(User user) {
        logger.info("Service: registerUser method call");

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserNotFoundException("Email is already taken");
        }
    }



    public Optional<User> getUserDetails(Long id) {
        logger.info("Service: getByUser  method call");
        return userRepository.findById(id);
    }


}
