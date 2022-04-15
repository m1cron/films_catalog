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
public class FilmDto {
  private Long id;
  private String title;
  private Integer year;
  private String genre;
  private Boolean watched;
  private List<ActorDto> actors;
}
