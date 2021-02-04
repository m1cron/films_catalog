package ru.micron.service;

import ru.micron.model.Film;

import java.util.List;

public interface FilmService {

    Film getFilm(Long id);

    List<Film> getAllFilms();

    void saveFilm(Film object);

    void deleteFilm(Film object);

}