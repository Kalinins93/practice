--liquibase formatted sql

--changeset liquibase:a
insert into  users ( name, email, hashcode,iconname )
    values ( 'admin', 'admin@admin.admin', '123','');

--changeset liquibase:b
insert into  games ( title, description, price, imagename )
    values ( 'The long dark',
    'The Long Dark - это исследовательская игра-симулятор выживания',
    600, '1_The Long Dark.jpg');

--changeset liquibase:c
insert into  games ( title, description, price, imagename )
    values ( 'Terraria',
    'Terraria - это пиксельная "песочница"',
    350, '25_Terraria.jpg');

--changeset liquibase:d
insert into  roles ( idofuser, role )
    values ( 1, 'admin');

