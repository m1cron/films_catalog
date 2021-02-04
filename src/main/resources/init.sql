CREATE TABLE films
(
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

CREATE TABLE users
(
    id BIGINT AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) DEFAULT 'USER' NOT NULL,
    status VARCHAR(10) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT USERS_PK PRIMARY KEY (id)
);
CREATE UNIQUE INDEX USERS_EMAIL_UINDEX ON users (login);

INSERT INTO users (login,password) VALUES ('user', '$2y$12$pZ3657DCwDaArV/AW35WyOToe9HYURHsoGQ9HHXhcaPZ4TRxI1nU.');
INSERT INTO users (login,password,role) VALUES ('admin', '$2y$12$CEmKhDb/Y2GwwI8gZdHUCeQp5aB.ZCt119g3YTReQkzkzqZzXzjka', 'ADMIN');

CREATE TABLE persons
(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	film_id BIGINT REFERENCES films(id),
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	role VARCHAR(15) DEFAULT 'ACTOR' NOT NULL
);

INSERT INTO persons (film_id, first_name, last_name)
VALUES
        (1, 'employee', 'employee'),
        (1, 'employee1', 'employee1'),
        (3, 'adsdfgfgadfg', 'adfsewrtgadgf'),
        (4, 'test12334', 'test12314'),
        (5, 'dfgpsdfgiubae0g', 'dagfhrashy');


