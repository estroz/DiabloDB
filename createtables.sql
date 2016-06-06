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
	FOREIGN KEY (PosterName) REFERENCES Poster
	ON DELETE SET NULL);

CREATE TABLE Thread (
	ThreadID INTEGER,
	Title VARCHAR2(50),
	Text VARCHAR2(4000),
	Time TIMESTAMP,
	voteNum INTEGER,
	isLockedFlag INTEGER,
	TopicName VARCHAR2(100) NOT NULL,
	PosterName VARCHAR2(50) NOT NULL,
	PRIMARY KEY (ThreadID),
	FOREIGN KEY (TopicName) REFERENCES Page
	ON DELETE CASCADE,
	FOREIGN KEY (PosterName) REFERENCES Poster
	ON DELETE SET NULL);

CREATE TABLE ThreadVote (
	PosterName VARCHAR2(50),
	UserVotedFlag INTEGER,
	ThreadID INTEGER,
	PRIMARY KEY (PosterName, ThreadID),
	FOREIGN KEY (PosterName) REFERENCES Poster
	ON DELETE CASCADE,
	FOREIGN KEY (ThreadID) REFERENCES Thread
	ON DELETE CASCADE);

CREATE TABLE UserComment (
	CommID INTEGER,
	Text VARCHAR2(4000),
	voteNum INTEGER,
	Time TIMESTAMP,
	PosterName VARCHAR2(50) NOT NULL,
	ThreadID INTEGER NOT NULL,
	PRIMARY KEY (CommID),
	FOREIGN KEY (PosterName) REFERENCES Poster
	ON DELETE SET NULL,
	FOREIGN KEY (ThreadID) REFERENCES Thread
	ON DELETE CASCADE);

CREATE TABLE CommentVote (
	PosterName VARCHAR2(50),
	UserVotedFlag INTEGER,
	CommID INTEGER,
	PRIMARY KEY (PosterName, CommID),
	FOREIGN KEY (PosterName) REFERENCES Poster 
	ON DELETE CASCADE,
	FOREIGN KEY (CommID) REFERENCES UserComment
	ON DELETE CASCADE);

CREATE TABLE Suggestion (
	SugID INTEGER,
	Text VARCHAR2(4000),
	PosterName VARCHAR2(50) NOT NULL,
	TopicName VARCHAR2(100),
	PRIMARY KEY (SugID),
	FOREIGN KEY (PosterName) REFERENCES Poster 
	ON DELETE SET NULL ,
	FOREIGN KEY (TopicName) REFERENCES Page
	ON DELETE SET NULL); 

insert into Poster values ('Eric', 0, 0, null);
insert into Poster values ('Sam', 0, 0, null);
insert into Poster values ('Brett', 0, 0, null);
insert into Poster values ('Josh', 0, 0, null);
Insert into Poster values ('Diablo', 0, 0, null);

insert into Page values ('EricPage', 'Eric');
insert into Page values ('SamPage', 'Sam');
insert into Page values ('BrettPage', 'Brett');
insert into Page values ('JoshPage', 'Josh');
insert into Page values ('DiabloPage', 'Diablo');

insert into Thread values (0, 'EricThread', 'ThreadText1', '2016-06-01', 0, 0, 'EricPage', 'Eric');
insert into Thread values (1, 'SamThread', 'ThreadText2', '2016-06-01', 0, 0, 'SamPage', 'Sam');
insert into Thread values (2, 'BrettThread', 'ThreadText3', '2016-06-01', 0, 0, 'BrettPage', 'Brett');
insert into Thread values (3, 'JoshThread', 'ThreadText4', '2016-06-01', 0, 0, 'JoshPage', 'Josh');
insert into Thread values (4, 'DiabloThread', 'ThreadText5', '2016-06-01', 0, 0, 'DiabloPage', 'Diablo');

insert into ThreadVote values ('Eric', 1, 0);
insert into ThreadVote values ('Sam', 1, 1);
insert into ThreadVote values ('Brett', 1, 2);
insert into ThreadVote values ('Josh', 1, 3);
insert into ThreadVote values ('Diablo', 1, 4);

insert into UserComment values (0, 'CommentText1', 0, '2016-06-01', 'Eric', 0);
insert into UserComment values (1, 'CommentText2', 0, '2016-06-01', 'Sam', 1);
insert into UserComment values (2, 'CommentText3', 0, '2016-06-01', 'Brett', 2);
insert into UserComment values (3, 'CommentText4', 0, '2016-06-01', 'Josh', 3);
insert into UserComment values (4, 'CommentText5', 0, '2016-06-01', 'Diablo', 4);

insert into CommentVote values ('Eric', 1, 0);
insert into CommentVote values ('Sam', 1, 1);
insert into CommentVote values ('Brett', 1, 2);
insert into CommentVote values ('Josh', 1, 3);
insert into CommentVote values ('Diablo', 1, 4);

insert into Suggestion values (0, 'Suggestion1', 'Eric', 'EricPage');
insert into Suggestion values (1, 'Suggestion2', 'Sam', 'SamPage');
insert into Suggestion values (2, 'Suggestion3', 'Brett', 'BrettPage');
insert into Suggestion values (3, 'Suggestion4', 'Josh', 'JoshPage');
insert into Suggestion values (4, 'Suggestion5', 'Diablo', 'DiabloPage');
