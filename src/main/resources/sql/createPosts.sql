USE blogs;

CREATE TABLE posts(
    id int NOT NULL AUTO_INCREMENT,
    contents VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY(id)

);