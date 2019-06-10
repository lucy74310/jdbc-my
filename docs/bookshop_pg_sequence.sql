drop sequence seq_author;
create sequence seq_author start 1;

drop sequence seq_book;
create sequence seq_book start 1;


CREATE TABLE "book"
(
	"no"        integer      NOT NULL default nextval('seq_book'), -- 번호
	"title"     VARCHAR(200) NOT NULL, -- 제목
	"status"    varchar(20)  NOT NULL, -- 상태
	"author_no" integer      NOT NULL  -- 저자번호
);


CREATE TABLE "author"
(
	"no"   integer      NOT NULL default nextval('seq_author'), -- 번호
	"name" VARCHAR(200) NOT NULL  -- 이름
);

