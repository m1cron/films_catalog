create table roles
(
    "id"   uuid,
    "name" varchar(32) not null,
    unique ("name"),
    primary key ("id")
);

insert into roles (id, name)
values ('2e40ad87-9e95-3201-9f4d-edbf8d479a12', 'ROLE_USER'),
       ('73acd9a5-9721-30b7-9066-c82595a1fae3', 'ROLE_ADMIN');

create table "user_role"
(
    "user_id" uuid,
    "role_id" uuid,
    primary key ("user_id", "role_id"),
    foreign key ("user_id") references users ("uuid"),
    foreign key ("role_id") references roles ("id")
);

insert into "user_role" (user_id, role_id)
values ('00000000-0000-0000-0000-000000000000', '73acd9a5-9721-30b7-9066-c82595a1fae3'),
       ('11111111-1111-1111-1111-111111111111', '2e40ad87-9e95-3201-9f4d-edbf8d479a12');
