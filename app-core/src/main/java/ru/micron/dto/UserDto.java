package ru.micron.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto {

  private UUID uuid;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
}
