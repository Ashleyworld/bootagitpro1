CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL
);

-- Task 테이블 생성
CREATE TABLE Task (
                      taskid BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255),
                      description TEXT,
                      status VARCHAR(50),
                      user_id BIGINT,
                      FOREIGN KEY (user_id) REFERENCES User (id)
);

INSERT INTO `Task` (title, description, status, user_id) VALUES
                                                             ('Task 1', 'Description for task 1','user', 1),
                                                             ('Task 2', 'Description for task 2','user', 2),
                                                             ('Task 3', 'Description for task 3','user', 2);

INSERT INTO `User` (username, password, email)
VALUES
    ('이선생', '1234', 'user@bootagit.com'),
    ('이선생', '1234', 'user@bootagit.com'),




select * from task;
select * from user;