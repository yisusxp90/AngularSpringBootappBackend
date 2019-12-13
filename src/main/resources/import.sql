insert into regiones (id, nombre) values (1, 'Sudamerica');
insert into regiones (id, nombre) values (2, 'Centroamerica');
insert into regiones (id, nombre) values (3, 'Norteamerica');
insert into regiones (id, nombre) values (4, 'Europa');
insert into regiones (id, nombre) values (5, 'Asia');
insert into regiones (id, nombre) values (6, 'Africa');
insert into regiones (id, nombre) values (7, 'Oceania');
insert into regiones (id, nombre) values (8, 'Antartida');

insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (1, 'Jesus', 'Melendez', 'yisusxp90@gmail.com', '2019-12-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (1, 'Mariangel', 'Melendez', 'tronci@gmail.com', '2019-12-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (3, 'Sebas', 'Melendez', 'sebas@gmail.com', '2019-01-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (3, 'Norexi', 'Melendez', 'norexi@gmail.com', '2019-02-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (3, 'Wilmer', 'Melendez', 'wilmer@gmail.com', '2019-03-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (4, 'Edgar', 'Melendez', 'edgar@gmail.com', '2019-04-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (4, 'Rosario', 'Reyes', 'rosario@gmail.com', '2019-05-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (2, 'Reinaldo', 'Vargas', 'reinaldo@gmail.com', '2019-06-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (2, 'Alexander', 'Vargas', 'alexander@gmail.com', '2019-07-01', 'nouser.jpg');
insert into clientes (region_id, nombre, apellido, email, create_at, foto) values (2, 'Ricardo', 'Vargas', 'ricardo@gmail.com', '2019-08-01', 'nouser.jpg');

insert into usuarios (username, password, enabled, nombre, apellido, email) values ('jesus', '$2a$10$PXHtnrMExi5ku69jDzmq6OaOHRja14NHLE.9oRqM9UM.Z9qiamnJq', true, 'jesus', 'Melendez', 'yisusxp90@gmail.com');
insert into usuarios (username, password, enabled, nombre, apellido, email) values ('sebas', '$2a$10$cprmn.oXNA7pSqMfCqmdD.XjcBbo7Yzwua3pwHryE1cLUG2te/jUq', true, 'sebas', 'Melendez', 'sebas@gmail.com');


insert into roles (nombre) values ('ROLE_USER');
insert into roles (nombre) values ('ROLE_ADMIN');

insert into users_authorities (usuario_id, role_id) values (1,1);
insert into users_authorities (usuario_id, role_id) values (2,2);
insert into users_authorities (usuario_id, role_id) values (2,1);
