DROP TABLE IF EXISTS member CASCADE;

CREATE TABLE member (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  name VARCHAR(255),
  PRIMARY KEY (id)
);