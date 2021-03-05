package ru.micron.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.micron.model.Actor;
import ru.micron.repository.ActorRepository;
import ru.micron.service.ActorService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

  private final ActorRepository actorRepository;

  @Override
  public List<Actor> findAll() {
    List<Actor> result = actorRepository.findAll().stream().sorted().collect(Collectors.toList());
    log.info("IN findAll - actors found, length: {}", result.size());
    return result;
  }

  @Override
  public Actor findById(Long id) {
    Actor result = actorRepository.findById(id).orElse(null);
    if (result == null) {
      log.warn("IN findById - no actor found by id: {}", id);
    }
    log.info("IN findById - actor found by id: {}", id);
    return result;
  }

  @Override
  public void save(Actor actor) {
    actorRepository.save(actor);
    log.info("IN save - actor successfully saved");
  }

  @Override
  public void deleteById(Long id) {
    actorRepository.deleteById(id);
    log.info("IN deleteById - actor with id: {} successfully deleted", id);
  }
}
