DROP TABLE Suggestion;
DROP TABLE CommentVote;
DROP TABLE ThreadVote;
DROP TABLE UserComment;
DROP TABLE Thread;
DROP TABLE Page;
DROP TABLE Poster;


CREATE TABLE Poster (
	PosterName VARCHAR2(50),
	Reputation INTEGER,
	numberOfPages INTEGER,
  AdminID INTEGER,
	PRIMARY KEY (PosterName));

CREATE TABLE Page (
	TopicName VARCHAR2(100),
  PosterName VARCHAR2(50) NOT NULL,
	PRIMARY KEY (TopicName),
  FOREIGN KEY (PosterName) REFERENCES Poster);

CREATE TABLE Thread (
	ThreadID INTEGER,
	Title CHAR(50),
	Text VARCHAR2(4000),
	Time TIMESTAMP,
	voteNum INTEGER,
	isLockedFlag INTEGER,
	TopicName VARCHAR2(100),
	PosterName VARCHAR2(50) NOT NULL,
	PRIMARY KEY (ThreadID),
	FOREIGN KEY (TopicName) REFERENCES Page,
	FOREIGN KEY (PosterName) REFERENCES Poster);

CREATE TABLE ThreadVote (
	PosterName VARCHAR2(50),
	UserVotedFlag INTEGER,
	ThreadID	INTEGER,
	PRIMARY KEY (PosterName, ThreadID),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (ThreadID) REFERENCES Thread);

CREATE TABLE UserComment (
	CommID INTEGER,
	Text VARCHAR2(4000),
	voteNum INTEGER,
	Time TIMESTAMP,
	PosterName VARCHAR2(50) NOT NULL,
	ThreadID INTEGER NOT NULL,
	PRIMARY KEY(CommID),
	FOREIGN KEY (ThreadID) REFERENCES Thread,
	FOREIGN KEY (PosterName) REFERENCES Poster);


CREATE TABLE CommentVote (
	PosterName VARCHAR2(50),
	UserVotedFlag INTEGER,
	ThreadID	INTEGER,
  CommID INTEGER,
	PRIMARY KEY (PosterName, CommID),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (CommID) REFERENCES UserComment);
	

CREATE TABLE Suggestion (
	SugID INTEGER,
	Text VARCHAR2(4000),
	PosterName VARCHAR2(100) NOT NULL,
	TopicName VARCHAR2(100),
	PRIMARY KEY (SugID, PosterName, TopicName),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (TopicName) REFERENCES Page);

