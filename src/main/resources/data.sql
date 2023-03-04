DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id serial,
    name character varying(255),
    surname character varying(255),
    age int,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

INSERT INTO users VALUES (1, 'Alex', 'Myshko', 27);
INSERT INTO users VALUES (2, 'John', 'Travolta', 90);

INSERT INTO connections (name, type, details) VALUES ('DataViewer', 'POSTGRES', '{"host": "localhost", "port": 5432, "database": "dataviewer", "password": "dataviewer", "username": "dataviewer"}');
INSERT INTO connections (name, type, details) VALUES ('Invalid', 'POSTGRES', '{"host": "uknnownHost", "port": 5432, "database": "db", "password": "pass", "username": "user"}');
