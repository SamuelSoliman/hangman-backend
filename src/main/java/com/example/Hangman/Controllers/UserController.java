package com.example.Hangman.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hangman.DTOs.LoginDto;
import com.example.Hangman.DTOs.SignUpDto;
import com.example.Hangman.Services.UserService;
import com.example.Hangman.entity.User;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        Optional<User> user = service.login(dto.username, dto.password);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("successful login");
        }

    }

    @PostMapping("auth/register")
    public ResponseEntity<?> registUser(@RequestBody SignUpDto dto) {
        try {
            service.registUser(dto.username, dto.password);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("successful registration");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong sign up information or username alerady exists");
        }

    }

}
