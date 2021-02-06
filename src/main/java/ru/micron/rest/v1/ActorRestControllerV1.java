package ru.micron.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.model.Actor;
import ru.micron.service.ActorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
public class ActorRestControllerV1 {

    private final ActorService actorService;

    @Autowired
    public ActorRestControllerV1(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping()
    public List<Actor> allActors() {
        return actorService.findAll();
    }

}
