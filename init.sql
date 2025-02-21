CREATE TABLE IF NOT EXISTS users
(
    id
    SERIAL
    PRIMARY
    KEY,
    username
    VARCHAR
(
    50
) UNIQUE NOT NULL,
    email VARCHAR
(
    100
) UNIQUE NOT NULL,
    password VARCHAR
(
    255
) NOT NULL
    );

INSERT INTO users (username, email, password)
VALUES ('recruiter', 'recruiter@ecorp.com', '12345'),
       ('it_candidate', 'it_candidate@ecorp.com', '12345'),
       ('b_candidate', 'b_candidate@ecorp.com', '12345'),
       ('it_manager', 'it_manager@ecorp.com', '12345'),
       ('b_manager', 'b_manager@ecorp.com', '12345');