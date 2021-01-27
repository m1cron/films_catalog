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

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    public Film addFilm(@RequestBody Film film) {
        filmService.add(film);
        return film;
    }

    @RequestMapping(value = "/films", method = RequestMethod.PUT)
    public Film editFilm(@RequestBody Film film) {
        filmService.edit(film);
        return film;
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.DELETE)
    public String deleteFilm(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchFilmException(String.format("There is no film with ID = %d in Database", id));
        }
        filmService.delete(film);
        return String.format("Film with ID = %d was deleted", id);
    }

}
