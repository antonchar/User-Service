CREATE TABLE User (
  id INT(8) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(25),
  age INT,
  isAdmin BIT,
  createDate TIMESTAMP
);

INSERT INTO User (name, age, isAdmin, createDate) VALUES
  ('Vasily Pupkin', 29, 0, NOW()),
  ('Superbratan', 48, 1, NOW()),
  ('John Doe', 34, 0, NOW()),
  ('Avo Pots', 22, 1, NOW()),
  ('Tisto Ergs', 56, 0, NOW());