DROP DATABASE IF EXISTS SmartPiano;
CREATE DATABASE SmartPiano;
USE SmartPiano;

DROP TABLE IF EXISTS System;
CREATE TABLE IF NOT EXISTS System(
  Name VARCHAR(255),
  DATE DATE,
  TotalOfUser INT,
  PRIMARY KEY (Name)
);

DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User(
  Name VARCHAR(255),
  Photo_Path VARCHAR(255),
  Password char(32),
  Email CHAR(255),
  PRIMARY KEY (Name)
);

DROP TABLE IF EXISTS Friendship;
CREATE TABLE IF NOT EXISTS Friendship(
  Name1 VARCHAR(255),
  Name2 VARCHAR(255),
  PRIMARY KEY (Name1,Name2),
  FOREIGN KEY (Name2) REFERENCES User(Name),
  FOREIGN KEY (Name1) REFERENCES User(Name)
);

DROP TABLE IF EXISTS Song;
CREATE TABLE IF NOT EXISTS Song(
  SongID SERIAL,
  Name VARCHAR(255),
  Duration INT,
  Description TEXT,
  Autor VARCHAR(255),
  Plays INT,
  File_Path VARCHAR(255),
  Creator VARCHAR(255),
  PRIMARY KEY (SongID),
  FOREIGN KEY (Autor) REFERENCES User(Name)
);

