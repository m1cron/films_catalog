package ru.micron.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.micron.exception.NoSuchEntityException;
import ru.micron.model.Film;
import ru.micron.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
public class FilmRestControllerV1 {

    private final FilmService filmService;

    @Autowired
    public FilmRestControllerV1(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping()
    public List<Film> allFilms() {
        return filmService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Film getFilmById(@PathVariable Long id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchEntityException(String.format("There is no film with ID = %d in Database", id));
        }
        return film;
    }

    @PostMapping()
    public Film addFilm(@RequestBody Film film) {
        filmService.save(film);
        return film;
    }

    @PutMapping()
    public Film editFilm(@RequestBody Film film) {
        filmService.save(film);
        return film;
    }

    @DeleteMapping("/{id}")
    public String deleteFilm(@PathVariable Long id) {
        Film film = filmService.getById(id);
        if (film == null) {
            throw new NoSuchEntityException(String.format("There is no film with ID = %d in Database", id));
        }
        filmService.deleteById(id);
        return String.format("Film with ID = %d was deleted", id);
    }

}
