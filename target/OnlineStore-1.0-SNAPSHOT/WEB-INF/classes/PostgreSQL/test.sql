CREATE TABLE manufacturerss(

  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL

);

INSERT INTO manufacturerss(name,country) VALUES ('Versace','Italy');
INSERT INTO manufacturerss(name,country) VALUES ('Lanvin','France');
INSERT INTO manufacturerss(name,country) VALUES ('Kenzo','France');