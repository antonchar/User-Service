DELETE FROM Users;
ALTER SEQUENCE Userservice_pk_sequence RESTART WITH 100;

-- Bcrypt hash for password: 123
INSERT INTO Users (id, email, pwd_hash, name, surname, age, role, blocked, creation_time) VALUES
  (1, 'admin@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Vasily', 'Pupkin', 29, 'ADMIN', FALSE, '2004-10-19 10:23:54'),
  (2, 'superadmin@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Super', 'Bratan', 48, 'SUPERADMIN', FALSE, '2005-12-31 10:23:54'),
  (3, 'user-blocked@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'John', 'Doe', 33, 'USER', TRUE, '2006-10-19 10:23:54'),
  (4, 'user@example.com', '$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq',
   'Avo', 'Pots', 22, 'USER', FALSE, '2007-12-31 10:23:54');