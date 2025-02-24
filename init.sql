
CREATE TABLE IF NOT EXISTS role
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    display_name VARCHAR(50) UNIQUE NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT REFERENCES role(id) ON DELETE SET NULL
);

INSERT INTO role (name)
VALUES ('Recruiter'), ('Candidate'), ('Manager')
ON CONFLICT (name) DO NOTHING;

CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO users (display_name, username, password, role_id)
SELECT 'recruiter', 'recruiter@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name = 'Recruiter'
UNION ALL
SELECT 'it_candidate', 'it_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name = 'Candidate'
UNION ALL
SELECT 'b_candidate', 'b_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name = 'Candidate'
UNION ALL
SELECT 'it_manager', 'it_manager@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name = 'Manager'
UNION ALL
SELECT 'b_manager', 'b_manager@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name = 'Manager'
ON CONFLICT (username) DO NOTHING;