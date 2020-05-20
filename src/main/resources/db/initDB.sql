DROP TABLE menu_items IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE votes IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP SEQUENCE GLOBAL_HSQL IF EXISTS;

CREATE SEQUENCE GLOBAL_HSQL AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_HSQL PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);


CREATE TABLE restaurants
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_HSQL PRIMARY KEY,
    name        VARCHAR(255)            NOT NULL
);

CREATE UNIQUE INDEX restaurants_unique_name_idx
    ON restaurants (name);

CREATE TABLE menu_items
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_HSQL PRIMARY KEY,
    name            VARCHAR(255)                NOT NULL,
    date            DATE DEFAULT TODAY()        NOT NULL,
    restaurant_id   INTEGER                     NOT NULL,
    price           INTEGER                     NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX menu_items_unique_date_restaurant_name_idx
    ON menu_items (date, restaurant_id, name);

CREATE TABLE votes
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_HSQL PRIMARY KEY,
    date            DATE DEFAULT TODAY()    NOT NULL,
    user_id         INTEGER                 NOT NULL,
    restaurant_id   INTEGER                 NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX votes_unique_date_user_idx
    ON votes (date, user_id);