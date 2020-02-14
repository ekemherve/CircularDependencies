-- ######################################              ROLE                    ######################################--

insert into postgres.roles (name) values ('ROLE_USER');
insert into postgres.roles (name) values ('ROLE_ADMIN');

-- ######################################              USER                    ######################################--

insert into users (username, password, email) values ('Herve', 'Herve', 'herve@gmail.com');
