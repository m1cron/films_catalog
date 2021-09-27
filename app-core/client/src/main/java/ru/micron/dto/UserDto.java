package ru.micron.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private List<String> roles;
  private List<FilmDto> favoriteFilms;
}
