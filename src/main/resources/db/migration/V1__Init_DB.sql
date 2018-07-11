
create table account(
  id bigint not null,
  currency varchar(255),
  sum bigint not null,
  title varchar(255),
  user_id bigint,
  primary key (id)
);


create table category (
transaction_id bigint not null,
category varchar(255));

create table hibernate_sequence (next_val bigint);

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table transaction (
id bigint not null,
date varchar(255),
description varchar(255),
filename varchar(255),
sum integer not null,
account_id bigint,
primary key (id));

create table user_role (
user_id bigint not null,
roles varchar(255));

create table usr (
id bigint not null,
activation_code varchar(255),
active bit not null,
email varchar(255),
password varchar(255) not null,
username varchar(255) not null,
primary key (id));

alter table account
add constraint account_user_fk
foreign key (user_id)
references usr (id);

alter table category
add constraint category_transaction_fk
foreign key (transaction_id)
references transaction (id);

alter table transaction
add constraint transaction_account_fk
foreign key (account_id)
references account (id);

alter table user_role
add constraint user_role_user_fk
foreign key (user_id)
references usr (id);