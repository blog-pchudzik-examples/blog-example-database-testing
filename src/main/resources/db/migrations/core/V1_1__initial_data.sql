insert into role(id, name) values(1, 'ADMIN');
insert into role(id, name) values(2, 'USER');

insert into user_(id, login, password) values(1, 'admin', 'admin');
insert into user__roles(user_id, roles_id) values(1, 1);
insert into user__roles(user_id, roles_id) values(1, 2);

insert into user_(id, login, password) values(2, 'user', 'user');
insert into user__roles(user_id, roles_id) values(2, 2);
