package ru.micron.controller.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.micron.dto.UserDto;
import ru.micron.paths.ApiPathUser;

import javax.validation.constraints.Min;
import java.util.List;

public interface ApiUser {

  @GetMapping(value = ApiPathUser.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  List<UserDto> getUsers();

  @GetMapping(value = ApiPathUser.GET_USER_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  UserDto getUserById(
      @PathVariable @Min(1) long id
  );

  @PostMapping(value = ApiPathUser.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  UserDto addUser(
      @RequestBody UserDto user
  );

  @PutMapping(value = ApiPathUser.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  UserDto editUser(
      @RequestBody UserDto user
  );

  @DeleteMapping(value = ApiPathUser.DELETE_USER_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  String deleteUser(
      @PathVariable @Min(1) long id
  );
}
