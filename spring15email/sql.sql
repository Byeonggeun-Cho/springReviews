create table cert(
who references member(id) on delete cascade,
what varchar2(6) not null,
when date default sysdate not null
);