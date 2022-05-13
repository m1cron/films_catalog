create table "film"
(
    "imdb_id"         varchar(255),
    "title"           varchar(255),
    "year_of_release" varchar(255),
    "rated"           varchar(255),
    "released"        varchar(255),
    "runtime"         varchar(255),
    "genre"           varchar(255),
    "director"        varchar(255),
    "writer"          text,
    "actor"           text,
    "plot"            text,
    "language_of"     varchar(255),
    "country"         varchar(255),
    "award"           text,
    "poster"          text,
    "metascore"       varchar(255),
    "imdb_rating"     varchar(255),
    "imdb_vote"       varchar(255),
    "type"            varchar(255),
    "dvd"             varchar(255),
    "box_office"      varchar(255),
    "production"      varchar(255),
    "website"         varchar(255),
    "response"        boolean,
    unique ("imdb_id"),
    primary key ("imdb_id")
);

create table "film_rating"
(
    "uuid"      uuid,
    "imdb_id" varchar(255) references film (imdb_id),
    "source"  varchar(255),
    "value"   varchar(255),
    primary key ("uuid")
);

create table users
(
    "uuid"       uuid,
    "username"   varchar(255)                 not null,
    "email"      varchar(255),
    "password"   varchar(255)                 not null,
    "first_name" varchar(50),
    "last_name"  varchar(100),
    "status"     varchar(10) default 'ACTIVE' not null,
    "created"    timestamp without time zone default now(),
    unique ("username"),
    primary key ("uuid")
);

insert into users (uuid, username, password, first_name, last_name, email)
values ('00000000-0000-0000-0000-000000000000', 'admin',
        '$2a$12$m/7.uBZnv.sW1Vvj6klJyO5AT3mIysVPbZRTZAoR4C.BFNQVqNEIu', 'admin',
        'admin', 'admin@email.com'),
       ('11111111-1111-1111-1111-111111111111', 'user',
        '$2a$12$S3z/n280S/wrmzaeYYL8Eub5FbT.MEokuxfwWmxIDV96FLIGdTav2', 'user',
        'user', 'user@email.com');

create table "user_favourite_film"
(
    "user_id" uuid,
    "imdb_id" varchar(255),
    primary key ("user_id", "imdb_id"),
    foreign key ("user_id") references users ("uuid"),
    foreign key ("imdb_id") references "film" ("imdb_id")
);
