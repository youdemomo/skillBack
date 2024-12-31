CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      find_password_answer VARCHAR(100),
                      status INT DEFAULT 1
);

