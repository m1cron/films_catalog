package ru.micron.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoSuchEntityException extends RuntimeException {

  public NoSuchEntityException(String message) {
    super(message);
    log.warn(message);
  }

}
