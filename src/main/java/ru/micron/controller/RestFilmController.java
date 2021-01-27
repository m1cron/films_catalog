package ru.micron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

}
