package ru.micron.rest.v1;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.feign.FilmsCatalogFeignClient;
import ru.micron.rest.v1.dto.FilmDto;
import ru.micron.rest.v1.interfaces.ApiFilm;

@RestController
@RequiredArgsConstructor
public class FilmsController implements ApiFilm {

  private final FilmsCatalogFeignClient filmsCatalogFeignClient;

  @Override
  public List<FilmDto> getFilms(String jwtToken) {
    return filmsCatalogFeignClient.getFilms(jwtToken);
  }

  @Override
  public FilmDto getFilmById(@Min(1) long id, String jwtToken) {
    return filmsCatalogFeignClient.getFilmById(id, jwtToken);
  }

  @Override
  public FilmDto addFilm(@NotBlank FilmDto film, String jwtToken) {
    return filmsCatalogFeignClient.addFilm(film, jwtToken);
  }

  @Override
  public FilmDto editFilm(FilmDto film, String jwtToken) {
    return filmsCatalogFeignClient.editFilm(film, jwtToken);
  }

  @Override
  public String deleteFilm(@Min(1) long id, String jwtToken) {
    return filmsCatalogFeignClient.deleteFilm(id, jwtToken);
  }

}
