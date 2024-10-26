package com.example.Hangman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hangman.entity.Words;

public interface WordRepository extends JpaRepository<Words, Integer> {

}
