package ru.micron.service;

import ru.micron.model.Actor;

import java.util.List;

public interface ActorService {

    Actor findById(Long id);

    List<Actor> findAll();

    void save(Actor actor);

    void deleteById(Long id);
}
