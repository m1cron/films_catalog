package ru.micron.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {}
