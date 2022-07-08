INSERT INTO roles (id, "name") VALUES(1, 'ROLE_ADMIN');
INSERT INTO roles (id, "name") VALUES(2, 'ROLE_MODERATOR');
INSERT INTO roles (id, "name") VALUES(3, 'ROLE_USER');
INSERT INTO roles (id, "name") VALUES(4, 'ROLE_CLIENTE');

INSERT INTO users (id, email, "password", username) VALUES(1, 'usuario1@prueba.com', '$2a$12$WaWMzmuWH22Ns2KKK.yDbO5zi3wxcYllqZU25s35jaDRb98tLhtVi', 'usuario1');
INSERT INTO users (id, email, "password", username) VALUES(2, 'usuario2@gmail.com', '$2a$10$4uzH9GR4topmPBHwdOQoi.n0TD4u8PPItDH4bvwwInGZ0Ez87dYU.', 'usuario2');

INSERT INTO user_roles (user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES(2, 4);

INSERT INTO modalidad (id_modalidad, cantidad_minima_descuento, codigo, descripcion, nombre, porcentaje_descuento, valido_desde, valido_hasta) VALUES(1, 10, 'M', 'Maritima', 'Maritima', 0.0, '2022-07-06', NULL);
INSERT INTO modalidad (id_modalidad, cantidad_minima_descuento, codigo, descripcion, nombre, porcentaje_descuento, valido_desde, valido_hasta) VALUES(2, 0, 'T', 'Terrestre', 'Terrestre', 10.0, '2022-07-06', NULL);


INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(3, 'asdad', 'asdasd', '222', '2022-07-06', NULL, 1);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(4, '3333', '3333333', '33333', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(6, 'asdas', 'dasd', 'sdasdas', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(7, 'asdas', 'Puerto 1', '555555', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(8, 'asdas', 'dasdasds', 'asdasdas', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(9, 'asdas', 'dasdas', 'asdasdas', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(10, 'asdas', 'sadasdasd', 'dasdasdasd', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(1, 'aaa', 'aaaaa', '11111', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(11, 'qadas', 'asdasdasd', 'sdasdasda', '2022-07-06', NULL, 1);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(12, 'asdas', 'dasdasda', 'dasdas', '2022-07-06', NULL, 2);
INSERT INTO localizacion_entrega (id_localizacion_entrega, codigo, descripcion, nombre, valido_desde, valido_hasta, id_modalidad) VALUES(5, 'asdas', 'asdasd', '555', '2022-07-06', NULL, 2);
