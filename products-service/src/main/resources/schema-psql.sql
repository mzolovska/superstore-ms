DROP TABLE IF EXISTS products;

create table if not exists products(
    id SERIAL,
    product_id VARCHAR(36),
    status VARCHAR(50),
    product_type VARCHAR(50),
    price VARCHAR(50),
    country VARCHAR(50),
    product_name VARCHAR(100),
    amount VARCHAR(50),
    PRIMARY KEY (id)

);