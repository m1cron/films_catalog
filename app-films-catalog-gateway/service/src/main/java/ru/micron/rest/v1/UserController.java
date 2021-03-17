package ru.micron.rest.v1;

import java.util.List;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.feign.UserFeignClient;
import ru.micron.rest.v1.dto.UserDto;
import ru.micron.rest.v1.interfaces.ApiUser;

@RestController
@RequiredArgsConstructor
public class UserController implements ApiUser {

  private final UserFeignClient userFeignClient;

  @Override
  public List<UserDto> getUsers(String jwtToken) {
    return userFeignClient.getUsers(jwtToken);
  }

  @Override
  public UserDto getUserById(@Min(1) long id, String jwtToken) {
    return userFeignClient.getUserById(id, jwtToken);
  }

  @Override
  public UserDto addUser(UserDto user, String jwtToken) {
    return userFeignClient.addUser(user, jwtToken);
  }

  @Override
  public UserDto editUser(UserDto user, String jwtToken) {
    return userFeignClient.editUser(user, jwtToken);
  }

  @Override
  public String deleteUser(@Min(1) long id, String jwtToken) {
    return userFeignClient.deleteUser(id, jwtToken);
  }
}
