INSERT INTO "film" (title, year, genre, watched)
VALUES ('Inception', 2010, 'sci-fi', true),
       ('The Lord of the Rings', 2001, 'fantasy', true),
       ('Tags', 2018, 'comedy', false),
       ('Gunfight at the O.K. Corral', 1957, 'western', false),
       ('Die Hard', 1988, 'action', true);

INSERT INTO users (username, password, first_name, last_name, email)
VALUES ('user', '$2a$12$S3z/n280S/wrmzaeYYL8Eub5FbT.MEokuxfwWmxIDV96FLIGdTav2', 'Billy', 'Long',
        'billy2long1@email.com'),
       ('admin', '$2a$12$m/7.uBZnv.sW1Vvj6klJyO5AT3mIysVPbZRTZAoR4C.BFNQVqNEIu', 'Harvie', 'Hull',
        'harv1ehu11@email.com');

INSERT INTO "actor" (first_name, last_name)
VALUES ('adgfdagf', 'dafgdagfdasfg'),
       ('Jeff', 'Tomsik'),
       ('Leonardo', 'Dicaprio'),
       ('adgadfg23fdagf', 'dafgadfgdagfdasfg');

INSERT INTO "actor_film" (actor_id, film_id)
VALUES (2, 1),
       (2, 3),
       (2, 4),
       (4, 2),
       (4, 4),
       (3, 3);

INSERT INTO "user_role" (user_id, role_id)
VALUES (1, 1),
       (1, 3),
       (1, 4),
       (2, 2),
       (2, 1);

INSERT INTO "actor_role" (actor_id, role_id)
VALUES (1, 3),
       (1, 4),
       (2, 4),
       (3, 3),
       (4, 3);

INSERT INTO "user_favorite_film" (user_id, film_id)
VALUES (1, 2),
       (2, 3),
       (2, 2);