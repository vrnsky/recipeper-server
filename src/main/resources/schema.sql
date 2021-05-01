create table usr (
  ID serial primary key,
  EMAIL varchar(64),
  PASSWORD varchar(120)
);

create table recipes (
 ID serial primary key,
 TITLE varchar(200),
 DESCRIPTION varchar(2000)
);

create table products (
  ID serial primary key,
  NAME varchar(200),
  DESCRIPTION varchar(2000)
);