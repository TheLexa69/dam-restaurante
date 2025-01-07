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
    CONSTRAINT pk_usuario_actual PRIMARY KEY (id_usuario),
    CONSTRAINT fk_usuario_actual_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
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
    img varchar(100) null,
    id_empresa varchar(10) not null,
    constraint pk_id primary key (id_comida)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists carrito (
id_carro int auto_increment,
id_usuario int not null unique,
comida_cantidad longtext,
id_factura int,
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
    id_empresa varchar(10) not null,
	cupo int not null,
	ocupada bool not null DEFAULT false,
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
id_ped int not null,

constraint pk_id_factura primary key (id_factura)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists carrito_comida (
  id_carrito int NOT NULL auto_increment,
  id_comida int NOT NULL,
  cantidad int NOT NULL DEFAULT 1,
  constraint pk_id_ped_prod primary key (id_carrito,id_comida)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE factura ADD FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
ALTER TABLE factura ADD FOREIGN KEY (cif_empresa) REFERENCES empresa(cif);

ALTER TABLE carrito ADD FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
ALTER TABLE carrito ADD FOREIGN KEY (id_factura) REFERENCES factura(id_factura);

ALTER TABLE carrito_comida ADD FOREIGN KEY (id_carrito) REFERENCES carrito(id_carro);
ALTER TABLE carrito_comida ADD FOREIGN KEY (id_comida) REFERENCES carta_comida(id_comida);

ALTER TABLE reservas ADD FOREIGN KEY (id_usuario) REFERENCES usuario_actual(id_usuario);
ALTER TABLE reservas ADD FOREIGN KEY (id_restaurante) REFERENCES empresa(cif);
ALTER TABLE reservas ADD FOREIGN KEY (id_mesa) REFERENCES mesas(id_mesa);

ALTER TABLE carta_alergenos ADD FOREIGN KEY (id_alergeno) REFERENCES alergenos(id_alergeno);
ALTER TABLE carta_alergenos ADD FOREIGN KEY (id_comida) REFERENCES carta_comida(id_comida);

ALTER TABLE mesas ADD FOREIGN KEY (id_empresa) references empresa(cif);

ALTER TABLE carta_comida ADD FOREIGN KEY (tipo) references tipo(id_tipo);
ALTER TABLE carta_comida ADD FOREIGN KEY (subtipo) references subtipo(id_subtipo);
ALTER TABLE carta_comida ADD FOREIGN KEY (id_empresa) references empresa(cif);


-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, apellido1, apellido2, fecha, num_telef, NIF, direccion, cp, img, correo, contraseña) 
VALUES 
('Juan', 'Pérez', 'Gómez', NOW(), '600123456', '12345678A', 'Calle Falsa 123', '28080', 'juan.jpg', 'juan@gmail.com', 'contraseña1'),
('María', 'López', 'Martínez', NOW(), '600987654', '87654321B', 'Avenida Real 45', '28081', 'maria.jpg', 'maria@gmail.com', 'contraseña2'),
('Carlos', 'Hernández', 'Ruiz', NOW(), '600112233', '11223344C', 'Calle Luna 67', '28082', 'carlos.jpg', 'carlos@gmail.com', 'contraseña3');


-- Insertar datos en la tabla usuario_actual
INSERT INTO usuario_actual (id_usuario) 
VALUES 
(1), -- Juan es un Admin
(2); -- María es un Usuario

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

-- Insertar datos en la tabla empresa
INSERT INTO empresa (cif, nombreLocal, nombre_sociedad, direccion, ciudad, cp, telefono, logo)
VALUES 
('1234567890', 'Restaurante Central', 'Central Foods S.A.', 'Calle Mayor 12', 'Madrid', 28013, 911234567, 'central_logo.jpg'),
('0987654321', 'Bistro Norte', 'Norte Gourmet S.L.', 'Calle Sur 45', 'Barcelona', 08001, 933456789, 'norte_logo.jpg');

-- Insertar datos en la tabla carta_comida
INSERT INTO carta_comida (nombre, descripcion, tipo, subtipo, fecha_inicio, fecha_fin, precio, disponible, img,id_empresa)
VALUES 
('Pizza Margarita', 'Pizza con tomate, mozzarella y albahaca', 1, NULL, '2024-01-01', NULL, 8.5, TRUE, 'pizza_margarita.jpg',1234567890),
('Hamburguesa Clásica', 'Hamburguesa con queso y lechuga', 2, 2, '2024-01-01', NULL, 10.0, TRUE, 'hamburguesa_clasica.jpg',1234567890);

-- Insertar datos en la tabla mesas
INSERT INTO mesas (enumMesa,id_empresa,cupo) 
VALUES 
('Mesa 1',1234567890,2), 
('Mesa 2',1234567890,2),
('Mesa 1',1234567890,5), 
('Mesa 2',1234567890,9);

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