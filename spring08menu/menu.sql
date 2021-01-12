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

create table menu_image(
file_no number,
file_name varchar2(255) not null,
file_size number,
file_type varchar2(30) not null,
file_time date,
menu_no number
);
