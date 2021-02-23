package ru.micron;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.micron.dto.UserDTO;
import ru.micron.paths.ApiPathAdmin;

import javax.validation.constraints.Min;
import java.util.List;

public interface ApiAdmin {

    @GetMapping(value = ApiPathAdmin.GET_USERS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDTO> allUsers();

    @GetMapping(value = ApiPathAdmin.GET_USER_BY_ID,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO getUserById(
            @PathVariable @Min(1) long id
    );

    @PostMapping(value = ApiPathAdmin.ADD_USER,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO addUser(
            @RequestBody UserDTO user
    );

    // TODO edit User method

    @DeleteMapping(value = ApiPathAdmin.DELETE_USER_BY_ID,
            produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteUser(
            @PathVariable @Min(1) long id
    );

}
