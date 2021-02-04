CREATE TABLE films (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    year INT,
    genre VARCHAR(20),
    watched BIT DEFAULT FALSE NOT NULL
);
CREATE UNIQUE INDEX films_title_uindex ON films (title);

INSERT INTO films (title,year,genre, watched)
VALUES
        ('Inception', 2010, 'sci-fi', 1),
        ('The Lord of the Rings', 2001, 'fantasy', 1),
        ('Tags', 2018, 'comedy', 0),
        ('Gunfight at the O.K. Corral', 1957, 'western', 0),
        ('Die Hard', 1988, 'action', 1);

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(100),
    film_id BIGINT REFERENCES films(id),
    status VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated VARCHAR(40) DEFAULT CURRENT_TIMESTAMP()
);
CREATE UNIQUE INDEX USERS_EMAIL_UINDEX ON users (username);

INSERT INTO users (username,password, film_id)
VALUES      ('user', '$2a$04$c53gTrfbot9lcL5i1p0KYOcpwiEKR2Y0yPdDoKGtfjCnjF4D.PkZ.',1),
            ('admin', '$2a$04$c53gTrfbot9lcL5i1p0KYOcpwiEKR2Y0yPdDoKGtfjCnjF4D.PkZ.',1);

CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE NOT NULL,
    status VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated VARCHAR(40) DEFAULT CURRENT_TIMESTAMP()
);
INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_ACTOR'), (4, 'ROLE_PRODUCER');

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    UNIQUE (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1), (2, 1), (2, 3);