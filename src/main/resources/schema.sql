DROP TABLE IF EXISTS queries;
DROP TABLE IF EXISTS connections;

CREATE TABLE connections
(
    id serial,
    name character varying(255) UNIQUE,
    type character varying(255),
    details jsonb,
    CONSTRAINT connection_pkey PRIMARY KEY (id)
);

CREATE TABLE queries
(
  id serial,
  name character varying(255) UNIQUE,
  query_string character varying,
  connection_id int,
  CONSTRAINT query_pkey PRIMARY KEY (id),
  FOREIGN KEY (connection_id) REFERENCES connections(id) ON DELETE SET NULL
);