package ru.micron.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.micron.model.Actor;
import ru.micron.repository.ActorRepository;

@Service
@RequiredArgsConstructor
public class ActorService {

  private final ActorRepository actorRepository;

  public List<Actor> findAll() {
    return actorRepository.findAll().stream().sorted().collect(Collectors.toList());
  }

  public Actor findById(Long id) {
    var result = actorRepository.findById(id).orElse(null);
    if (result == null) {
      throw new RuntimeException("no actor found by id: " + id);
    }
    return result;
  }

  public void save(Actor actor) {
    actorRepository.save(actor);
  }

  public void deleteById(Long id) {
    actorRepository.deleteById(id);
  }
}
