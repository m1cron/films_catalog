package ru.micron.security.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessDeniedHandler implements
    org.springframework.security.web.access.AccessDeniedHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    Map<String, Object> body = Map.of(
        "timestamp", OffsetDateTime.now(),
        "status", HttpServletResponse.SC_FORBIDDEN,
        "error", "Forbidden",
        "message", accessDeniedException.getMessage(),
        "path", request.getServletPath()
    );

    objectMapper.writeValue(response.getOutputStream(), body);
  }
}
