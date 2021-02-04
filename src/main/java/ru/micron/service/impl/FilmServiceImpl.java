package ru.micron.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.micron.repository.FilmRepository;
import ru.micron.model.Film;
import ru.micron.service.FilmService;

import java.util.List;

@Service("filmServiceImpl")
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film getFilm(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void saveFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Film film) {
        filmRepository.delete(film);
    }

}
