CREATE TABLE "role"
(
    "id"      BIGSERIAL,
    "name"    VARCHAR(20) UNIQUE           NOT NULL,
    "status"  VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    "created" TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    "updated" TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    UNIQUE ("name"),
    PRIMARY KEY ("id")
);

INSERT INTO "role" (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN'),
       (3, 'ROLE_ACTOR'),
       (4, 'ROLE_PRODUCER');

CREATE TABLE "user_role"
(
    "user_id" BIGINT NOT NULL,
    "role_id" BIGINT NOT NULL,
    PRIMARY KEY ("user_id", "role_id"),
    FOREIGN KEY ("user_id") REFERENCES "user" ("id"),
    FOREIGN KEY ("role_id") REFERENCES "role" ("id")
);

CREATE TABLE "actor_role"
(
    "actor_id" BIGINT NOT NULL,
    "role_id"  BIGINT NOT NULL,
    PRIMARY KEY ("actor_id", "role_id"),
    FOREIGN KEY ("actor_id") REFERENCES "actor" ("id"),
    FOREIGN KEY ("role_id") REFERENCES "role" ("id")
);