create table "film_receive"
(
    "imdb_id"     varchar(255),
    "is_received" boolean default false,
    primary key ("imdb_id")
);