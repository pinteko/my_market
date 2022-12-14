create table orders (
    id                  bigserial primary key,
    user_id             bigint not null references users (id),
    total_price         int not null,
    address             varchar(255),
    phone               varchar(255),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table order_items (
    id                  bigserial primary key,
    novel_id            bigint not null references novels (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   int not null,
    price               int not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

alter table users add column created_at timestamp default current_timestamp;
alter table users add column updated_at timestamp default current_timestamp;

alter table roles add column created_at timestamp default current_timestamp;
alter table roles add column updated_at timestamp default current_timestamp;

alter table novels add column created_at timestamp default current_timestamp;
alter table novels add column updated_at timestamp default current_timestamp;