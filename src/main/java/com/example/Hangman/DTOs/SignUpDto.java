package com.example.Hangman.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpDto {

    @NotBlank
    public String username;
    @NotBlank
    public String password;
}
