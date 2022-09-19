--1234
insert into user (email, nick_name, password, name, created_at, created_by, modified_at, modified_by) values
('lhs@gmail', 'lhs', '$2a$12$Jpx.QbbyiIOYnBtFd8/kfu9dEPe6Ag5hjVvBMpJwkXLnW//qIGvvC', 'leehyunseok', now(), 'lhs@gmail', now(), 'lhs@gmail');
--1234
insert into user (email, nick_name, password, name, created_at, created_by, modified_at, modified_by) values
('lhs2@gmail', 'lhs2', '$2a$12$74V5JbG/mxV.0r0PItoENO.tLiJz0ZW15ECdYp1.GznBmuu33/vo.', 'leehyunseok2', now(), 'lhs2@gmail', now(), 'lhs2@gmail');

insert into board (title, content, user_id, created_at, created_by, modified_at, modified_by) values
("test1", "이것은 테스트 1 문장입니다.!!!!!", 1, now(), "lhs@gmail", now(), "lhs@gmail");
insert into board (title, content, user_id, created_at, created_by, modified_at, modified_by) values
("test2", "이것은 테스트 2 문장입니다.!!!!!", 2, now(), "lhs2@gmail", now(), "lhs2@gmail");
insert into board (title, content, user_id, created_at, created_by, modified_at, modified_by) values
("test3", "이것은 테스트 3 문장입니다.!!!!!", 1, now(), "lhs@gmail", now(), "lhs@gmail");
insert into board (title, content, user_id, created_at, created_by, modified_at, modified_by) values
("test4", "이것은 테스트 4 문장입니다.!!!!!", 2, now(), "lhs2@gmail", now(), "lhs2@gmail");