package ru.micron.rest;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.ActorDto;
import ru.micron.mapper.ActorMapper;
import ru.micron.service.ActorService;

@Validated
@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {

  private final ActorService actorService;
  private final ActorMapper actorMapper;

  @ApiOperation("Get all actors")
  @GetMapping
  public List<ActorDto> getActors() {
    return actorService.findAll().stream().map(actorMapper::toDto).collect(Collectors.toList());
  }

  @ApiOperation("Get actor by ID")
  @GetMapping("/{id}")
  public ActorDto getActorById(@PathVariable @Min(1) long id) {
    return actorMapper.toDto(actorService.findById(id));
  }
}
