DROP ALL OBJECTS;


CREATE TABLE IF NOT EXISTS role
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO role (id, name) VALUES (1, 'Recruiter');
INSERT INTO role (id, name) VALUES (2, 'Candidate');
INSERT INTO role (id, name) VALUES (3, 'Manager');

CREATE TABLE IF NOT EXISTS users
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    display_name VARCHAR(50) UNIQUE NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT REFERENCES role(id) ON DELETE SET NULL
);
--populate roles

--populate users and assing roles
INSERT INTO users (id, display_name, username, password, role_id)
SELECT 1, 'Keith Recruiter', 'keith_recruiter@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                     'Recruiter'
UNION ALL
SELECT 2, 'John Candidate', 'john_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                         'Candidate'
UNION ALL
SELECT 3, 'Brian Candidate', 'brian_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                         'Candidate'
UNION ALL
SELECT 4, 'Elon Candidate', 'elon_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 5, 'Mary Candidate', 'mary_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 6, 'Eliah Candidate', 'eliah_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 7, 'Alice Candidate', 'alice_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 8, 'Bob Candidate', 'bob_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 9, 'Lucy Candidate', 'lucy_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 10, 'Carl Candidate', 'carl_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 11, 'Stephen Candidate', 'stephen_candidate@ecorp.com', '12345', r.id FROM role r WHERE r
    .name =
                                                                                                            'Candidate'
UNION ALL
SELECT 12, 'Jane Candidate', 'jane_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 13, 'Janice Candidate', 'janice_candidate@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                            'Candidate'
UNION ALL
SELECT 14, 'Monica Candidate', 'monica_candidate@ecorp.com', '12345', r.id FROM role r WHERE r
    .name =
                                                                                                      'Candidate'
UNION ALL
SELECT 15, 'Nancy Manager', 'nancy_manager@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                       'Manager'
UNION ALL
SELECT 16, 'Ralph Manager', 'ralph_manager@ecorp.com', '12345', r.id FROM role r WHERE r.name =
                                                                                                       'Manager';
--end populate users

CREATE TABLE IF NOT EXISTS job
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(100) UNIQUE NOT NULL,
    location VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    publish_date TIMESTAMP(6)
);

--populate jobs

INSERT INTO job (id, title, description, location, status, publish_date)
SELECT 1, 'Scrum Master', 'You will help teams to be more and more agile', 'Lucca, Italy', 'OPEN',
       PARSEDATETIME('2024-03-01 14:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 2, 'Junior Developer', 'If you want to start a tech career this is the job for you', 'Lucca, Italy', 'OPEN',
       PARSEDATETIME('2024-04-02 12:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 3, 'Frontend Developer', 'To apply to this job you should know React, Angular, Vue, and 736479 other fancy ' ||
                             'frameworks',
       'Salerno, Italy', 'OPEN',
       PARSEDATETIME('2024-05-01 11:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 4, 'Backend Developer', 'To apply to this job you should know Java,C#, C++ and 746832 other languages',
       'Salerno,' ||
                                                                                                            ' Italy', 'OPEN',
       PARSEDATETIME('2025-01-01 10:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 5, 'HR Recruiter', 'You will help to recruit new resources in a fast growing company.', 'Dublin, Ireland',
       'OPEN',
       PARSEDATETIME('2024-07-12 09:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 6, 'Career Manager', 'With this job you will guide the employees through their career.', 'Dublin, Ireland',
       'OPEN',
       PARSEDATETIME('2024-06-12 09:30:00', 'yyyy-MM-dd HH:mm:ss')
UNION ALL
SELECT 7, 'Senior Accountant', 'A job for managin the whole company account.', 'Dublin, Ireland', 'CLOSED',PARSEDATETIME
('2024-07-12 09:30:00', 'yyyy-MM-dd HH:mm:ss');

--end populate jobs

CREATE TABLE IF NOT EXISTS application
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    status  VARCHAR(255),
    job_id  BIGINT REFERENCES job(id),
    user_id BIGINT REFERENCES users(id)
);

INSERT INTO application (id, status, job_id, user_id)
SELECT 1, 'Waiting for feedback', j.id, u.id
FROM job j
         JOIN users u ON u.username = 'brian_candidate@ecorp.com'
WHERE j.title = 'Scrum Master';

--