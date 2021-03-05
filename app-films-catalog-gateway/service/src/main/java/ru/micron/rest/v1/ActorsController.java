package ru.micron.rest.v1;

import java.util.List;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.feign.ActorsFeignClient;
import ru.micron.rest.v1.dto.ActorDto;
import ru.micron.rest.v1.interfaces.ApiActors;

@RestController
@RequiredArgsConstructor
public class ActorsController implements ApiActors {

  private final ActorsFeignClient actorsFeignClient;

  @Override
  public List<ActorDto> getActors(String jwtToken) {
    return actorsFeignClient.getActors(jwtToken);
  }

  @Override
  public ActorDto getActorById(@Min(1) long id, String jwtToken) {
    return actorsFeignClient.getActorById(id, jwtToken);
  }
}
