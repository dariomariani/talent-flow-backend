CREATE TABLE IF NOT EXISTS users
(
    id
    SERIAL
    PRIMARY
    KEY,
    display_name
    VARCHAR
(
    50
) UNIQUE NOT NULL,
    username VARCHAR
(
    100
) UNIQUE NOT NULL,
    password VARCHAR
(
    255
) NOT NULL
    );

CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO users (display_name, username, password)
VALUES ('recruiter', 'recruiter@ecorp.com', crypt('12345', gen_salt('bf'))),
       ('it_candidate', 'it_candidate@ecorp.com', crypt('12345', gen_salt('bf'))),
       ('b_candidate', 'b_candidate@ecorp.com', crypt('12345', gen_salt('bf'))),
       ('it_manager', 'it_manager@ecorp.com', crypt('12345', gen_salt('bf'))),
       ('b_manager', 'b_manager@ecorp.com', crypt('12345', gen_salt('bf')));