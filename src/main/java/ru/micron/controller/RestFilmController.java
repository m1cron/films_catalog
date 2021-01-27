package ru.micron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.micron.exception_handling.NoSuchFilmException;
import ru.micron.model.Film;
import ru.micron.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestFilmController {

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public List<Film> allFilms() {
        return filmService.allFilms();
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.GET)
    public Film getFilmById(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchFilmException(String.format("There is no film with ID = %d in Database", id));
        }
        return film;
    }

}
