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
('The Lord of the Rings: The Fellowship of the Ring', 2001, 'fantasy', 1),
('Tags', 2018, 'comedy', 0),
('Gunfight at the O.K. Corral', 1957, 'western', 0),
('Die Hard', 1988, 'action', 1);