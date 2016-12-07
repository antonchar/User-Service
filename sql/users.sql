# Create User table
CREATE TABLE User (
  id INT(8) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(25),
  age INT,
  isAdmin BIT,
  createDate TIMESTAMP
);

# Fill the table with data
INSERT INTO User (name, age, isAdmin, createDate) VALUES
  ('Vasily Pupkin', 29, 0, NOW()),
  ('Superbratan', 48, 1, NOW()),
  ('John Doe', 34, 0, NOW()),
  ('Avo Pots', 22, 1, NOW()),
  ('Tristo Ergs', 56, 0, NOW()),
  ('Ben Toom', 33, 1, NOW()),
  ('Bubi Zilberstein', 43, 0, NOW()),
  ('Edgar Smith', 17, 0, NOW()),
  ('badass', 90, 1, NOW()),
  ('Ivan Ivanovich Ivanov', 38, 0, NOW()),
  ('Mark Kerames', 28, 1, NOW()),
  ('Peter Snow', 56, 0, NOW());

# Rename table fields
ALTER TABLE User CHANGE isAdmin admin BIT;
ALTER TABLE User CHANGE createDate creation_date TIMESTAMP;

# Change charset to utf-8 (for Russian language)
ALTER TABLE User MODIFY COLUMN name VARCHAR(25) CHARACTER SET utf8 COLLATE utf8_general_ci;