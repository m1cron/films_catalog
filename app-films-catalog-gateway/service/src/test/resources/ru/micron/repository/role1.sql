INSERT INTO users (username,password)
VALUES      ('user', '$2y$12$2fBeXbxyuaCk8KWNkwA7nee/E4HQQNMc1qnP2Hi93oy2xDWrxJtAa'),
            ('admin', '$2y$12$lF9vTEu4wDd3L0oPIJyPbeO/9mCr69.CkNHDHmGLR0J7XBjCPimFa');

INSERT INTO user_roles (user_id, role_id)
VALUES (1,1), (1,3), (1,4), (2,2), (2,4);