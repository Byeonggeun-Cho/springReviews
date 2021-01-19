-- 대도시 테이블
create table city(
city_no number primary key,
city_name varchar2(60) not null
);

-- 구역 테이블
create table zone(
zone_no number primary key,
zone_name varchar2(60) not null,
city_no references city(city_no) on delete cascade
);


-- 시퀀스
create sequence zone_seq nocache;

-- 데이터 추가
insert into city values(1, '서울시');
insert into city values(2, '경기도');
insert into city values(3, '제주도');
insert into city values(4, '전라도');
insert into city values(5, '경상도');

insert into zone values(101, '강남구', 1);
insert into zone values(102, '서초구', 1);
insert into zone values(103, '종로구', 1);
insert into zone values(104, '수원시', 2);
insert into zone values(105, '용인시', 2);
insert into zone values(106, '광주시', 2);
insert into zone values(107, '성남시', 2);

commit;

