CREATE TABLE IF NOT EXISTS films (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    year INT,
    genre VARCHAR(20),
    watched BIT DEFAULT FALSE NOT NULL,
    status VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated VARCHAR(40) DEFAULT CURRENT_TIMESTAMP(),
    UNIQUE (title)
);

INSERT INTO films (title, year, genre, watched)
VALUES
        ('Inception', 2010, 'sci-fi', 1),
        ('The Lord of the Rings', 2001, 'fantasy', 1),
        ('Tags', 2018, 'comedy', 0),
        ('Gunfight at the O.K. Corral', 1957, 'western', 0),
        ('Die Hard', 1988, 'action', 1);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(100),
    status VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated VARCHAR(40) DEFAULT CURRENT_TIMESTAMP(),
    UNIQUE (username)
);

INSERT INTO users (username, password, first_name, last_name, email)
VALUES      ('user', '$2y$12$2fBeXbxyuaCk8KWNkwA7nee/E4HQQNMc1qnP2Hi93oy2xDWrxJtAa', 'Billy', 'Long', 'billy2long1@email.com'),
            ('admin', '$2y$12$lF9vTEu4wDd3L0oPIJyPbeO/9mCr69.CkNHDHmGLR0J7XBjCPimFa', 'Harvie', 'Hull', 'harv1ehu11@email.com');

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE NOT NULL,
    status VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated VARCHAR(40) DEFAULT CURRENT_TIMESTAMP()
);
INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_ACTOR'), (4, 'ROLE_PRODUCER');

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT INTO user_roles (user_id, role_id)
VALUES (1,1), (1,3), (1,4), (2,2), (2,1);

CREATE TABLE IF NOT EXISTS actors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(100),
    status VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated VARCHAR(40) DEFAULT CURRENT_TIMESTAMP(),
    UNIQUE (first_name, last_name)
);
INSERT INTO actors (first_name, last_name)
VALUES  ('adgfdagf', 'dafgdagfdasfg'),
        ('Jeff', 'Tomsik'),
        ('Leonardo', 'Dicaprio'),
        ('adgadfg23fdagf', 'dafgadfgdagfdasfg');

CREATE TABLE IF NOT EXISTS actors_films (
    actor_id BIGINT NOT NULL,
    film_id BIGINT NOT NULL,
    PRIMARY KEY (actor_id, film_id),
    FOREIGN KEY (actor_id)  REFERENCES actors(id),
    FOREIGN KEY (film_id)   REFERENCES films(id)
);
INSERT INTO actors_films (actor_id, film_id)
VALUES (2,1), (2,3), (2,4), (4,2), (4,4), (3,3);


CREATE TABLE IF NOT EXISTS user_fav_films (
    user_id BIGINT NOT NULL,
    film_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (film_id) REFERENCES films(id)
);
INSERT INTO user_fav_films (user_id, film_id)
VALUES (1,2), (2,3), (2,2);

CREATE TABLE IF NOT EXISTS actors_roles (
    actor_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (actor_id) REFERENCES actors(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT INTO actors_roles (actor_id, role_id)
VALUES (1,3), (1,4), (2,4), (3,3), (4,3);