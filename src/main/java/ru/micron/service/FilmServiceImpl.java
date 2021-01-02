package ru.micron.service;

import ru.micron.dao.FilmDAO;
import ru.micron.dao.FilmDAOImpl;
import ru.micron.model.Film;

import java.util.List;

public class FilmServiceImpl implements FilmService {
    private final FilmDAO filmDAO = new FilmDAOImpl();

    @Override
    public List<Film> allFilms() {
        return filmDAO.allFilms();
    }

    @Override
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    public void edit(Film film) {
        filmDAO.delete(film);
    }

    @Override
    public Film getById(int id) {
        return filmDAO.getById(id);
    }
}
