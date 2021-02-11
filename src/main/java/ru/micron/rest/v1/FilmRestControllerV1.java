package ru.micron.rest.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.micron.dto.FilmDTO;
import ru.micron.exception.NoSuchEntityException;
import ru.micron.mapper.FilmMapper;
import ru.micron.model.Film;
import ru.micron.service.FilmService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmRestControllerV1 {

    private final FilmService filmService;
    private final FilmMapper filmMapper;

    @GetMapping()
    public List<FilmDTO> allFilms() {
        return filmService.findAll().stream().map(filmMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public FilmDTO getFilmById(@PathVariable Long id) {
        Film film = filmService.findById(id);
        if (film == null) {
            throw new NoSuchEntityException(String.format("There is no film with ID = %d in Database", id));
        }
        return filmMapper.toDto(film);
    }

    @PostMapping()
    public FilmDTO addFilm(@RequestBody Film film) {
        filmService.save(film);
        return filmMapper.toDto(film);
    }

    @PutMapping()
    public FilmDTO editFilm(@RequestBody Film film) {
        filmService.save(film);
        return filmMapper.toDto(film);
    }

    @DeleteMapping("/{id}")
    public String deleteFilm(@PathVariable Long id) {
        Film film = filmService.findById(id);
        if (film == null) {
            throw new NoSuchEntityException(String.format("There is no film with ID = %d in Database", id));
        }
        filmService.deleteById(id);
        return String.format("Film with ID = %d was deleted", id);
    }

}
