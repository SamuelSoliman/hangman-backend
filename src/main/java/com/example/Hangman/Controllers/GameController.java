package com.example.Hangman.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hangman.Services.GameService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class GameController {

    @Autowired
    GameService service;

    // @GetMapping("startgame")
    // public void startGame() {
    //     service.startGame();
    // }
    @PostMapping("checkletter")
    public ObjectNode checkLetter(@RequestBody char letter) {
        return service.checkLetter(letter);

    }

}
