CREATE TABLE IF NOT EXISTS RECIPES (
 ID serial primary key,
 TITLE varchar(200),
 DESCRIPTION varchar(2000),
 PRODUCT
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
  ID serial primary key,
  NAME varchar(200),
  DESCRIPTION varchar(2000)
);

CREATE TABLE IF NOT EXISTS RECIPE_PRODUCT (
    ID SERIAL PRIMARY KEY,
    RECIPE_ID bigint references RECIPES(id),
    PRODUCT_ID bigint references PRODUCT(id)
);