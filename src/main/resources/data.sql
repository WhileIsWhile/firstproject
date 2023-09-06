/*기존 데이터*/
insert into article(title,content) values ('111111','111111');
insert into article(title,content) values ('211111','111111');
insert into article(title,content) values ('311111','111111');

/*Article 더미 데이터*/
insert into article(title,content) values ('영화 머임?????','기기');
insert into article(title,content) values ('음식 머임?????','댓 기');
insert into article(title,content) values ('노래 머임?????','댓 기억');

/*Comment 더미 데이터*/
insert into comment(article_id,nickname,body) values (4, '1번님','킹콩 1시간 동안 은 개노젬');
insert into comment(article_id,nickname,body) values (4, '2번님','반지의 제욍 4시간 너무김');
insert into comment(article_id,nickname,body) values (4, '3번님','타겟 영화 돈아까움 ㅇㅈ?');

insert into comment(article_id,nickname,body) values (5,'1-1번님','오코노미야끼 ㅇ?');
insert into comment(article_id,nickname,body) values (5,'1-2번님','우동 ㅇ?');
insert into comment(article_id,nickname,body) values (5,'1-3번님','라멩? ㅇ?');

insert into comment(article_id,nickname,body) values (6,'2-1번님','아라리');
insert into comment(article_id,nickname,body) values (6,'2-2번님','야래향');
insert into comment(article_id,nickname,body) values (6,'2-3번님','화조도');