DROP TABLE users;

CREATE TABLE users
(
    id bigint NOT NULL,
    name character varying(255),
    surname character varying(255),
    age int,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

INSERT INTO users VALUES (1, 'Alex', 'Myshko', 27);
INSERT INTO users VALUES (2, 'John', 'Travolta', 90);