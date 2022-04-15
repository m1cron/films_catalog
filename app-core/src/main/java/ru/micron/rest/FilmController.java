package ru.micron.rest;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.FilmDto;
import ru.micron.mapper.FilmMapper;
import ru.micron.persistence.model.Film;
import ru.micron.service.FilmService;

@Validated
@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmController {

  private final FilmService filmService;
  private final FilmMapper filmMapper;

  @ApiOperation("Get all films")
  @GetMapping
  public List<FilmDto> getFilms() {
    var res =
        filmService.findAll();
    return res.stream().map(filmMapper::toDto).collect(Collectors.toList());
  }

  @ApiOperation("Get films by ID")
  @GetMapping("/{id}")
  public FilmDto getFilmById(@PathVariable @Min(1) long id) {
    Film film = filmService.findById(id);
    if (film == null) {
      throw new RuntimeException(
          String.format("There is no film with ID = %d in Database", id));
    }
    return filmMapper.toDto(film);
  }

  @ApiOperation("Add film")
  @PostMapping
  public FilmDto addFilm(@RequestBody FilmDto film) {
    filmService.save(filmMapper.toEntity(film));
    return film;
  }

  @ApiOperation("Edit film")
  @PutMapping
  public FilmDto editFilm(@RequestBody FilmDto film) {
    filmService.save(filmMapper.toEntity(film));
    return film;
  }

  @ApiOperation("Delete film")
  @DeleteMapping("/{id}")
  public String deleteFilm(@PathVariable @Min(1) long id) {
    Film film = filmService.findById(id);
    if (film == null) {
      throw new RuntimeException(
          String.format("There is no film with ID = %d in Database", id));
    }
    filmService.deleteById(id);
    return String.format("Film with ID = %d was deleted", id);
  }
}
