package com.example.Hangman.DTOs;

public class GameDto {

    String word;
    int tries;
    char[] wordInWork;
    String Username;
    boolean wordIsCompleted;

    public GameDto(String word, String username) {
        this.word = word;
        this.tries = 7;
        this.wordInWork = new char[this.word.length()];
        this.Username = username;
        this.wordIsCompleted = false;

    }

    public String getWord() {
        return this.word;
    }

    public String getWordInWork() {
        String str = new String(wordInWork);
        return str;
    }

    public void addCharWordinWork(int idx, char letter) {
        wordInWork[idx] = letter;

    }

    public int getTries() {
        return this.tries;
    }

    public void minusLife() {
        tries = tries - 1;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setWordIsCompleted() {
        this.wordIsCompleted = true;
    }

    public boolean getWordIsCompleted() {
        return wordIsCompleted;
    }

}
