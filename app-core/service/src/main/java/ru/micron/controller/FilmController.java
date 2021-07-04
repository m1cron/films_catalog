package ru.micron.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.micron.v1.ApiFilm;
import ru.micron.dto.FilmDto;
import ru.micron.mapper.FilmMapper;
import ru.micron.model.Film;
import ru.micron.service.FilmService;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
public class FilmController implements ApiFilm {

  private final FilmService filmService;
  private final FilmMapper filmMapper;

  @ApiOperation("Get all films")
  @Override
  public List<FilmDto> getFilms() {
    return filmService.findAll().stream().map(filmMapper::toDto).collect(Collectors.toList());
  }

  @ApiOperation("Get films by ID")
  @Override
  public FilmDto getFilmById(
      @Min(1) long id
  ) {
    Film film = filmService.findById(id);
    if (film == null) {
      throw new RuntimeException(
          String.format("There is no film with ID = %d in Database", id));
    }
    return filmMapper.toDto(film);
  }

  @ApiOperation("Add film")
  @Override
  public FilmDto addFilm(
      FilmDto film
  ) {
    filmService.save(filmMapper.toEntity(film));
    return film;
  }

  @ApiOperation("Edit film")
  @Override
  public FilmDto editFilm(
      FilmDto film
  ) {
    filmService.save(filmMapper.toEntity(film));
    return film;
  }

  @ApiOperation("Delete film")
  @Override
  public String deleteFilm(
      @Min(1) long id
  ) {
    Film film = filmService.findById(id);
    if (film == null) {
      throw new RuntimeException(
          String.format("There is no film with ID = %d in Database", id));
    }
    filmService.deleteById(id);
    return String.format("Film with ID = %d was deleted", id);
  }
}
