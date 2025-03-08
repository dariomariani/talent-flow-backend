
CREATE TABLE IF NOT EXISTS role
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    display_name VARCHAR(50) UNIQUE NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT REFERENCES role(id) ON DELETE SET NULL
);
--populate roles
INSERT INTO role (name)
VALUES ('Recruiter'), ('Candidate'), ('Manager');

--populate users and assing roles
INSERT INTO users (display_name, username, password, role_id)
SELECT 'Keith Recruiter', 'keith_recruiter@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                     'Recruiter'
UNION ALL
SELECT 'John Candidate', 'john_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                         'Candidate'
UNION ALL
SELECT 'Brian Candidate', 'brian_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                         'Candidate'
UNION ALL
SELECT 'Elon Candidate', 'elon_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Mary Candidate', 'mary_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Eliah Candidate', 'eliah_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Alice Candidate', 'alice_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Bob Candidate', 'bob_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Lucy Candidate', 'lucy_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Carl Candidate', 'carl_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Stephen Candidate', 'stephen_candidate@ecorp.com', '12345', r.id FROM role r WHERE r
    .name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Jane Candidate', 'jane_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Janice Candidate', 'janice_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 'Monica Candidate', 'monica_candidate@ecorp.com', '12345', r.id FROM role r WHERE r
    .name =
                                                                                                      'Candidate'
UNION ALL
SELECT 'Nancy Manager', 'nancy_manager@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                       'Manager'
UNION ALL
SELECT 'Ralph Manager', 'ralph_manager@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                       'Manager';
--end populate users

CREATE TABLE IF NOT EXISTS job
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(100) UNIQUE NOT NULL,
    location VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    publish_date TIMESTAMP(6)
);

--populate jobs

INSERT INTO job (title, description, location, status, publish_date)
SELECT 'Scrum Master', 'You will help teams to be more and more agile', 'Lucca, Italy', 'OPEN',
       PARSEDATETIME('2024-03-01 14:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 'Junior Developer', 'If you want to start a tech career this is the job for you', 'Lucca, Italy', 'OPEN',
       PARSEDATETIME('2024-04-02 12:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 'Frontend Developer', 'To apply to this job you should know React, Angular, Vue, and 736479 other fancy ' ||
                             'frameworks',
       'Salerno, Italy', 'OPEN',
       PARSEDATETIME('2024-05-01 11:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 'Backend Developer', 'To apply to this job you should know Java,C#, C++ and 746832 other languages', 'Salerno,' ||
                                                                                                            ' Italy', 'OPEN',
       PARSEDATETIME('2025-01-01 10:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 'HR Recruiter', 'You will help to recruit new resources in a fast growing company.', 'Dublin, Ireland', 'OPEN',
       PARSEDATETIME('2024-07-12 09:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 'Career Manager', 'With this job you will guide the employees through their career.', 'Dublin, Ireland', 'OPEN',
       PARSEDATETIME('2024-06-12 09:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 'Senior Accountant', 'A job for managin the whole company account.', 'Dublin, Ireland', 'CLOSED',PARSEDATETIME
('2024-07-12 09:30:00', 'yyyy-MM-dd HH:mm:ss');

--end populate jobs

CREATE TABLE IF NOT EXISTS application
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status  VARCHAR(255),
    job_id  BIGINT REFERENCES job(id),
    user_id BIGINT REFERENCES users(id)
);

INSERT INTO application (status, job_id, user_id)
SELECT 'Waiting for feedback', j.id, u.id
FROM job j
         JOIN users u ON u.username = 'brian_candidate@ecorp.com'
WHERE j.title = 'Scrum Master';

--
CREATE TABLE IF NOT EXISTS application
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status  VARCHAR(255),
    job_id  BIGINT REFERENCES job(id),
    user_id BIGINT REFERENCES users(id)
);