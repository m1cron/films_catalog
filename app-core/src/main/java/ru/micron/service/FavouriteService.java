package ru.micron.service;

import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.micron.dto.FavouriteDto;
import ru.micron.dto.PageableData;
import ru.micron.dto.PageableResponse;
import ru.micron.dto.omdb.FilmResponseDto;
import ru.micron.mapper.FilmMapper;
import ru.micron.persistence.repository.FilmRepository;
import ru.micron.persistence.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FavouriteService {

  private final UserRepository userRepository;
  private final FilmRepository filmRepository;
  private final UserService userService;
  private final FilmMapper filmMapper;

  public PageableResponse<FilmResponseDto> getUserFilmsByUserUuid(UUID uuid, int page, int size) {
    var user = userService.findById(uuid);
    var result = filmRepository.findFilmsByUsers(user, PageRequest.of(page - 1, size))
        .stream()
        .map(filmMapper::toDto).collect(Collectors.toList());
    return new PageableResponse<FilmResponseDto>()
        .setData(result)
        .setPageable(new PageableData(filmRepository.countFilmsByUsers(user), page, size, result.size()));
  }

  public Boolean checkIsFavourite(FavouriteDto dto) {
    return filmRepository.existsFilmByUsersAndImdbID(
        userService.findById(dto.getUserId()),
        dto.getImdbId()
    );
  }

  public void addFavourite(FavouriteDto dto) {
    var film = filmRepository.getById(dto.getImdbId());
    var user = userRepository.getById(dto.getUserId())
        .addToFavourite(film);
    userRepository.save(user);
  }

  public void removeFavourite(FavouriteDto dto) {
    var film = filmRepository.getById(dto.getImdbId());
    var user = userRepository.getById(dto.getUserId())
        .removeFromFavourite(film);
    userRepository.save(user);
  }
}
