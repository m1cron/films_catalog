package ru.micron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.micron.exception_handling.NoSuchFilmException;
import ru.micron.model.Film;
import ru.micron.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
public class RestFilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping()
    public List<Film> allFilms() {
        return filmService.allFilms();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('films:read')")
    public Film getFilmById(@PathVariable int id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchFilmException(String.format("There is no film with ID = %d in Database", id));
        }
        return film;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('films:write')")
    public Film addFilm(@RequestBody Film film) {
        filmService.add(film);
        return film;
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('films:write')")
    public Film editFilm(@RequestBody Film film) {
        filmService.edit(film);
        return film;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('films:write')")
    public String deleteFilm(@PathVariable int id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchFilmException(String.format("There is no film with ID = %d in Database", id));
        }
        filmService.delete(film);
        return String.format("Film with ID = %d was deleted", id);
    }

}
