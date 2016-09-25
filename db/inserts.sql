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

insert into CommentVote values ('Eric', 1, 0, 0);
insert into CommentVote values ('Sam', 1, 1, 1);
insert into CommentVote values ('Brett', 1, 2, 2);
insert into CommentVote values ('Josh', 1, 3, 3);
insert into CommentVote values ('Diablo', 1, 4, 4);

insert into Suggestion values (0, 'Suggestion1', 'Eric', 'EricPage');
insert into Suggestion values (1, 'Suggestion2', 'Sam', 'SamPage');
insert into Suggestion values (2, 'Suggestion3', 'Brett', 'BrettPage');
insert into Suggestion values (3, 'Suggestion4', 'Josh', 'JoshPage');
insert into Suggestion values (4, 'Suggestion5', 'Diablo', 'DiabloPage');

