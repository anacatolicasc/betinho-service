package com.pac6.betinho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pac6.betinho.model.User;
import com.pac6.betinho.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User registrationUser) {
        return userService.createUser(registrationUser.getEmail(), registrationUser.getPassword());
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        boolean loginSuccess = userService.checkLogin(loginUser.getEmail(), loginUser.getPassword());
        
        if (loginSuccess) {
            return ResponseEntity.ok("Login OK");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login falhou");
        }
    }
}
