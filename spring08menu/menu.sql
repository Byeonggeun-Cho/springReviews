-- 메뉴 테이블
create table menu(
no number,
name varchar2(255) not null,
type varchar2(30) not null,
price number not null,
reg date default sysdate,
primary key(no),
check(type in ('식사', '음료', '안주'),
check(price >= 0)
);

create sequence menu_seq nocache;

-- 메뉴파일 테이블
create table menu_image(
file_no number primary key,
file_name varchar2(255) not null,
file_size number default 0,
file_type varchar2(30) not null,
file_time date default sysdate not null,
menu_no number references menu(no) on delete cascade
);


create sequence menu_image_seq nocache;