package ru.micron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.micron.persistence.model.Film;
import ru.micron.persistence.repository.FilmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

  private final FilmRepository filmRepository;

  public Film findById(String id) {
    return filmRepository.findById(id).orElseThrow(() ->
        new RuntimeException(String.format("There is no film with ID = %s in Database", id)));
  }

  public List<Film> findAll() {
    return filmRepository.findAll();
  }

  public void save(Film film) {
    filmRepository.save(film);
  }

  public void deleteById(String id) {
    var entity = findById(id);
    filmRepository.delete(entity);
  }
}
