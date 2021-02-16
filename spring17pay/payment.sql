drop table payment;

create sequence payment_seq nocache;

create table payment(
no number primary key,
tid varchar2(20) not null unique,
partner_order_id varchar2(40) not null,
partner_user_id varchar2(40) not null,
total_amount number not null,
time date default sysdate not null,
);

-- 추가구문

truncate table payment;

alter table payment add(
item_name varchar2(100) not null,
quantity number default 1 not null,
status varchar2(12) not null check(status in ('결제대기', '결제승인', '부분취소', '결제취소'))
);