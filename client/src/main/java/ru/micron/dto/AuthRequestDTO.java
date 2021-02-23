package ru.micron.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthRequestDTO {
    private String username;
    private String password;
}
