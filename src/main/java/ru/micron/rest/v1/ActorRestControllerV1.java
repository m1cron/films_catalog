package ru.micron.rest.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.ActorDTO;
import ru.micron.mapper.ActorMapper;
import ru.micron.service.ActorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorRestControllerV1 {

    private final ActorService actorService;
    private final ActorMapper actorMapper;

    @GetMapping()
    public List<ActorDTO> allActors() {
        return actorService.findAll().stream().map(actorMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ActorDTO getById(@PathVariable Long id) {
        return actorMapper.toDto(actorService.findById(id));
    }

}
