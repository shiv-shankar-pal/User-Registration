package com.user_service.controller;

import com.user_service.entity.User;

import com.user_service.service.UserService;
import com.user_service.service.serviceimpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user,@RequestParam(value = "source",required = false,defaultValue = "web") String source) {
        long start=System.currentTimeMillis();

        if (user.getAge() <= 18 || !"France".equalsIgnoreCase(user.getCountry())) {
            return ResponseEntity.status(403).body("Only adults residing in France can register.");
        }
        User registeredUser = userService.registerUser(user);
        logger.info("Input: {}",user);
        long end =System.currentTimeMillis();
        logger.info("Processing time: {} ms",(end - start));
        return ResponseEntity.ok(registeredUser);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean includeEmail) {
        logger.info("Retriving user with id:",id);
        Optional<User> user = userService.getUserDetails(id);
        if (user.isPresent()) {
            if (!includeEmail) {
                user.get().setEmail(null);
            }
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

}
