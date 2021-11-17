package ru.micron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.micron.model.Film;
import ru.micron.repository.FilmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

  private final FilmRepository filmRepository;

  public Film findById(Long id) {
    return filmRepository.findById(id).orElse(null);
  }

  public List<Film> findAll() {
    return filmRepository.findAll();
  }

  public void save(Film film) {
    filmRepository.save(film);
  }

  public void deleteById(Long id) {
    filmRepository.deleteById(id);
  }
}
