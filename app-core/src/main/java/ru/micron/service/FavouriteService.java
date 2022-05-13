package ru.micron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.micron.dto.FavouriteDto;
import ru.micron.persistence.repository.FilmRepository;
import ru.micron.persistence.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FavouriteService {

  private final UserRepository userRepository;
  private final FilmRepository filmRepository;

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
