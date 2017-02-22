DELETE FROM Users;
ALTER SEQUENCE Userservice_pk_sequence RESTART WITH 100;

-- Bcrypt hash for password: 123
INSERT INTO Users (id, email, pwd_hash, name, surname, age, role, blocked, creation_time) VALUES
  (1, 'vasilypup@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Vasily', 'Pupkin', 29, 'ADMIN', FALSE, '2004-10-19 10:23:54'),
  (2, 'superbratan@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Super', 'Bratan', 48, 'SUPERADMIN', FALSE, '2004-12-31 10:23:54'),
  (3, 'johnnny@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'John', 'Doe', 33, 'USER', TRUE , '2004-10-19 10:23:54'),
  (4, 'avo.pots@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Avo', 'Pots', 22, 'SUPERADMIN', FALSE, '2004-12-31 10:23:54'),
  (5, 'tracktoristo@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Tristo', 'Ergs', 56, 'USER', FALSE , NOW()),
  (6, 'toroee@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Benjamin', 'Toom', 54, 'USER', FALSE , NOW()),
  (7, 'uncle-bubi@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Bubi', 'Zilberstein', 34, 'ADMIN', TRUE, NOW()),
  (8, 'young_2345@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Edgar', 'Smith', 17, 'USER', TRUE, NOW()),
  (9, 'badass@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'BAD', 'ASS', 90, 'SUPERADMIN', FALSE , NOW()),
  (10, 'iii@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Ivan Ivanych', 'Ivanov', 38, 'USER', FALSE , NOW()),
  (11, 'marko@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Mark', 'Kerames', 28, 'USER', TRUE , NOW()),
  (12, 'peter@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
    'Peter', 'Snow', 56, 'USER', FALSE , NOW());
