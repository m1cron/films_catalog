package ru.micron.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FilmDTO {
    private String title;
    private Integer year;
    private String genre;
    private Boolean watched;
    private List<ActorDTO> actors;
}
