CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL
);

CREATE TABLE Task (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      status VARCHAR(50),
                      user_id BIGINT,
                      FOREIGN KEY (user_id) REFERENCES User(id)
);

/*
user_id 의 존재 이유?

BIGINT 의 의미?

*/