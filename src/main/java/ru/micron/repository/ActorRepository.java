package ru.micron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.micron.model.Actor;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

/*    @Query("select from actor_films" +
            "group by * order by asc")
    List<Actor> findAll();*/

}
