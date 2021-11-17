package ru.micron.paths;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPathFilms {

  public static final String BASE_URL = "/api/v1/films";

  public static final String GET_FILM_BY_ID = BASE_URL + ApiConstants.ID;
  public static final String DELETE_FILM_BY_ID = BASE_URL + ApiConstants.ID;
}
