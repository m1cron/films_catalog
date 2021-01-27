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

    @GetMapping("/films")
    public List<Film> allFilms() {
        return filmService.allFilms();
    }

    @GetMapping(value = "/films/{id}")
    public Film getFilmById(@PathVariable int id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchFilmException(String.format("There is no film with ID = %d in Database", id));
        }
        return film;
    }

    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        filmService.add(film);
        return film;
    }

    @PutMapping("/films")
    public Film editFilm(@RequestBody Film film) {
        filmService.edit(film);
        return film;
    }

    @DeleteMapping("/films/{id}")
    public String deleteFilm(@PathVariable int id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchFilmException(String.format("There is no film with ID = %d in Database", id));
        }
        filmService.delete(film);
        return String.format("Film with ID = %d was deleted", id);
    }

}
