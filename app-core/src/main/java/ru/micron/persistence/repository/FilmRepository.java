package ru.micron.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.Film;

public interface FilmRepository extends JpaRepository<Film, String> {

  List<Film> findAllByTitle(String title);

  List<Film> findAllByYear(String year);

  List<Film> findAllByYearLessThanEqual(String year);

  List<Film> findAllByYearGreaterThanEqual(String year);
}
