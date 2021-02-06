INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN'),
       (3, 'ROLE_ACTOR'),
       (4, 'ROLE_PRODUCER');

INSERT INTO films (title,year,genre,watched)
VALUES  ('Inception', 2010, 'sci-fi', 1),
        ('Tags', 2018, 'comedy', 0),
        ('Die Hard', 1988, 'action', 1);

INSERT INTO actors (first_name, last_name)
VALUES  ('adgfdagf', 'dafgdagfdasfg'),
        ('adgfdasgaagf', 'dafdfggdagfdasfg'),
        ('adgfdagf', 'dafgdadfgagfdasfg'),
        ('adgadfg23fdagf', 'dafgadfgdagfdasfg');

INSERT INTO actors_films (actor_id, film_id)
VALUES (1,1), (2,3), (3,3), (2,4);