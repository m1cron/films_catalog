package ru.micron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {}
