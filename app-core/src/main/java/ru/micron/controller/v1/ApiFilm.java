package ru.micron.controller.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.micron.dto.FilmDto;
import ru.micron.paths.ApiPathFilms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface ApiFilm {

  @GetMapping(value = ApiPathFilms.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  List<FilmDto> getFilms();

  @GetMapping(value = ApiPathFilms.GET_FILM_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  FilmDto getFilmById(
      @PathVariable @Min(1) long id
  );

  @PostMapping(value = ApiPathFilms.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  FilmDto addFilm(
      @RequestBody @NotBlank FilmDto film
  );

  @PutMapping(value = ApiPathFilms.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  FilmDto editFilm(
      @RequestBody FilmDto film
  );

  @DeleteMapping(value = ApiPathFilms.DELETE_FILM_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  String deleteFilm(
      @PathVariable @Min(1) long id
  );
}
