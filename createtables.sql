DROP TABLE Suggestion;
DROP TABLE CommentVote;
DROP TABLE ThreadVote;
DROP TABLE UserComment;
DROP TABLE Thread;
DROP TABLE Poster;
DROP TABLE Page;

CREATE TABLE Page (
	TopicName VARCHAR2(100),
	AdminPageFlag INTEGER,
	PRIMARY KEY (TopicName));

CREATE TABLE Poster (
	PosterName VARCHAR2(50),
	Reputation INTEGER,
	numberOfPages INTEGER,
  AdminID INTEGER,
	PRIMARY KEY (PosterName));

CREATE TABLE Thread (
	ThreadID INTEGER,
	Title CHAR(50),
	Text VARCHAR2(4000),
	Time CHAR(12),
	voteNum INTEGER,
	isLockedFlag INTEGER,
	TopicName VARCHAR2(100),
	PosterName VARCHAR2(50),
	PRIMARY KEY (ThreadID),
	FOREIGN KEY (TopicName) REFERENCES Page,
	FOREIGN KEY (PosterName) REFERENCES Poster);

CREATE TABLE UserComment (
	CommID INTEGER,
	Text VARCHAR2(4000),
	voteNum INTEGER,
	Time TIMESTAMP,
	PosterName VARCHAR2(50),
	ThreadID INTEGER,
	PRIMARY KEY(CommID),
	FOREIGN KEY (ThreadID) REFERENCES Thread,
	FOREIGN KEY (PosterName) REFERENCES Poster);

CREATE TABLE ThreadVote (
	PosterName VARCHAR2(50),
	UserVotedFlag INTEGER,
	ThreadID	INTEGER,
	PRIMARY KEY (PosterName, ThreadID),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (ThreadID) REFERENCES Thread);

CREATE TABLE CommentVote (
	PosterName VARCHAR2(100),
	UserVotedFlag INTEGER,
	ThreadID	INTEGER,
  CommID INTEGER,
	PRIMARY KEY (PosterName, CommID),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (CommID) REFERENCES UserComment);
	

CREATE TABLE Suggestion (
	SugID INTEGER,
	Text VARCHAR2(4000),
	PosterName VARCHAR2(100),
	TopicName VARCHAR2(100),
	PRIMARY KEY (SugID, PosterName, TopicName),
	FOREIGN KEY (PosterName) REFERENCES Poster,
	FOREIGN KEY (TopicName) REFERENCES Page);




