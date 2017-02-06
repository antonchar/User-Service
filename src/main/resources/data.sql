DELETE FROM Users;
ALTER SEQUENCE Userservice_pk_sequence RESTART WITH 100;

INSERT INTO Users (id, name, age, admin, creation_time) VALUES
  (1, 'Vasily Pupkin', 29, FALSE , '2004-10-19 10:23:54'),
  (2, 'Superbratan', 48, TRUE, '2004-12-31 10:23:54'),
  (3, 'John Doe', 34, FALSE , '2007-10-19 10:23:54'),
  (4,'Avo Pots', 22, TRUE, '2015-10-19 10:23:54'),
  (5, 'Tristo Ergs', 56, FALSE, NOW()),
  (6, 'Ben Toom', 33, TRUE, NOW()),
  (7, 'Bubi Zilberstein', 43, FALSE, NOW()),
  (8, 'Edgar Smith', 17, FALSE, NOW()),
  (9, 'badass', 90, TRUE, NOW()),
  (10, 'Ivan Ivanovich Ivanov', 38, FALSE, NOW()),
  (11, 'Mark Kerames', 28, TRUE, NOW()),
  (12, 'Peter Snow', 56, FALSE, NOW());