package ru.micron.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
}
