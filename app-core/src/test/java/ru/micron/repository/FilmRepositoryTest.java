package ru.micron.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.micron.persistence.model.Film;
import ru.micron.persistence.repository.FilmRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FilmRepositoryTest {

  private final FilmRepository filmRepository;

  @Autowired
  public FilmRepositoryTest(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

/*  @Test
  @Sql("films.sql")
  void crudTest() {
    assertThat(
        Objects.requireNonNull(filmRepository.findById(1L).orElse(null)).getTitle()).isEqualTo(
        "Inception");

    Film film = new Film();
    film.setGenre("test");
    Actor actor = new Actor();
    actor.setFirstName("123");
    actor.setLastName("123");
    film.setActors(Stream.of(actor).collect(Collectors.toList()));
    filmRepository.save(film);
    assertThat(Objects.requireNonNull(
        Objects.requireNonNull(filmRepository.findById(4L).orElse(null)).getGenre())).isEqualTo(
        "test");

    Film inceptionFilm = filmRepository.findById(1L).orElse(null);
    if (inceptionFilm != null) {
      inceptionFilm.setTitle("TITLEAAA");
      filmRepository.save(inceptionFilm);
    }
    assertThat(
        Objects.requireNonNull(filmRepository.findById(1L).orElse(null)).getTitle()).isEqualTo(
        "TITLEAAA");
  }*/

}
