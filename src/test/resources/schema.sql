DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_roles;

create table user(
    id BIGINT IDENTITY PRIMARY KEY,
    name varchar(45) NOT NULL,
    email varchar(125) NOT NULL,
    password varchar(125) NOT NULL
);

create table user_roles(
    user_id BIGINT,
    roles INT(11) NOT NULL
);

ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES user(id);

INSERT INTO user (id, name, email, password) VALUES (1, 'test_user1', 'test_user1@bandme.com', '$2y$10$4gavJ2u2P5lTj75.HC0qT.MCFhltSzYbGVJapYeOFQh0ucudJIr7C' ); -- PW: 123456
INSERT INTO user (id, name, email, password) VALUES (2, 'test_admin1', 'test_admin1@bandme.com', '$2y$10$4gavJ2u2P5lTj75.HC0qT.MCFhltSzYbGVJapYeOFQh0ucudJIr7C' ); -- PW: 123456

INSERT INTO user_roles (user_id, roles) VALUES ( 1, 1 ); -- ROLE: User
INSERT INTO user_roles (user_id, roles) VALUES ( 2, 1 ); -- ROLE: User
INSERT INTO user_roles (user_id, roles) VALUES ( 2, 2 ); -- ROLE: Moderator
INSERT INTO user_roles (user_id, roles) VALUES ( 2, 3 ); -- ROLE: Admin
