CREATE TABLE films
(
    id int PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    year int,
    genre VARCHAR(20),
    watched BIT DEFAULT false  NOT NULL
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
    id bigint auto_increment,
    login varchar(255) not null,
    password varchar(255) not null,
    role varchar(10) default 'USER' not null,
    status varchar(10) default 'ACTIVE' not null,
    constraint USERS_PK
        primary key (id)
);
CREATE UNIQUE INDEX USERS_EMAIL_UINDEX ON users (login);

INSERT INTO users (login,password) VALUES ('user', '$2y$12$pZ3657DCwDaArV/AW35WyOToe9HYURHsoGQ9HHXhcaPZ4TRxI1nU.');
INSERT INTO users (login,password,role) VALUES ('admin', '$2y$12$CEmKhDb/Y2GwwI8gZdHUCeQp5aB.ZCt119g3YTReQkzkzqZzXzjka', 'ADMIN');