package com.example.Hangman.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Hangman.DTOs.GameDto;
import com.example.Hangman.repositories.WordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class GameService {

    // String[] words = {"apple", "banana", "cherry"};
    @Autowired
    WordRepository repository;
    GameDto currentGame;

    public void setCurrentGame(GameDto currentGame) {
        this.currentGame = currentGame;
    }

    // public void startGame() {
    //     Random randomizer = new Random();
    //     int wordId = randomizer.nextInt(1, 50);
    //     Words wordDetermin = repository.findById(wordId).get();
    //     String gameWord = wordDetermin.Word;
    //     currentGame = new GameDto(gameWord);
    //     System.out.println(currentGame);
    // }
    public ObjectNode checkLetter(char letter) {

        ArrayList<Integer> arrIndx = new ArrayList<>();
        Boolean charExists = false;
        // boolean wordIsCompleted = false;
        char charHolder = 'a';
        String currentGameWord = currentGame.getWord();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode charInfo = mapper.createObjectNode();
        for (int i = 0; i < currentGameWord.length(); i++) {
            if (currentGameWord.charAt(i) == letter) {
                arrIndx.add(i);
                charExists = true;
                charHolder = letter;

            }

        }

        if (charExists == true) {
            for (int i = 0; i < arrIndx.size(); i++) {
                currentGame.addCharWordinWork(arrIndx.get(i), charHolder);
            }
        }
        if (currentGame.getWordInWork().equals(currentGameWord)) {
            // wordIsCompleted = true;
            currentGame.setWordIsCompleted();
        }
        if (charExists == false) {
            currentGame.minusLife();
        }
        charInfo.put("CharExists", charExists);
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Integer x : arrIndx) {
            arrayNode.add(x);
        }
        charInfo.set("ArrayOfIndexes", arrayNode);
        charInfo.put("TriesLeft", currentGame.getTries());
        charInfo.put("WordIsCompleted", currentGame.getWordIsCompleted());
        charInfo.put("WordProgress", currentGame.getWordInWork());
        charInfo.put("CurrentWord", currentGame.getWord());
        charInfo.put("Username", currentGame.getUsername());
        return charInfo;

    }

}
