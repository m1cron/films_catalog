package ru.micron.rest;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String login;
    private String password;
}
