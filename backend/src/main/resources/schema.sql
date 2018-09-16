create table usr (
id serial primary key,
email varchar(64),
password varchar(120)
);

create table recipes (
id serial primary key,
title varchar(200),
description varchar(2000)
);

create table products (
id serial primary key,
name varchar(200),
description varchar(2000)
);