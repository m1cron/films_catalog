package ru.micron.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.micron.model.Film;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FilmRepositoryTest {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Autowired
    public FilmRepositoryTest(FilmRepository filmRepository, UserRepository userRepository) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @Test
    @Sql("films.sql")
    void crudTest() {
        assertThat(Objects.requireNonNull(filmRepository.findById(1L).orElse(null)).getTitle()).isEqualTo("Inception");

        Film film = new Film();
        film.setGenre("test");
        film.setUsers(Stream.of(userRepository.findUserByUsername("user")).collect(Collectors.toSet()));
        filmRepository.save(film);
        assertThat(Objects.requireNonNull(Objects.requireNonNull(filmRepository.findById(4L).orElse(null)).getGenre())).isEqualTo("test");

        Film inceptionFilm = filmRepository.findById(1L).orElse(null);
        if (inceptionFilm != null) {
            inceptionFilm.setTitle("TITLEAAA");
            filmRepository.save(inceptionFilm);
        }
        assertThat(Objects.requireNonNull(filmRepository.findById(1L).orElse(null)).getTitle()).isEqualTo("TITLEAAA");
    }

}
