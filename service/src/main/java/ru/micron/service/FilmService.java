package ru.micron.service;

import ru.micron.model.Film;

import java.util.List;

public interface FilmService {

  Film findById(Long id);

  List<Film> findAll();

  void save(Film object);

  void deleteById(Long id);
}