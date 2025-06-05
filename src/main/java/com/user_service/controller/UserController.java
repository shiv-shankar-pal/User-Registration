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







//    @PostMapping
//   ResponseEntity<User> createUser(@Valid @RequestBody User user){
//        logger.info("Controller create method call");
//
//        User user1=  userService.createUser(user);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
//        }
//
//        return new ResponseEntity<>(user, HttpStatus.OK); // 200 OK
//
//    }
//    @PutMapping("/{id}")
//    User updateUser(@PathVariable Long id, @Valid @RequestBody User user){
//        logger.info("Controller put method call");
//        return  userService.updateUser(id,user);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        logger.info("Controller getUserById method call");
//        User user = userService.getUserById(id);
//
//        if (user == null || user.getId() == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
//        }
//
//        return new ResponseEntity<>(user, HttpStatus.OK); // 200 OK
//    }
//
//
//
//    @GetMapping
//    List<User>getAllUser(){
//
//        logger.trace("Log level: TRACE");
//        logger.debug("Log level: DEBUG");
//        logger.info("Log level: INFO");
//        logger.warn("Log level: WARN");
//        logger.error("Log level: ERROR");
//
//
//        return userService.getAllUser();
//    }
//
//    @DeleteMapping
//    void deleteUser(@PathVariable Long id){
//        logger.info("Controller deleteUser method call");
//       userService.deleteUser(id);
//    }
}
