CREATE TYPE ROLE_ENUM AS ENUM('ROLE_USER', 'ROLE_ADMIN');

CREATE TABLE users (
  id SERIAL NOT NULL PRIMARY KEY,
  password VARCHAR(60) NOT NULL,
  email VARCHAR(60) NOT NULL,
  name VARCHAR(60) NOT NULL,
  enabled boolean NOT NULL default true,
  role ROLE_ENUM NOT NULL default 'ROLE_USER'
);

CREATE TYPE STATE_ENUM AS ENUM('NOT_COMPLETED', 'COMPLETED');

CREATE TABLE orders (
  id SERIAL NOT NULL PRIMARY KEY,
  good VARCHAR(60) NOT NULL,
  destination VARCHAR(60) NOT NULL,
  ordered_at timestamp NOT NULL,
  ordered_to timestamp NOT NULL,
  state STATE_ENUM NOT NULL default 'NOT_COMPLETED',
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO users(password, email, name, enabled, role)
	VALUES ('$2a$10$tNttum7fcQuJl6cF37Zdw.imRM7rf1znQnNUF4sdSw9z2so7BErqW', 'urmat@gmail.com', 'Urmat', true, 'ROLE_ADMIN'),
            ('$2a$10$xgKeawScFKNam03sOmIrbeTXwvx99SM47bJBDELlHUp1dXSSmppXG','geralt@gmail.com','Geralt', true,'ROLE_USER'),
            ('$2a$10$eO/nXfowPeNhJcuplXtqPuuYP2/CNJgWbcSs/jP8Y5v8an5mKVLTK','triss@gmail.com','Triss', false,'ROLE_USER'),
            ('$2a$10$odLNQf3OKnKVRGe3k2DtWet4OkPyOWnH0c7gk2JgJT2B4D8YC83F2','ada@gmail.com','Ada', true,'ROLE_USER');

