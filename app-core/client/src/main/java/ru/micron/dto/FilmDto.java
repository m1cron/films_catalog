package ru.micron.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FilmDto {
  private String title;
  private Integer year;
  private String genre;
  private Boolean watched;
  private List<ActorDto> actors;
}
