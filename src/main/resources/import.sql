insert into regiones (id, nombre) values (1, 'Sudamerica');
insert into regiones (id, nombre) values (2, 'Centroamerica');
insert into regiones (id, nombre) values (3, 'Norteamerica');
insert into regiones (id, nombre) values (4, 'Europa');
insert into regiones (id, nombre) values (5, 'Asia');
insert into regiones (id, nombre) values (6, 'Africa');
insert into regiones (id, nombre) values (7, 'Oceania');
insert into regiones (id, nombre) values (8, 'Antartida');

insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('123-1', 1, 'Jesus', 'Melendez', 'yisusxp90@gmail.com', '2019-12-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('234-4', 1, 'Yuzmary', 'Porras', 'yuzmap@gmail.com', '2019-12-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('345-5', 1, 'Mariangel', 'Melendez', 'tronci@gmail.com', '2019-12-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('456-6', 3, 'Sebas', 'Melendez', 'sebas@gmail.com', '2019-01-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('567-8', 3, 'Norexi', 'Melendez', 'norexi@gmail.com', '2019-02-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('678-9', 3, 'Wilmer', 'Melendez', 'wilmer@gmail.com', '2019-03-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('789-0', 4, 'Edgar', 'Melendez', 'edgar@gmail.com', '2019-04-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('890-1', 4, 'Rosario', 'Reyes', 'rosario@gmail.com', '2019-05-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('901-2', 2, 'Reinaldo', 'Vargas', 'reinaldo@gmail.com', '2019-06-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('1234-5', 2, 'Alexander', 'Vargas', 'alexander@gmail.com', '2019-07-01', 'nouser.jpg');
insert into clientes (dni, region_id, nombre, apellido, email, create_at, foto) values ('2345-6', 2, 'Ricardo', 'Vargas', 'ricardo@gmail.com', '2019-08-01', 'nouser.jpg');

insert into usuarios (username, password, enabled, nombre, apellido, email) values ('jesus', '$2a$10$PXHtnrMExi5ku69jDzmq6OaOHRja14NHLE.9oRqM9UM.Z9qiamnJq', true, 'jesus', 'Melendez', 'yisusxp90@gmail.com');
insert into usuarios (username, password, enabled, nombre, apellido, email) values ('sebas', '$2a$10$cprmn.oXNA7pSqMfCqmdD.XjcBbo7Yzwua3pwHryE1cLUG2te/jUq', true, 'sebas', 'Melendez', 'sebas@gmail.com');


insert into roles (nombre) values ('ROLE_USER');
insert into roles (nombre) values ('ROLE_ADMIN');

insert into users_authorities (usuario_id, role_id) values (1,1);
insert into users_authorities (usuario_id, role_id) values (2,2);
insert into users_authorities (usuario_id, role_id) values (2,1);

INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());


INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(3, 2, 6);
