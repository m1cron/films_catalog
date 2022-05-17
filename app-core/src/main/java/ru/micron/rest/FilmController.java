package ru.micron.rest;

import java.util.stream.Collectors;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.PageableData;
import ru.micron.dto.PageableResponse;
import ru.micron.dto.omdb.FilmResponseDto;
import ru.micron.mapper.FilmMapper;
import ru.micron.persistence.model.Film;
import ru.micron.service.FilmService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/film")
@RequiredArgsConstructor
public class FilmController {

  private final FilmService filmService;
  private final FilmMapper filmMapper;

  @GetMapping("/getAll")
  public BasicResponse<PageableResponse<FilmResponseDto>> getFilms(
      @RequestParam(defaultValue = "1") @Min(1) int pageNum,
      @RequestParam(defaultValue = "12") @Min(1) @Max(500) int pageSize
  ) {
    var result = filmService.findAllByPageableAnd(pageNum, pageSize)
        .stream()
        .map(filmMapper::toDto)
        .collect(Collectors.toList());
    return new BasicResponse<PageableResponse<FilmResponseDto>>()
        .setData(new PageableResponse<FilmResponseDto>()
            .setData(result)
            .setPageable(new PageableData(filmService.count(), pageNum, pageSize, result.size()))
        );
  }

  @GetMapping("/")
  public BasicResponse<FilmResponseDto> getFilmById(@RequestParam String imdbId) {
    Film film = filmService.findById(imdbId);
    return new BasicResponse<FilmResponseDto>().setData(filmMapper.toDto(film));
  }
}
