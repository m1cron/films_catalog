package ru.micron.exception;

public class NoSuchFilmException extends RuntimeException {

    public NoSuchFilmException(String message) {
        super(message);
    }

}
