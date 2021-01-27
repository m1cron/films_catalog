package ru.micron.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.model.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
