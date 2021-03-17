package ru.micron.rest.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.feign.AuthFeignClient;
import ru.micron.rest.v1.dto.AuthRequestDto;
import ru.micron.rest.v1.interfaces.ApiAuth;

@RestController
@RequiredArgsConstructor
public class AuthController implements ApiAuth {

  private final AuthFeignClient authFeignClient;

  @Override
  public ResponseEntity<?> authenticate(AuthRequestDto requestDTO) {
    return authFeignClient.authenticate(requestDTO);
  }

}
