package ru.micron.libs.exception;

public class NoSuchEntityException extends RuntimeException {

  public NoSuchEntityException(String message) {
    super(message);
  }
}
