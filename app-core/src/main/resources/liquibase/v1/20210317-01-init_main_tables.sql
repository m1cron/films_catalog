CREATE TABLE "film"
(
    "id"      BIGSERIAL,
    "title"   VARCHAR(100) NOT NULL,
    "year"    INTEGER,
    "genre"   VARCHAR(20),
    "watched" BOOLEAN               DEFAULT FALSE NOT NULL,
    "status"  VARCHAR(10)           DEFAULT 'ACTIVE' NOT NULL,
    "created" TIMESTAMP    NOT NULL DEFAULT now(),
    "updated" TIMESTAMP    NOT NULL DEFAULT now(),
    UNIQUE ("title"),
    PRIMARY KEY ("id")
);

CREATE TABLE "user"
(
    "id"         BIGSERIAL,
    "username"   VARCHAR(255)                 NOT NULL,
    "email"      VARCHAR(255),
    "password"   VARCHAR(255)                 NOT NULL,
    "first_name" VARCHAR(50),
    "last_name"  VARCHAR(100),
    "status"     VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    "created"    TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    "updated"    TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    UNIQUE ("username"),
    PRIMARY KEY ("id")
);

CREATE TABLE "actor"
(
    "id"         BIGSERIAL,
    "first_name" VARCHAR(50),
    "last_name"  VARCHAR(100),
    "status"     VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    "created"    TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    "updated"    TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    UNIQUE ("first_name", "last_name"),
    PRIMARY KEY ("id")
);