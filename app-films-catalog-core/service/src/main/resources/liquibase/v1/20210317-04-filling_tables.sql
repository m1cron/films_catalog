INSERT INTO "${schema}".films (title, year, genre, watched)
VALUES
('Inception', 2010, 'sci-fi', true),
('The Lord of the Rings', 2001, 'fantasy', true),
('Tags', 2018, 'comedy', false),
('Gunfight at the O.K. Corral', 1957, 'western', false),
('Die Hard', 1988, 'action', true);

INSERT INTO "${schema}".users (username, password, first_name, last_name, email)
VALUES
('user', '$2y$12$2fBeXbxyuaCk8KWNkwA7nee/E4HQQNMc1qnP2Hi93oy2xDWrxJtAa', 'Billy', 'Long', 'billy2long1@email.com'),
('admin', '$2y$12$lF9vTEu4wDd3L0oPIJyPbeO/9mCr69.CkNHDHmGLR0J7XBjCPimFa', 'Harvie', 'Hull', 'harv1ehu11@email.com');

INSERT INTO "${schema}".actors (first_name, last_name)
VALUES
('adgfdagf', 'dafgdagfdasfg'),
('Jeff', 'Tomsik'),
('Leonardo', 'Dicaprio'),
('adgadfg23fdagf', 'dafgadfgdagfdasfg');

INSERT INTO "${schema}".actors_films (actor_id, film_id)
VALUES (2,1), (2,3), (2,4), (4,2), (4,4), (3,3);


INSERT INTO "${schema}".user_roles (user_id, role_id)
VALUES (1,1), (1,3), (1,4), (2,2), (2,1);

INSERT INTO "${schema}".actors_roles (actor_id, role_id)
VALUES (1,3), (1,4), (2,4), (3,3), (4,3);


INSERT INTO "${schema}".user_fav_films (user_id, film_id)
VALUES (1,2), (2,3), (2,2);