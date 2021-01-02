package ru.micron.dao;

import ru.micron.model.Film;

import java.util.List;

public interface FilmDAO {
    List<Film> allFilms(int id);
    void add(Film film);
    void delete(Film film);
    void edit(Film film);
    Film getById(int id);
}
