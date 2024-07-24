CREATE TABLE User (
                      userid BIGINT AUTO_INCREMENT PRIMARY KEY,
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
                      userid BIGINT,
                      FOREIGN KEY (userid) REFERENCES User (userid)
);



INSERT INTO user (userid, username, email, password) VALUES
                                                     (1, 'john1', 'john@example.com', '1231'),
                                                     (2, 'jane2', 'jane@example.com', '1232'),
                                                     (3, 'doe3', 'doe@example.com', '1233');

INSERT INTO task (title, description, status, userid) VALUES
                                                           ('새로운 작업1', '이것은 새로운 작업에 대한 설명입니다. 1', '진행 중', 1),
                                                           ('새로운 작업2', '이것은 새로운 작업에 대한 설명입니다. 2', '진행 중', 2),
                                                           ('새로운 작업3', '이것은 새로운 작업에 대한 설명입니다. 3', '진행 중', 3);

select * from task;
select * from user;

# drop table task;
# drop table user;