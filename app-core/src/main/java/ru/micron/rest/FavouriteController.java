package ru.micron.rest;

import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.FavouriteDto;
import ru.micron.dto.PageableResponse;
import ru.micron.dto.omdb.FilmResponseDto;
import ru.micron.service.FavouriteService;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/favourite")
@RequiredArgsConstructor
public class FavouriteController {

  private final FavouriteService favouriteService;

  @GetMapping
  public BasicResponse<PageableResponse<FilmResponseDto>> getFavouriteByUserUuid(
      @RequestParam UUID uuid,
      @RequestParam(defaultValue = "1") @Min(1) int pageNum,
      @RequestParam(defaultValue = "12") @Min(1) @Max(500) int pageSize
  ) {
    log.debug("getFavouriteByUserUuid uuid:{} pageNum:{} pageSize:{}", uuid, pageNum, pageSize);
    return new BasicResponse<PageableResponse<FilmResponseDto>>()
        .setData(favouriteService.getUserFilmsByUserUuid(uuid, pageNum, pageSize));
  }

  @PostMapping("/checkIsFavourite")
  public BasicResponse<Boolean> checkIsFavourite(@RequestBody FavouriteDto dto) {
    log.debug("checkIsFavourite {}", dto);
    return new BasicResponse<Boolean>()
        .setData(favouriteService.checkIsFavourite(dto));
  }

  @PostMapping("/add")
  public BasicResponse<Void> addFavourite(@RequestBody FavouriteDto dto) {
    log.debug("addFavourite {}", dto);
    favouriteService.addFavourite(dto);
    return new BasicResponse<>();
  }

  @PostMapping("/remove")
  public BasicResponse<Void> removeFromFavourite(@RequestBody FavouriteDto dto) {
    log.debug("removeFromFavourite {}", dto);
    favouriteService.removeFavourite(dto);
    return new BasicResponse<>();
  }
}
