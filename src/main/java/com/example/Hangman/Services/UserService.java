package com.example.Hangman.Services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Hangman.DTOs.GameDto;
import com.example.Hangman.entity.User;
import com.example.Hangman.entity.Words;
import com.example.Hangman.repositories.UserRepository;
import com.example.Hangman.repositories.WordRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    GameService gameService;

    GameDto currentGame;

    @Transactional
    public User registUser(String username, String rawPassword) throws Exception {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new Exception("Username already exists");

        }
        User user = new User();
        user.username = username;
        user.password = (passwordEncoder.encode(rawPassword));

        startGame(username);
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String rawPassword) {
        Optional<User> userExist = userRepository.findByUsername(username);
        if (userExist.isPresent()) {
            User user = userExist.get();
            if (passwordEncoder.matches(rawPassword, user.password)) {
                startGame(username);
                return Optional.of(user);
            }
        }
        return Optional.empty();

    }

    public void startGame(String username) {
        Random randomizer = new Random();
        int wordId = randomizer.nextInt(1, 50);
        Words wordDetermin = wordRepository.findById(wordId).get();
        String gameWord = wordDetermin.Word;
        currentGame = new GameDto(gameWord, username);
        gameService.setCurrentGame(currentGame);
        System.out.println(currentGame);
    }
}
