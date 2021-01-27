package ru.micron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.micron.dao.FilmRepository;
import ru.micron.model.Film;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Film> allFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void add(Film film) {
        filmRepository.save(film);
    }

    @Override
    public void delete(Film film) {
        filmRepository.delete(film);
    }

    @Override
    public void edit(Film film) {
        filmRepository.save(film);
    }

    @Override
    public Film getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
}
