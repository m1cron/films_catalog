INSERT INTO roles (id, name)
VALUES (1, 'USER'),
       (2, 'ADMIN'),
       (3, 'ACTOR'),
       (4, 'PRODUCER');

INSERT INTO films (title,year,genre,watched)
VALUES  ('Inception', 2010, 'sci-fi', 1),
        ('Tags', 2018, 'comedy', 0),
        ('Die Hard', 1988, 'action', 1);

INSERT INTO users (username,password,film_id)
VALUES  ('user', '$2a$04$c53gTrfbot9lcL5i1p0KYOcpwiEKR2Y0yPdDoKGtfjCnjF4D.PkZ.', 1),
        ('admin', '$2a$04$c53gTrfbot9lcL5i1p0KYOcpwiEKR2Y0yPdDoKGtfjCnjF4D.PkZ.', 1);

INSERT INTO user_roles (user_id, role_id)
VALUES (1,1),
       (1,3),
       (1,4);