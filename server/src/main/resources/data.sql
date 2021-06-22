DROP TABLE IF EXISTS Cards;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  pin INT NOT NULL
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  balance DECIMAL DEFAULT 0,
  client_id  INT NOT NULL,
    foreign key (client_id) references Clients(id)
);
CREATE TABLE Cards (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  number VARCHAR,
  expired_date DATE,
  account_id  INT NOT NULL,
    foreign key (account_id) references Accounts(id)
);

INSERT INTO Clients (pin) VALUES
  (11),
  (22),
  (33);

INSERT INTO Accounts (balance,client_id) VALUES
  (100,1),
  (200,2),
  (300,3);

INSERT INTO Cards (expired_date,number,account_id) VALUES
  ('2023-01-01',1111,1),
  ('2022-01-01',2222,2),
  ('2020-01-01',3333,3);
