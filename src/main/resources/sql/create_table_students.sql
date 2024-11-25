create  table student(
    id serial primary key not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    patronymic varchar(255),
    telephone varchar(100),
    telegram varchar (200),
    git varchar(200),
    mail varchar(200)
);