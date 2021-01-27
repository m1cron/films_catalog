package ru.micron.exception_handling;

public class NoSuchFilmException extends RuntimeException {

    public NoSuchFilmException(String message) {
        super(message);
    }
}
