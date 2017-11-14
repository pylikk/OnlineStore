/**
  creating users and roles
 */

CREATE TABLE users (

  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL

);

CREATE TABLE roles (

  id SERIAL PRIMARY KEY,
  name VARCHAR(100)

);

CREATE TABLE user_roles (

  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY(role_id) REFERENCES roles(id),

  UNIQUE (role_id, user_id)

);

/**
  creating goods, goods_images, manufacturers, order_foods and orders
 */

CREATE TABLE manufacturers(

  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL

);

CREATE TABLE goods(

  id SERIAL PRIMARY KEY,
  manufacturer_id INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(10000) NOT NULL DEFAULT 'description',
  price INT NOT NULL,
  quantity INT NOT NULL,
  image VARCHAR(255),
  FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id)

    ON UPDATE CASCADE
    ON DELETE CASCADE

);


CREATE TABLE orders(

  id SERIAL PRIMARY KEY,
  goods_id INT NOT NULL,
  firstname VARCHAR(100) NULL,
  lastname VARCHAR(100) NULL,
  phone VARCHAR(45) NULL,
  order_date DATE,
  delivery_date DATE,
  address VARCHAR(255),
  order_status VARCHAR(45),
  order_info VARCHAR(255),

  FOREIGN KEY (goods_id) REFERENCES goods(id)

);

INSERT INTO users VALUES (1, 'customer', '$2a$11$LlhXjPrF3f72nUmGb//WVO3Bxs7mh0m3JLnwXHOPspQNK8D8ynqEK');
INSERT INTO users VALUES (2, 'admin', '$2a$11$DAj0a6lPpQbX0Cazfe6dVuDzdmSZOli7jKBpVoI.BC0NRjiHEqWlm');

INSERT INTO roles VALUES (1,'ROLE_CUSTOMER');
INSERT INTO roles VALUES (2,'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1,1);
INSERT INTO user_roles VALUES (2,2);
