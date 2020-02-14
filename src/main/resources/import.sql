-- ######################################              ROLE                    ######################################--

insert into postgres.roles (name) values ('ROLE_USER');
insert into postgres.roles (name) values ('ROLE_ADMIN');

-- ######################################              USER                    ######################################--

insert into users (username, password, email) values ('Herve', 'Herve', 'herve@gmail.com');


-- ######################################              USER_ROLES                   ######################################--

insert into postgres.users_roles (user_id, role_id) values (1, 1);
