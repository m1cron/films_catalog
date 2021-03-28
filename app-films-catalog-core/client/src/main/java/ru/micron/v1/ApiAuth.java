package ru.micron.v1;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.micron.dto.AuthRequestDto;
import ru.micron.paths.ApiPathAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ApiAuth {

  @PostMapping(value = ApiPathAuth.LOGIN,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> authenticate(
      @RequestBody AuthRequestDto requestDTO
  );

  @PostMapping(value = ApiPathAuth.LOG_OUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  void logout(
      HttpServletRequest request,
      HttpServletResponse response
  );
}
