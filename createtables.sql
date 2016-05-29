DROP TABLE Page;
DROP TABLE Poster;
DROP TABLE Thread;
DROP TABLE UserComment;
DROP TABLE ThreadVote;
DROP TABLE CommentVote;
DROP TABLE Admin;
DROP TABLE Suggestion;

CREATE TABLE Page (
	TopicName CHAR(100),
	AdminPageFlag INTEGER,
	PRIMARY KEY (TopicName));

CREATE TABLE Poster (
	PosterName CHAR(50),
	Reputation INTEGER,
	numberOfPages INTEGER,
	PRIMARY KEY (PosterName));

CREATE TABLE Thread (
	ThreadID INTEGER,
	Title CHAR(50),
	Text VARCHAR2(4000),
	Time CHAR(12),
	voteNum INTEGER,
	isLockedFlag INTEGER,
	TopicName CHAR(100),
	PosterName CHAR(50),
	PRIMARY KEY (Title, ThreadID, TopicName),
	FOREIGN KEY (TopicName) REFERENCES Page,
	FOREIGN KEY (PosterName) REFERENCES Poster);

CREATE TABLE UserComment (
	CommID INTEGER,
	Text VARCHAR2(4000),
	voteNum INTEGER,
	Time CHAR(12),
	PosterName CHAR(50),
	ThreadID INTEGER,
	PRIMARY KEY(CommID),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (ThreadID) REFERENCES Thread);

CREATE TABLE ThreadVote (
	PosterName CHAR(50),
	UserVotedFlag INTEGER,
	ThreadID	INTEGER,
	PRIMARY KEY (PosterName, ThreadID),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (ThreadID) REFERENCES Thread);

CREATE TABLE CommentVote (
	PosterName CHAR(50),
	UserVotedFlag INTEGER,
	ThreadID	INTEGER,
  CommID INTEGER,
	PRIMARY KEY (PosterName, CommID),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (CommID) REFERENCES UserComment);
	

CREATE TABLE Admin (
	AdminName CHAR(50) PRIMARY KEY);

-- TODO :  admin controls tables


CREATE TABLE Suggestion (
	SugID INTEGER,
	Text VARCHAR2(4000),
	PosterName CHAR(50),
	TopicName CHAR(100),
	PRIMARY KEY (SugID, PosterName, TopicName),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (TopicName) REFERENCES Page);



