CREATE TABLE roles (
    "id" BIGSERIAL,
    "name" VARCHAR(20) UNIQUE NOT NULL,
    "status" VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    "created" TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    "updated" TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    UNIQUE ("name"),
    PRIMARY KEY ("id")
);
INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_ACTOR'), (4, 'ROLE_PRODUCER');

CREATE TABLE user_roles (
    "user_id" BIGINT NOT NULL,
    "role_id" BIGINT NOT NULL,
    PRIMARY KEY ("user_id", "role_id"),
    FOREIGN KEY ("user_id") REFERENCES users("id"),
    FOREIGN KEY ("role_id") REFERENCES roles("id")
);

CREATE TABLE actors_roles (
    "actor_id" BIGINT NOT NULL,
    "role_id" BIGINT NOT NULL,
    PRIMARY KEY ("actor_id", "role_id"),
    FOREIGN KEY ("actor_id") REFERENCES actors("id"),
    FOREIGN KEY ("role_id") REFERENCES roles("id")
);