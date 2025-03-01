
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

--populate jobs

INSERT INTO job (title, description, location, publish_date)
SELECT 'Scrum Master', 'You will help teams to be more and more agile', 'Lucca, Italy',
       TO_TIMESTAMP('2024-03-01T14:30:00', 'YYYY-MM-DDTHH24:MI.SS.FF')
UNION ALL
SELECT 'Junior Developer', 'If you want to start a tech career this is the job for you', 'Lucca, Italy',
       TO_TIMESTAMP('2024-04-02T12:30:00.313+08:00', 'yyyy-mm-ddThh24:mi.ss.ff')
UNION ALL
SELECT 'Frontend Developer', 'To apply to this job you should know React, Angular, Vue, and 736479 other fancy ' ||
                             'frameworks',
       'Salerno, Italy',
                    TO_TIMESTAMP('2024-05-01T11:30:00.313+08:00', 'yyyy-mm-ddThh24:mi.ss.ff')
UNION ALL
SELECT 'Backend Developer', 'To apply to this job you should know Java,C#, C++ and 746832 other languages', 'Salerno,' ||
                                                                                                            ' Italy',
                                 TO_TIMESTAMP('2025-01-01T10:30:00.313+08:00', 'yyyy-mm-ddThh24:mi.ss.ff')
UNION ALL
SELECT 'HR Recruiter', 'You will help to recruit new resources in a fast growing company.', 'Dublin, Ireland',
                                              TO_TIMESTAMP('2024-07-12T09:30:00.313+08:00', 'yyyy-mm-ddThh24:mi.ss.ff')
UNION ALL
SELECT 'Career Manager', 'With this job you will guide the employees through their career.', 'Dublin, Ireland',
                                                           TO_TIMESTAMP('2024-06-12T09:30:00.313+08:00', 'yyyy-mm-ddThh24:mi.ss.ff')
UNION ALL
SELECT 'Senior Accountant', 'A job for managin the whole company account.', 'Dublin, Ireland', TO_TIMESTAMP
('2024-07-12T09:30:00.313+08:00', 'yyyy-mm-ddThh24:mi.ss.ff')

ON CONFLICT (title) DO NOTHING;

--end populate jobs

CREATE TABLE IF NOT EXISTS application
(
    id SERIAL PRIMARY KEY,
    status  VARCHAR(255),
    job_id  BIGINT REFERENCES job(id),
    user_id BIGINT REFERENCES users(id)
);