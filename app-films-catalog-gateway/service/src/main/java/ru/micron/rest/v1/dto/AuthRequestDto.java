package ru.micron.rest.v1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthRequestDto {
  private String username;
  private String password;
}
