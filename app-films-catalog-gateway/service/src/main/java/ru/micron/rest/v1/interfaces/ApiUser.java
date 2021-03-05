package ru.micron.rest.v1.interfaces;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import ru.micron.rest.v1.dto.UserDto;
import ru.micron.rest.v1.paths.ApiPathUser;

public interface ApiUser {

  @GetMapping(value = ApiPathUser.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  List<UserDto> getUsers(
      @RequestHeader("Authorization") String jwtToken
  );

  @GetMapping(value = ApiPathUser.GET_USER_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  UserDto getUserById(
      @PathVariable @Min(1) long id,
      @RequestHeader("Authorization") String jwtToken
  );

  @PostMapping(value = ApiPathUser.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  UserDto addUser(
      @RequestBody UserDto user,
      @RequestHeader("Authorization") String jwtToken
  );

  @PutMapping(value = ApiPathUser.BASE_URL,
      produces = MediaType.APPLICATION_JSON_VALUE)
  UserDto editUser(
      @RequestBody UserDto user,
      @RequestHeader("Authorization") String jwtToken
  );

  @DeleteMapping(value = ApiPathUser.DELETE_USER_BY_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  String deleteUser(
      @PathVariable @Min(1) long id,
      @RequestHeader("Authorization") String jwtToken
  );
}
