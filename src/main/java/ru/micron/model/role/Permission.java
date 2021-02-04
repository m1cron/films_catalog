package ru.micron.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    FILMS_READ("films:read"),
    FILMS_WRITE("films:write");

    private final String permission;

}
