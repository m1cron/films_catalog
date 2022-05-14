package ru.micron.rest;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.UserDto;
import ru.micron.service.UserService;

@Validated
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public BasicResponse<Void> registerUser(@RequestBody UserDto user) {
    userService.register(user);
    return new BasicResponse<>();
  }

  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable UUID id) {
    return userService.getUserById(id);
  }
  @PatchMapping
  public BasicResponse<Void> editUser(@RequestBody UserDto user) {
    userService.editUserData(user);
    return new BasicResponse<>();
  }
}
