CREATE TABLE "user" (
                        user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        account_disabled BOOLEAN NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        email VARCHAR(255) NOT NULL,
                        first_name VARCHAR(255),
                        last_name VARCHAR(255),
                        password_hash VARCHAR(255) NOT NULL,
                        profile_pic VARCHAR(255),
                        role VARCHAR(255) CHECK (role IN ('ADMIN', 'EMPLOYEE', 'USER')) NOT NULL,
                        username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE post (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      user_id BIGINT,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "user"(user_id)
);

CREATE TABLE reply (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       content TEXT NOT NULL,
                       post_id BIGINT NOT NULL,
                       user_id BIGINT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES post(id),
                       CONSTRAINT fk_user_reply FOREIGN KEY (user_id) REFERENCES "user"(user_id)
);
