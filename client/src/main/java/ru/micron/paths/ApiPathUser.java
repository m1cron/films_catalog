package ru.micron.paths;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPathUser {

    public static final String BASE_URL = "/api/v1/users";

    public static final String GET_USER_BY_ID = BASE_URL + ApiConstants.ID;
    public static final String DELETE_USER_BY_ID = BASE_URL + ApiConstants.ID;

}
