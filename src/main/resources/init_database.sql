/**
  creating users and roles
 */

CREATE TABLE users (

  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL

) ENGINE=InnoDB;

CREATE TABLE roles (

  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100)

) ENGINE=InnoDB;

CREATE TABLE user_roles (

  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY(role_id) REFERENCES roles(id),

  UNIQUE (role_id, user_id)

) ENGINE=InnoDB;

/**
  creating goods, goods_images, manufacturers, order_foods and orders
 */

CREATE TABLE orders(

  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  goods_id INT NOT NULL,
  firstname VARCHAR(100) NULL,
  lastname VARCHAR(100) NULL,
  phone VARCHAR(45) NULL,
  order_date DATETIME,
  delivery_date DATE,
  address VARCHAR(255),
  order_status VARCHAR(45) NOT NULL DEFAULT 'new',
  order_info VARCHAR(255),

  FOREIGN KEY (goods_id) REFERENCES goods(id)

) ENGINE=InnoDB;

CREATE TABLE manufacturers(

  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL

) ENGINE=InnoDB;

CREATE TABLE goods(

  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  manufacturer_id INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(10000) NOT NULL DEFAULT 'description',
  price INT NOT NULL,
  quantity INT NOT NULL,
  image VARCHAR(255),
  FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id)

) ENGINE=InnoDB;

INSERT INTO users VALUES (1, 'customer', '$2a$11$LlhXjPrF3f72nUmGb//WVO3Bxs7mh0m3JLnwXHOPspQNK8D8ynqEK');
INSERT INTO users VALUES (2, 'manager', '$2a$11$C2t5J6zbym7aD/BH44907OrsOPYPts/5poXum1aK2VH1S8/XfhMQ.');
INSERT INTO users VALUES (3, 'admin', '$2a$11$DAj0a6lPpQbX0Cazfe6dVuDzdmSZOli7jKBpVoI.BC0NRjiHEqWlm');

INSERT INTO roles VALUES (1,'ROLE_CUSTOMER');
INSERT INTO roles VALUES (2,'ROLE_MANAGER');
INSERT INTO roles VALUES (3,'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1,1);
INSERT INTO user_roles VALUES (2,2);
INSERT INTO user_roles VALUES (3,3);
