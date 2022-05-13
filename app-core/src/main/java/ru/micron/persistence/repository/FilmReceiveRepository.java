package ru.micron.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.micron.persistence.model.FilmReceive;

@Repository
public interface FilmReceiveRepository extends JpaRepository<FilmReceive, String> {

  List<FilmReceive> findFilmReceivesByIsReceivedFalse();
}
