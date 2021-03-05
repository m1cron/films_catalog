package ru.micron.rest.v1.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.micron.rest.v1.dto.AuthRequestDto;
import ru.micron.rest.v1.paths.ApiPathAuth;

public interface ApiAuth {

  @PostMapping(value = ApiPathAuth.LOGIN,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> authenticate(
      @RequestBody AuthRequestDto requestDTO
  );
}
