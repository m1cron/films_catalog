CREATE TABLE "${schema}".actors_films (
    "actor_id" BIGINT NOT NULL,
    "film_id" BIGINT NOT NULL,
    PRIMARY KEY ("actor_id", "film_id"),
    FOREIGN KEY ("actor_id") REFERENCES actors("id"),
    FOREIGN KEY ("film_id") REFERENCES films("id")
);

CREATE TABLE "${schema}".user_fav_films (
    "user_id" BIGINT NOT NULL,
    "film_id" BIGINT NOT NULL,
    PRIMARY KEY ("user_id", "film_id"),
    FOREIGN KEY ("user_id") REFERENCES users("id"),
    FOREIGN KEY ("film_id") REFERENCES films("id")
);