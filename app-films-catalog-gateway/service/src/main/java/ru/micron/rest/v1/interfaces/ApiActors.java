package ru.micron.rest.v1.interfaces;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Min;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.micron.rest.v1.dto.ActorDto;
import ru.micron.rest.v1.paths.ApiPathActors;

public interface ApiActors {

  @GetMapping(value = ApiPathActors.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  List<ActorDto> getActors(
      @RequestHeader("Authorization") String jwtToken
  );

  @GetMapping(value = ApiPathActors.GET_ACTOR_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ActorDto getActorById(
      @PathVariable(value = "id") @Min(1) long id,
      @RequestHeader("Authorization") String jwtToken
  );
}