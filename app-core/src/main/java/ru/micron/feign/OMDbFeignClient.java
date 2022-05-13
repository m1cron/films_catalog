package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.micron.dto.omdb.ApiResponseDto;
import ru.micron.dto.omdb.ApiSearchResponse;
import ru.micron.dto.omdb.FilmType;

@FeignClient(name = "filmFeignClient", url = "${feign.OMDbApi.url}")
public interface OMDbFeignClient {

  @GetMapping("/")
  ApiResponseDto receiveFilms(
      @RequestParam("i") String id,
      @RequestParam("type") FilmType type,
      @RequestParam("y") String year,
      @RequestParam("apikey") String apiKey);

  @GetMapping("/")
  ApiSearchResponse receiveFilmByTitle(
      @RequestParam("s") String title,
      @RequestParam("type") FilmType type,
      @RequestParam("y") String year,
      @RequestParam("page") Integer page,
      @RequestParam("apikey") String apiKey
  );
}
