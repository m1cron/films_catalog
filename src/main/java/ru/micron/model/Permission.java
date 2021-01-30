package ru.micron.model;

public enum Permission {
    FILMS_READ("films:read"),
    FILMS_WRITE("films:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
