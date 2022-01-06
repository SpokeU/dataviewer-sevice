DROP TABLE IF EXISTS query;
DROP TABLE IF EXISTS connection_parameters;
DROP TABLE IF EXISTS connection;

CREATE TABLE connection
(
    id serial,
    name character varying(255) UNIQUE,
    type character varying(255),
    CONSTRAINT connection_pkey PRIMARY KEY (id)
);

CREATE TABLE connection_parameters
(
	connection_id serial,
    name character varying(255),
    value character varying(255),
	FOREIGN KEY (connection_id) REFERENCES connection(id)
);

CREATE TABLE query
(
  id serial,
  name character varying(255) UNIQUE,
  query_string character varying,
  connection_id bigint NOT NULL,
  CONSTRAINT query_pkey PRIMARY KEY (id),
  FOREIGN KEY (connection_id) REFERENCES connection(id)
);