package ru.micron.rest.v1.interfaces;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import ru.micron.rest.v1.dto.FilmDto;
import ru.micron.rest.v1.paths.ApiPathFilms;

public interface ApiFilm {

  @GetMapping(value = ApiPathFilms.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  List<FilmDto> getFilms(
      @RequestHeader("Authorization") String jwtToken
  );

  @GetMapping(value = ApiPathFilms.GET_FILM_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  FilmDto getFilmById(
      @PathVariable @Min(1) long id,
      @RequestHeader("Authorization") String jwtToken
  );

  @PostMapping(value = ApiPathFilms.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  FilmDto addFilm(
      @RequestBody @NotBlank FilmDto film,
      @RequestHeader("Authorization") String jwtToken
  );

  @PutMapping(value = ApiPathFilms.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  FilmDto editFilm(
      @RequestBody FilmDto film,
      @RequestHeader("Authorization") String jwtToken
  );

  @DeleteMapping(value = ApiPathFilms.DELETE_FILM_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  String deleteFilm(
      @PathVariable @Min(1) long id,
      @RequestHeader("Authorization") String jwtToken
  );
}
