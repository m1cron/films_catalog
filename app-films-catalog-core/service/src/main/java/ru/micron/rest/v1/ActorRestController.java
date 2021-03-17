package ru.micron.rest.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.ApiActors;
import ru.micron.dto.ActorDto;
import ru.micron.mapper.ActorMapper;
import ru.micron.service.ActorService;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
public class ActorRestController implements ApiActors {

  private final ActorService actorService;
  private final ActorMapper actorMapper;

  @ApiOperation("Get all actors")
  @Override
  public List<ActorDto> getActors() {
    return actorService.findAll().stream().map(actorMapper::toDto).collect(Collectors.toList());
  }

  @ApiOperation("Get actor by ID")
  @Override
  public ActorDto getActorById(
      @ApiParam("actor id") @Min(1) long id
  ) {
    return actorMapper.toDto(actorService.findById(id));
  }
}
