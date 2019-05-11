SET @@session.time_zone = '+00:00';
SET @@global.time_zone = '+00:00';


DROP DATABASE IF EXISTS SmartPiano;
CREATE DATABASE SmartPiano;
USE SmartPiano;

DROP TABLE IF EXISTS Syst;
CREATE TABLE IF NOT EXISTS Syst(
  ID INTEGER AUTO_INCREMENT,
  Name VARCHAR(255),
  Date DATE,
  TotalOfUsers INT,
  PRIMARY KEY (ID)
);


DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User(
  Name VARCHAR(255),
  #Photo BLOB,
  Photo_Path VARCHAR(255),
  Password char(32),
  Email CHAR(255),
  PRIMARY KEY (Name)
);

DROP TABLE IF EXISTS Friendship;
CREATE TABLE IF NOT EXISTS Friendship(
  Id INTEGER AUTO_INCREMENT,
  Name1 VARCHAR(255),
  Name2 VARCHAR(255),
  PRIMARY KEY (Id),
  FOREIGN KEY (Name1) REFERENCES User(Name),
  FOREIGN KEY (Name2) REFERENCES User(Name)
);

DROP TABLE IF EXISTS Song;
CREATE TABLE IF NOT EXISTS Song(
  SongID INT AUTO_INCREMENT,
  Name VARCHAR(255),
  Duration INT,
  Description TEXT,
  Author VARCHAR(255),
  Plays INT,
  File_Path VARCHAR(255),
  SystemID INT,
  PRIMARY KEY (SongID),
  FOREIGN KEY (Author) REFERENCES User(Name),
  FOREIGN KEY (SystemID) REFERENCES Syst(ID)
);


SELECT * FROM Song;
SELECT * FROM Friendship;

truncate Friendship;

DELIMITER  $$
DROP    PROCEDURE    IF    EXISTS updateFriends  $$
CREATE PROCEDURE  updateFriends ()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE nam1 VARCHAR(255);
  DECLARE nam2 VARCHAR(255);
  DECLARE cur1 CURSOR FOR (SELECT f.Name1, f.Name2 FROM Friendship AS f WHERE ((SELECT (COUNT(*)) FROM Friendship AS f2 WHERE f.Name1 = f2.Name2 AND f.Name2 = f2.Name1) = 0));
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;




  OPEN cur1;
   bucle: LOOP


      FETCH cur1 INTO nam1, nam2;

      IF done=1 THEN
         LEAVE bucle;
      ELSE
         INSERT INTO Friendship (Name1, Name2) VALUES (nam2,nam1);
      END IF;

    END LOOP bucle;
    CLOSE cur1;
END $$
DELIMITER ;




INSERT INTO Syst (ID, Name, Date, TotalOfUsers) VALUES (1,'System','2019-04-29',30);
INSERT INTO Syst (ID, Name, Date, TotalOfUsers) VALUES (2,'System','2019-04-30',69);
INSERT INTO Syst (ID, Name, Date, TotalOfUsers) VALUES (3,'System','2019-05-01',50);
INSERT INTO Syst (ID, Name, Date, TotalOfUsers) VALUES (4,'System','2019-10-04',35);
INSERT INTO Syst (ID, Name, Date, TotalOfUsers) VALUES (5,'System','2019-10-06',38);
INSERT INTO User (Name, Photo_Path, Password, Email) VALUES ('josep','hola.txt','roig','joseproig1999');
INSERT INTO User (Name, Photo_Path, Password, Email) VALUES ('pepe','holas.txt','roig','peperoig1999');
INSERT INTO Song (Name, Duration, Description,Plays, File_Path,SystemID) VALUES ('pepe',12,'pepe',120,'pepe.mp3',1);
INSERT INTO Song (Name, Duration, Description,Plays, File_Path, Author) VALUES ('todays class',12,'pepe',56,'pepe.mp3','pepe');
INSERT INTO Song (Name, Duration, Description,Plays, File_Path, Author) VALUES ('josepsSong',12,'pepe',89,'pepe.mp3','pepe');
INSERT INTO Song (Name, Duration, Description,Plays, File_Path, Author) VALUES ('classo',12,'pepe',91,'pepe.mp3','josep');
INSERT INTO Song (Name, Duration, Description,Plays, File_Path, Author) VALUES ('pepeSong',12,'pepe',91,'pepe.mp3','josep');
INSERT INTO Song (Name, Duration, Description,Plays, File_Path, Author) VALUES ('FlamencoGitano',12,'pepe',57,'pepe.mp3','pepe');




DROP USER IF EXISTS 'noAlias'@'localhost';
DROP USER IF EXISTS 'normalUser'@'localhost';
DROP USER IF EXISTS 'admin'@'localhost';

CREATE USER 'noAlias'@'localhost' IDENTIFIED BY 'password1999';
CREATE USER 'normalUser'@'localhost' IDENTIFIED BY 'normalUserPassword1999';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'adminPassword1999';

GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';
GRANT SELECT  ON SmartPiano. * TO 'normalUser'@'localhost';
GRANT INSERT  ON SmartPiano. * TO 'normalUser'@'localhost';
GRANT DELETE  ON SmartPiano. * TO 'normalUser'@'localhost';
GRANT UPDATE ON SmartPiano. * TO 'normalUser'@'localhost';
GRANT INSERT ON SmartPiano. * TO 'noAlias'@'localhost';
GRANT SELECT ON SmartPiano. * TO 'noAlias'@'localhost';
#Permission to allow to a user to execute a procedure
GRANT EXECUTE ON PROCEDURE SmartPiano.updateFriends TO 'normalUser'@'localhost';


SELECT * FROM User;

