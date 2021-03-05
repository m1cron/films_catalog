package ru.micron.rest.v1;

import java.util.List;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.feign.AdminFeignClient;
import ru.micron.rest.v1.dto.UserDto;
import ru.micron.rest.v1.interfaces.ApiAdmin;

@RestController
@RequiredArgsConstructor
public class AdminController implements ApiAdmin {

  private final AdminFeignClient adminFeignClient;

  @Override
  public List<UserDto> allUsers(String jwtToken) {
    return adminFeignClient.allUsers(jwtToken);
  }

  @Override
  public UserDto getUserById(@Min(1) long id, String jwtToken) {
    return adminFeignClient.getUserById(id, jwtToken);
  }

  @Override
  public UserDto addUser(UserDto user, String jwtToken) {
    return adminFeignClient.addUser(user, jwtToken);
  }

  @Override
  public String deleteUser(@Min(1) long id, String jwtToken) {
    return adminFeignClient.deleteUser(id, jwtToken);
  }
}
