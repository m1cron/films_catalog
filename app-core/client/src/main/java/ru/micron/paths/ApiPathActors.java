package ru.micron.paths;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPathActors {

  public static final String BASE_URL = "/api/v1/actors";

  public static final String GET_ACTOR_BY_ID = BASE_URL + ApiConstants.ID;
}
