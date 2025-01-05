SET NAMES UTF8;
DROP DATABASE IF EXISTS LuaChea;
CREATE DATABASE IF NOT EXISTS LuaChea;
USE LuaChea;

-- TABLA BASE USUARIO
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    apellido1 VARCHAR(40) NOT NULL,
    apellido2 VARCHAR(40) NULL,
    fecha TIMESTAMP NOT NULL,
    num_telef VARCHAR(9) NOT NULL,
    NIF VARCHAR(9) NULL,
    direccion VARCHAR(1000) NULL,
    cp VARCHAR(5) NULL,
    img VARCHAR(100) NOT NULL,
    correo VARCHAR(40) NULL,
    contraseña VARCHAR(255) NULL,
    PRIMARY KEY (id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- TABLA USUARIO_ACTUAL
CREATE TABLE IF NOT EXISTS usuario_actual (
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,
    CONSTRAINT pk_usuario_actual PRIMARY KEY (id_usuario),
    CONSTRAINT fk_usuario_actual_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_usuario_actual_rol FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TABLA USUARIO_PASADO
CREATE TABLE IF NOT EXISTS usuario_pasado (
    id_datos_usuario INT AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_usuario_pasado INT NOT NULL,
    PRIMARY KEY (id_datos_usuario),
    CONSTRAINT fk_usuario_pasado_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_usuario_pasado_usuario_actual FOREIGN KEY (id_usuario_pasado) REFERENCES usuario_actual(id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- TABLA CARTA_COMIDA
create table if not exists carta_comida(
    id_comida int auto_increment,
    nombre varchar(100) not null,
    descripcion varchar(300) null,
    tipo int not null,
    subtipo int null,
    fecha_inicio date not null,
    fecha_fin date null,
    precio float not null,
    disponible  boolean not null default true,
    img varchar(100) not null,
    constraint pk_id primary key (id_comida)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TABLA PEDIDOS
CREATE TABLE IF NOT EXISTS pedidos (
    id_ped INT NOT NULL AUTO_INCREMENT,
    id_usuario int not null,
    fecha date NOT NULL,
    enviado boolean NOT NULL default false,
    restaurante VARCHAR(10) NOT NULL,
    PRIMARY KEY (id_ped)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists carrito (
id_carro int auto_increment,
id_usuario int not null,
comida_cantidad longtext,
id_ped int,
constraint pk_id primary key (id_carro)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TABLA RESERVAS
create table if not exists reservas (
    id_reservas	int auto_increment,
    id_usuario int,
    id_restaurante varchar(10),
    id_mesa int,
    fecha_reserva date not null,
    turno enum('comer', 'cenar'),
    reservaAceptada boolean DEFAULT false,
    constraint pk_id primary key (id_reservas)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TABLA MESAS
create table if not exists mesas (
    id_mesa	int auto_increment,
    enumMesa varchar(20) not null,
    constraint pk_id primary key (id_mesa)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TABLA EMPRESA
create table if not exists empresa(
    cif varchar(10) not null,
    nombreLocal varchar(120) not null,
    nombre_sociedad varchar(120) not null,
    direccion varchar(60) not null,
    ciudad varchar(20) not null,
    cp int null,
    telefono int(9) not null,
    logo varchar(100) not null,
    constraint pk_cif primary key (cif)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;







-- LAS DE ARRIBA ERAN LAS DEL PAPEL
-- COMPROBAR SI QUEREMOS PONER LAS SIGUIENTES TABLAS

create table if not exists tipo (
id_tipo int auto_increment,
nombre_tipo varchar(100),
constraint pk_tipo primary key (id_tipo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table if not exists subtipo(
id_subtipo int auto_increment,
nombre_subtipo varchar(100) not null,
constraint pk_subTipo primary key (id_subtipo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table if not exists alergenos(
id_alergeno	int	auto_increment,
nombre_alergeno varchar(100) not null,
descripcion varchar(100) not null,
img varchar(100) null,
constraint pk_idAlergeno primary key (id_alergeno)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table if not exists carta_alergenos(
id_carta_alergenos int not null primary key auto_increment,
id_alergeno int not null,
id_comida int not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table if not exists factura(
id_factura int auto_increment,
id_usuario int not null,
cif_empresa varchar(10) not null,
fecha TIMESTAMP not null,
total float not null,
modo_pago int not null,
id_ped int not null,

constraint pk_id_factura primary key (id_factura)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists modo_pago(
  id_modo_pago int not null auto_increment primary key,
  nombre varchar(50) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists ped_prod (
  id_ped_prod int NOT NULL auto_increment,
  id_ped int NOT NULL,
  id_prod int NOT NULL,
  cantidad int NOT NULL,
  precio float NOT NULL,
  constraint pk_id_ped_prod primary key (id_ped_prod)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE ped_prod ADD FOREIGN KEY (id_ped) REFERENCES pedidos(id_ped);
ALTER TABLE ped_prod ADD FOREIGN KEY (id_prod) REFERENCES carta_comida(id_comida);

ALTER TABLE factura ADD FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
ALTER TABLE factura ADD FOREIGN KEY (cif_empresa) REFERENCES empresa(cif);
ALTER TABLE factura ADD FOREIGN KEY (modo_pago) REFERENCES modo_pago(id_modo_pago);
ALTER TABLE factura ADD FOREIGN KEY (id_ped) REFERENCES pedidos(id_ped);

ALTER TABLE carrito ADD FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
ALTER TABLE carrito ADD FOREIGN KEY (id_ped) REFERENCES pedidos(id_ped);

ALTER TABLE reservas ADD FOREIGN KEY (id_usuario) REFERENCES usuario_actual(id_usuario);
ALTER TABLE reservas ADD FOREIGN KEY (id_restaurante) REFERENCES empresa(cif);
ALTER TABLE reservas ADD FOREIGN KEY (id_mesa) REFERENCES mesas(id_mesa);

ALTER TABLE pedidos ADD FOREIGN KEY (restaurante) REFERENCES empresa(cif);

ALTER TABLE carta_alergenos ADD FOREIGN KEY (id_alergeno) REFERENCES alergenos(id_alergeno);
ALTER TABLE carta_alergenos ADD FOREIGN KEY (id_comida) REFERENCES carta_comida(id_comida);

ALTER TABLE carta_comida ADD FOREIGN KEY (tipo) references tipo(id_tipo);
ALTER TABLE carta_comida ADD FOREIGN KEY (subtipo) references subtipo(id_subtipo);


-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, apellido1, apellido2, fecha, num_telef, NIF, direccion, cp, img, correo, contraseña) 
VALUES 
('Juan', 'Pérez', 'Gómez', NOW(), '600123456', '12345678A', 'Calle Falsa 123', '28080', 'juan.jpg', 'juan@gmail.com', 'contraseña1'),
('María', 'López', 'Martínez', NOW(), '600987654', '87654321B', 'Avenida Real 45', '28081', 'maria.jpg', 'maria@gmail.com', 'contraseña2'),
('Carlos', 'Hernández', 'Ruiz', NOW(), '600112233', '11223344C', 'Calle Luna 67', '28082', 'carlos.jpg', 'carlos@gmail.com', 'contraseña3');

-- Insertar datos en la tabla roles
INSERT INTO roles (nombre_rol) 
VALUES 
('Admin'), 
('Usuario'), 
('Empleado');

-- Insertar datos en la tabla usuario_actual
INSERT INTO usuario_actual (id_usuario, id_rol) 
VALUES 
(1, 1), -- Juan es un Admin
(2, 2); -- María es un Usuario

-- Insertar datos en la tabla usuario_pasado
INSERT INTO usuario_pasado (id_usuario, id_usuario_pasado) 
VALUES 
(3, 1); -- Carlos fue pasado como usuario por Juan

-- Insertar datos en la tabla tipo
INSERT INTO tipo (nombre_tipo) 
VALUES 
('Comida'), 
('Bebida');

-- Insertar datos en la tabla subtipo
INSERT INTO subtipo (nombre_subtipo) 
VALUES 
('Vegetariano'), 
('Carnívoro');

-- Insertar datos en la tabla carta_comida
INSERT INTO carta_comida (nombre, descripcion, tipo, subtipo, fecha_inicio, fecha_fin, precio, disponible, img)
VALUES 
('Pizza Margarita', 'Pizza con tomate, mozzarella y albahaca', 1, NULL, '2024-01-01', NULL, 8.5, TRUE, 'pizza_margarita.jpg'),
('Hamburguesa Clásica', 'Hamburguesa con queso y lechuga', 2, 2, '2024-01-01', NULL, 10.0, TRUE, 'hamburguesa_clasica.jpg');

-- Insertar datos en la tabla empresa
INSERT INTO empresa (cif, nombreLocal, nombre_sociedad, direccion, ciudad, cp, telefono, logo)
VALUES 
('1234567890', 'Restaurante Central', 'Central Foods S.A.', 'Calle Mayor 12', 'Madrid', 28013, 911234567, 'central_logo.jpg'),
('0987654321', 'Bistro Norte', 'Norte Gourmet S.L.', 'Calle Sur 45', 'Barcelona', 08001, 933456789, 'norte_logo.jpg');


-- Insertar datos en la tabla pedidos
INSERT INTO pedidos (id_usuario, fecha, enviado, restaurante) 
VALUES 
(1, '2024-01-01', FALSE, '1234567890'), -- Pedido de Juan
(2, '2024-01-02', TRUE, '0987654321'); -- Pedido de María

-- Insertar datos en la tabla mesas
INSERT INTO mesas (enumMesa) 
VALUES 
('Mesa 1'), 
('Mesa 2');

-- Insertar datos en la tabla reservas
INSERT INTO reservas (id_usuario, id_restaurante, id_mesa, fecha_reserva, turno, reservaAceptada) 
VALUES 
(1, '1234567890', 1, '2024-01-10', 'comer', TRUE), -- Reserva de Juan para almorzar
(2, '0987654321', 2, '2024-01-15', 'cenar', FALSE); -- Reserva de María para cenar

-- Insertar datos en la tabla alergenos
INSERT INTO alergenos (nombre_alergeno, descripcion, img) 
VALUES 
('Gluten', 'Presente en trigo, cebada, y centeno', 'gluten.jpg'), 
('Lactosa', 'Presente en productos lácteos', 'lactosa.jpg');

-- Insertar datos en la tabla carta_alergenos
INSERT INTO carta_alergenos (id_alergeno, id_comida) 
VALUES 
(1, 1), -- La pizza Margarita contiene gluten
(2, 2); -- La hamburguesa Clásica contiene lactosa

-- Insertar datos en la tabla modo_pago
INSERT INTO modo_pago (nombre) 
VALUES 
('Tarjeta'), 
('Efectivo');

-- Insertar datos en la tabla factura
INSERT INTO factura (id_usuario, cif_empresa, fecha, total, modo_pago, id_ped) 
VALUES 
(1, '1234567890', NOW(), 18.5, 1, 1), -- Factura para el pedido de Juan
(2, '0987654321', NOW(), 20.0, 2, 2); -- Factura para el pedido de María


START TRANSACTION;

	alter table mesas add cupo int not null;
	alter table mesas add ocupada bool not null;
    update mesas set cupo = 2 where id_mesa = 1;
    update mesas set cupo = 4 where id_mesa = 2;
    update mesas set cupo = 6 where id_mesa = 3;
    update mesas set ocupada=false;
    update mesas set ocupada=true where id_mesa = 2;

commit; 