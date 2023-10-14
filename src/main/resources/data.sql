create table attachment
(
    id                 bigserial
        primary key,
    created_at         timestamp(6),
    updated_at         timestamp(6),
    content_type       varchar(255),
    download_url       varchar(255),
    file_name          varchar(255),
    file_original_name varchar(255),
    size               bigint not null
);

create table product
(
    id            bigserial
        primary key,
    created_at    timestamp(6),
    updated_at    timestamp(6),
    name          varchar(255) not null,
    attachment_id bigint
        constraint uk_93fvxor12tulrdfqcv0lo2q3k
            unique
        constraint fka4ev5q3ittjs5m74d0va0dkch
            references attachment
);

create table users
(
    id           bigserial
        primary key,
    created_at   timestamp(6),
    updated_at   timestamp(6),
    phone_number varchar(255) not null,
    username     varchar(255) not null
);

create table orders
(
    id         bigserial
        primary key,
    created_at timestamp(6),
    updated_at timestamp(6),
    count      integer,
    product_id bigint
        constraint fk787ibr3guwp6xobrpbofnv7le
            references product,
    user_id    bigint
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users on delete cascade
);

