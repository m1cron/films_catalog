package ru.micron.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.FavouriteDto;
import ru.micron.service.FavouriteService;

@Validated
@RestController
@RequestMapping("/api/v1/favourite")
@RequiredArgsConstructor
public class FavouriteController {

  private final FavouriteService favouriteService;

  @PostMapping("/")
  public BasicResponse<Void> addFavourite(@RequestBody FavouriteDto dto) {
    favouriteService.addFavourite(dto);
    return new BasicResponse<>();
  }

  @DeleteMapping("/")
  public BasicResponse<Void> removeFromFavourite(@RequestBody FavouriteDto dto) {
    favouriteService.removeFavourite(dto);
    return new BasicResponse<>();
  }
}
