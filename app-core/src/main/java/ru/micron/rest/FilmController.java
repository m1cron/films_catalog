package ru.micron.rest;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.omdb.ApiResponseDto;
import ru.micron.mapper.FilmMapper;
import ru.micron.persistence.model.Film;
import ru.micron.service.FilmService;

@Validated
@RestController
@RequestMapping("/api/v1/film")
@RequiredArgsConstructor
public class FilmController {

  private final FilmService filmService;
  private final FilmMapper filmMapper;

  @GetMapping("/getAll")
  public BasicResponse<List<ApiResponseDto>> getFilms() {
    var result = filmService.findAll()
        .stream()
        .map(filmMapper::toDto)
        .collect(Collectors.toList());
    return new BasicResponse<List<ApiResponseDto>>().setData(result);
  }

  @GetMapping("/{id}")
  public BasicResponse<ApiResponseDto> getFilmById(@PathVariable String id) {
    Film film = filmService.findById(id);
    return new BasicResponse<ApiResponseDto>().setData(filmMapper.toDto(film));
  }
}
