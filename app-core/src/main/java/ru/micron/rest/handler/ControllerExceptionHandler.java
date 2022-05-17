package ru.micron.rest.handler;

import com.fasterxml.jackson.core.JsonParseException;
import java.nio.file.AccessDeniedException;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.ErrorObject;

@Slf4j
@NoArgsConstructor
@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<BasicResponse<Object>> handleError(Throwable e) {
    Throwable rootCause = ExceptionUtils.unwrapInvocationTargetException(e);
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    String errorMes = rootCause.getMessage();

    if (rootCause instanceof DateTimeParseException) {
      errorMes = ((DateTimeParseException) e).getParsedString() + " is not valid datetime value";
    } else if (rootCause instanceof AccessDeniedException) {
      httpStatus = HttpStatus.FORBIDDEN;
    } else if (rootCause instanceof BadCredentialsException
        || rootCause instanceof AuthenticationServiceException) {
      httpStatus = HttpStatus.BAD_REQUEST;
    } else if (rootCause instanceof JsonParseException) {
      httpStatus = HttpStatus.BAD_REQUEST;
      errorMes = "HTTP request body is not a valid JSON: " + e.getMessage();
    } else if (rootCause instanceof BindException) {
      httpStatus = HttpStatus.BAD_REQUEST;
      errorMes =
          ((BindException) e)
              .getBindingResult().getAllErrors().stream()
              .map(this::getMessageFromFieldError)
              .collect(Collectors.joining(", "));
    } else if (rootCause instanceof ResponseStatusException) {
      httpStatus = HttpStatus.BAD_REQUEST;
    }

    ErrorObject errorObject = new ErrorObject("" + httpStatus.value(), errorMes);
    log.error(errorObject.toString(), rootCause);
    return ResponseEntity.status(httpStatus.value())
        .body(buildErrorResponse(errorObject));
  }

  private String getMessageFromFieldError(ObjectError error) {
    String field = ((FieldError) error).getField();
    return String.format("%s %s", field, error.getDefaultMessage());
  }

  private BasicResponse<Object> buildErrorResponse(ErrorObject errorObject) {
    return BasicResponse.builder()
        .error(errorObject)
        .success(false)
        .build();
  }
}
