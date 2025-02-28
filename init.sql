
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
--populate roles
INSERT INTO role (name)
VALUES ('Recruiter'), ('Candidate'), ('Manager')
ON CONFLICT (name) DO NOTHING;

CREATE EXTENSION IF NOT EXISTS pgcrypto;
--populate users and assing roles
INSERT INTO users (display_name, username, password, role_id)
SELECT 'Keith Recruiter', 'keith_recruiter@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                     'Recruiter'
UNION ALL
SELECT 'John Candidate', 'john_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                         'Candidate'
UNION ALL
SELECT 'Brian Candidate', 'brian_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                         'Candidate'
UNION ALL
SELECT 'Elon Candidate', 'elon_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Mary Candidate', 'mary_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Eliah Candidate', 'eliah_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Alice Candidate', 'alice_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Bob Candidate', 'bob_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Lucy Candidate', 'lucy_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Carl Candidate', 'carl_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Stephen Candidate', 'stephen_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r
    .name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Jane Candidate', 'jane_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Janice Candidate', 'janice_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Monica Candidate', 'monica_candidate@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r
    .name =
                                                                                                      'Candidate'
UNION ALL
SELECT 'Nancy Manager', 'nancy_manager@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                       'Manager'
UNION ALL
SELECT 'Ralph Manager', 'ralph_manager@ecorp.com', crypt('12345', gen_salt('bf')), r.id FROM role r WHERE r.name =
                                                                                                       'Manager'
ON CONFLICT (username) DO NOTHING;
--end populate users

CREATE TABLE IF NOT EXISTS job
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(100) UNIQUE NOT NULL,
    location VARCHAR(255) NOT NULL,
    publish_date TIMESTAMP(6)
);

CREATE TABLE IF NOT EXISTS application
(
    id SERIAL PRIMARY KEY,
    status  VARCHAR(255),
    job_id  BIGINT REFERENCES job(id),
    user_id BIGINT REFERENCES users(id)
);