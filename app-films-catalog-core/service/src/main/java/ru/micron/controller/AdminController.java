package ru.micron.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.v1.ApiAdmin;
import ru.micron.dto.UserDto;
import ru.micron.exception.NoSuchEntityException;
import ru.micron.mapper.UserMapper;
import ru.micron.model.User;
import ru.micron.service.UserService;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
public class AdminController implements ApiAdmin {

  private final UserService userService;
  private final UserMapper userMapper;

  @ApiOperation("Get all users")
  @Override
  public List<UserDto> allUsers(
  ) {
    return userService.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
  }

  @ApiOperation("Get user by ID")
  @Override
  public UserDto getUserById(
      @Min(1) long id
  ) {
    return userMapper.toDto(userService.findById(id));
  }

  @ApiOperation("Register user")
  @Override
  public UserDto addUser(
      @RequestBody UserDto user
  ) {
    return userService.register(userMapper.toEntity(user));
  }

  @ApiOperation("Delete user")
  @Override
  public String deleteUser(
      @Min(1) long id
  ) {
    User user = userService.findById(id);
    if (user == null) {
      throw new NoSuchEntityException(
          String.format("There is no user with ID = %d in Database", id));
    }
    userService.deleteById(id);
    return String.format("User with ID = %d was deleted", id);
  }
}
