package ru.micron.rest;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.omdb.FilmResponseDto;
import ru.micron.mapper.FilmMapper;
import ru.micron.persistence.model.User;
import ru.micron.service.FilmService;
import ru.micron.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;
  private final FilmService filmService;
  private final FilmMapper filmMapper;

  @GetMapping("/user")
  public BasicResponse<List<User>> allUsers() {
    return new BasicResponse<List<User>>().setData(userService.findAll());
  }

  @GetMapping("/user/{id}")
  public BasicResponse<User> getUserById(@PathVariable UUID id) {
    return new BasicResponse<User>().setData(userService.findById(id));
  }

  @DeleteMapping("/user/{id}")
  public BasicResponse<Void> deleteUser(@PathVariable UUID id) {
    userService.deleteById(id);
    return new BasicResponse<>();
  }

  @DeleteMapping("/film/{imdbId}")
  public BasicResponse<Void> deleteFilm(@PathVariable String imdbId) {
    filmService.deleteById(imdbId);
    return new BasicResponse<>();
  }

  @PostMapping("/film")
  public BasicResponse<Void> addFilm(@RequestBody FilmResponseDto film) {
    filmService.save(filmMapper.toEntity(film));
    return new BasicResponse<>();
  }

  @PutMapping("/film")
  public BasicResponse<Void> editFilm(@RequestBody FilmResponseDto film) {
    filmService.save(filmMapper.toEntity(film));
    return new BasicResponse<>();
  }
}
