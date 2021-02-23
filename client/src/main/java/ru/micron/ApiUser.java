package ru.micron;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.micron.dto.UserDTO;
import ru.micron.paths.ApiPathUser;

import javax.validation.constraints.Min;
import java.util.List;

public interface ApiUser {

    @GetMapping(value = ApiPathUser.BASE_URL,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDTO> getUsers();

    @GetMapping(value = ApiPathUser.GET_USER_BY_ID,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO getUserById(
            @PathVariable @Min(1) long id
    );

    @PostMapping(value = ApiPathUser.BASE_URL,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO addUser(
            @RequestBody UserDTO user
    );

    // TODO edit User method

    @DeleteMapping(value = ApiPathUser.DELETE_USER_BY_ID,
            produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteUser(
            @PathVariable @Min(1) long id
    );

}
