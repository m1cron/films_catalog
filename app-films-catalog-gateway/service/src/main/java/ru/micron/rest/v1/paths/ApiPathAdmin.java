package ru.micron.rest.v1.paths;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPathAdmin {

  public static final String BASE_URL = "/api/v1/admin";
  public static final String USERS = "/users";

  public static final String GET_USERS = BASE_URL + USERS;
  public static final String ADD_USER = BASE_URL + USERS;
  public static final String GET_USER_BY_ID = BASE_URL + USERS + ApiConstants.ID;
  public static final String DELETE_USER_BY_ID = BASE_URL + USERS + ApiConstants.ID;
}
