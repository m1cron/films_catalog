CREATE TABLE "actor_film"
(
    "actor_id" BIGINT NOT NULL,
    "film_id"  BIGINT NOT NULL,
    PRIMARY KEY ("actor_id", "film_id"),
    FOREIGN KEY ("actor_id") REFERENCES "actor" ("id"),
    FOREIGN KEY ("film_id") REFERENCES "film" ("id")
);

CREATE TABLE "user_favorite_film"
(
    "user_id" BIGINT NOT NULL,
    "film_id" BIGINT NOT NULL,
    PRIMARY KEY ("user_id", "film_id"),
    FOREIGN KEY ("user_id") REFERENCES "user" ("id"),
    FOREIGN KEY ("film_id") REFERENCES "film" ("id")
);