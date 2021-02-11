package ru.micron.service;

import ru.micron.model.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> findAll();

    Actor findById(Long id);

    void save(Actor actor);

    void deleteById(Long id);
}
