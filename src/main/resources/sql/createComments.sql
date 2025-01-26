USE blogs;

CREATE TABLE comments(
    id int NOT NULL AUTO_INCREMENT,
    contents VARCHAR(255) NOT NULL,
    user_id int,
    post_id int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (post_id) REFERENCES users(id),
    PRIMARY KEY(id)

);