package ru.micron.persistence.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.Film;
import ru.micron.persistence.model.User;

public interface FilmRepository extends JpaRepository<Film, String> {

  List<Film> findAllByOrderByImdbRatingDesc();
  List<Film> findAllByTitle(String title);
  List<Film> findAllByYear(String year);
  List<Film> findAllByYearLessThanEqual(String year);
  List<Film> findAllByYearGreaterThanEqual(String year);

  Page<Film> findFilmsByUsers(User user, Pageable pageable);

  Long countFilmsByUsers(User user);

  boolean existsFilmByUsersAndImdbID(User user, String imdbID);
}
