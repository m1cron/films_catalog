package ru.micron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {}
