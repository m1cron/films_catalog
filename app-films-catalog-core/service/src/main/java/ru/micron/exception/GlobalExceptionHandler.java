package ru.micron.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<FilmIncorrectData> handleException(JwtAuthenticationException exception) {
    FilmIncorrectData data = new FilmIncorrectData();
    data.setInfo(exception.getMessage());
    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler
  public ResponseEntity<FilmIncorrectData> handleException(NoSuchEntityException exception) {
    FilmIncorrectData data = new FilmIncorrectData();
    data.setInfo(exception.getMessage());
    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<FilmIncorrectData> handleException(Exception exception) {
    FilmIncorrectData data = new FilmIncorrectData();
    data.setInfo(exception.getMessage());
    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
  }

}
