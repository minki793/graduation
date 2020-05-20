DELETE FROM menu_items;
DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM users;
DELETE FROM restaurants;

ALTER SEQUENCE GLOBAL_HSQL RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User 1', 'user1@yandex.ru', '{noop}password1'),
('User 2', 'user2@yandex.ru', '{noop}password2'),
('User 3', 'user3@yandex.ru', '{noop}password3'),
('User 4', 'user4@yandex.ru', '{noop}password4'),
('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
('USER', 100000),
('USER', 100001),
('USER', 100002),
('USER', 100003),
('ADMIN', 100004),
('USER', 100004);


INSERT INTO restaurants (name) VALUES
('rest 1'),
('rest 2'),
('rest 3');


INSERT INTO menu_items (date, restaurant_id, name, price) VALUES
('2020-05-01', 100005, 'dish 5', 200),
('2020-05-01', 100005, 'dish 2', 220),
('2020-05-01', 100006, 'dish 4', 325),
('2020-05-01', 100006, 'dish 2', 300),
('2020-05-01', 100007, 'dish 1', 275),
('2020-05-01', 100007, 'dish 3', 440),

(TODAY(), 100005, 'dish 1', 265),
(TODAY(), 100005, 'dish 2', 220),
(TODAY(), 100005, 'dish 5', 200),
(TODAY(), 100006, 'dish 3', 515),
(TODAY(), 100006, 'dish 2', 300),
(TODAY(), 100006, 'dish 4', 375),
(TODAY(), 100007, 'dish 1', 275),
(TODAY(), 100007, 'dish 4', 325);

INSERT INTO votes (date, user_id, restaurant_id) VALUES
('2020-05-01', 100000, 100005),
('2020-05-01', 100001, 100005),
('2020-05-01', 100002, 100006),
('2020-05-01', 100003, 100007),

(TODAY(), 100000, 100006),
(TODAY(), 100001, 100007),
(TODAY(), 100002, 100007),
(TODAY(), 100003, 100007);

