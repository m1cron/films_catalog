package ru.micron.service;

import ru.micron.dao.FilmDAO;
import ru.micron.dao.FilmDAOImpl;
import ru.micron.model.Film;

import javax.transaction.Transactional;
import java.util.List;

public class FilmServiceImpl implements FilmService {
    private final FilmDAO filmDAO = new FilmDAOImpl();

    @Override
    @Transactional
    public List<Film> allFilms() {
        return filmDAO.allFilms();
    }

    @Override
    @Transactional
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    @Transactional
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    @Transactional
    public void edit(Film film) {
        filmDAO.delete(film);
    }

    @Override
    @Transactional
    public Film getById(int id) {
        return filmDAO.getById(id);
    }
}
