package ru.micron.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ActorDto {
  private String firstName;
  private String lastName;
  private List<String> roles;
}
