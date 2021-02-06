package ru.micron.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.micron.repository.FilmRepository;
import ru.micron.model.Film;
import ru.micron.service.FilmService;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

}
