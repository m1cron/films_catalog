package ru.micron.rest;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.UserDto;
import ru.micron.mapper.UserMapper;
import ru.micron.persistence.model.User;
import ru.micron.service.UserService;

@Validated
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;
  private final UserMapper userMapper;

  @ApiOperation("Get all users")
  @GetMapping("/users")
  public List<UserDto> allUsers() {
    return userService.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
  }

  @ApiOperation("Get user by ID")
  @GetMapping("/users/{id}")
  public UserDto getUserById(@PathVariable @Min(1) long id) {
    return userMapper.toDto(userService.findById(id));
  }

  @ApiOperation("Register user")
  @PostMapping("/users")
  public UserDto addUser(@RequestBody UserDto user) {
    return userService.register(userMapper.toEntity(user));
  }

  @ApiOperation("Delete user")
  @DeleteMapping("/users/{id}")
  public String deleteUser(
      @PathVariable @Min(1) long id
  ) {
    User user = userService.findById(id);
    if (user == null) {
      throw new RuntimeException(
          String.format("There is no user with ID = %d in Database", id));
    }
    userService.deleteById(id);
    return String.format("User with ID = %d was deleted", id);
  }
}
