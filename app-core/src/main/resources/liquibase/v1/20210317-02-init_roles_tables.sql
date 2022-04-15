CREATE TABLE roles
(
    "id"      BIGSERIAL,
    "name"    VARCHAR(20) UNIQUE           NOT NULL,
    UNIQUE ("name"),
    PRIMARY KEY ("id")
);

INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN'),
       (3, 'ROLE_ACTOR'),
       (4, 'ROLE_PRODUCER');

CREATE TABLE "user_role"
(
    "user_id" BIGINT NOT NULL,
    "role_id" BIGINT NOT NULL,
    PRIMARY KEY ("user_id", "role_id"),
    FOREIGN KEY ("user_id") REFERENCES users ("id"),
    FOREIGN KEY ("role_id") REFERENCES roles ("id")
);

CREATE TABLE "actor_role"
(
    "actor_id" BIGINT NOT NULL,
    "role_id"  BIGINT NOT NULL,
    PRIMARY KEY ("actor_id", "role_id"),
    FOREIGN KEY ("actor_id") REFERENCES "actor" ("id"),
    FOREIGN KEY ("role_id") REFERENCES roles ("id")
);