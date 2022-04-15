package ru.micron.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {}
