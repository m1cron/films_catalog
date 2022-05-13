package ru.micron.rest;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.RegisterUserDto;
import ru.micron.mapper.UserMapper;
import ru.micron.persistence.model.User;
import ru.micron.service.UserService;

@Validated
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping("/register")
  public BasicResponse<Void> registerUser(@RequestBody RegisterUserDto user) {
    userService.register(user);
    return new BasicResponse<>();
  }

  @GetMapping("/{id}")
  public RegisterUserDto getUserById(@PathVariable UUID id) {
    User user = userService.findById(id);
    return userMapper.toDto(user);
  }
  @PatchMapping
  public BasicResponse<Void> editUser(@RequestBody RegisterUserDto user) {
    userService.editUserInfo(user);
    return new BasicResponse<>();
  }
}
