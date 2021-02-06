package ru.micron.service;

import ru.micron.model.Film;

import java.util.List;

public interface FilmService {

    Film getById(Long id);

    List<Film> getAll();

    void save(Film object);

    void deleteById(Long id);

}