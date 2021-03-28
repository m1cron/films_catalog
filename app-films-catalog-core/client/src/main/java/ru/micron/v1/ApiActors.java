package ru.micron.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.micron.dto.ActorDto;
import ru.micron.paths.ApiPathActors;

import javax.validation.constraints.Min;
import java.util.List;

public interface ApiActors {

  @GetMapping(value = ApiPathActors.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  List<ActorDto> getActors();

  @GetMapping(value = ApiPathActors.GET_ACTOR_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ActorDto getActorById(
      @PathVariable(value = "id") @Min(1) long id
  );
}