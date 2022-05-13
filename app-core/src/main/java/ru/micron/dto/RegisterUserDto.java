package ru.micron.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RegisterUserDto {

  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
}
