drop table payment;

create sequence payment_seq nocache;

create table payment(
no number primary key,
tid varchar2(20) not null unique,
partner_order_id varchar2(40) not null,
partner_user_id varchar2(40) not null,
total_amount number not null,
time date default sysdate not null
);