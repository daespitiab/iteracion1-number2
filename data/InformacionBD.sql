--- Sentencias SQL para la creaci�n del esquema de HOTEL ANDES
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido deseado de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada
    
-- RF1: Registrar roles de usuario

INSERT INTO a_rol_usuario(ID, NOMBRE)
VALUES( 1, 'Administrador Datos');

INSERT INTO a_rol_usuario(ID, NOMBRE)
VALUES( 2, 'Administrador Sistema');

INSERT INTO a_rol_usuario(ID, NOMBRE)
VALUES( 3, 'Gerente');

INSERT INTO a_rol_usuario(ID, NOMBRE)
VALUES( 4, 'Recepcionista');

INSERT INTO a_rol_usuario(ID, NOMBRE)
VALUES( 5, 'Cliente');

-- RF2: Registrar usuario

INSERT INTO a_gerente(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 1, 'CC', 'Gerente1', 'gerente1@hotelandes.com');

INSERT INTO a_administrador_datos(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 1, 'CC', 'Administrador Datos 1', 'administradordatos1@hotelandes.com');

INSERT INTO a_recepcionista(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 1, 'CC', 'Recepcionista1', 'recepcionista1@hotelandes.com');

INSERT INTO a_recepcionista(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 2, 'CC', 'Recepcionista2', 'recepcionista2@hotelandes.com');

INSERT INTO a_empleado(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 1, 'CC', 'Empleado1', 'empleado1@hotelandes.com');

INSERT INTO a_empleado(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 2, 'CC', 'Empleado2', 'empleado2@hotelandes.com');

INSERT INTO a_empleado(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 3, 'CC', 'Empleado3', 'empleado3@hotelandes.com');

INSERT INTO a_empleado(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 4, 'CC', 'Empleado1', 'empleado4@hotelandes.com');

INSERT INTO a_cliente(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 1, 'CC', 'Cliente1', 'cliente1@hotelandes.com');

INSERT INTO a_cliente(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 2, 'CC', 'Cliente2', 'cliente2@hotelandes.com');

INSERT INTO a_cliente(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 3, 'CC', 'Cliente3', 'cliente3@hotelandes.com');

INSERT INTO a_cliente(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO )
VALUES( 4, 'CC', 'Cliente4', 'cliente4@hotelandes.com');

-- RF3: Registrar tipo de habitacion

INSERT INTO a_tipo_habitacion(ID, NOMBRE, COSTO )
VALUES( 1, 'Suite', 500.00);

INSERT INTO a_tipo_habitacion(ID, NOMBRE, COSTO )
VALUES( 2, 'Doble', 150.00);

INSERT INTO a_tipo_habitacion(ID, NOMBRE, COSTO )
VALUES( 3, 'Sencilla', 100.00);

-- RF4: Registrar habitacion

INSERT INTO a_es_de_tipo(DOTACION_HABITACION, CAPACIDAD_HABITACION, ID_TIPO)
VALUES('Dotacion1' ,2 ,1);

INSERT INTO a_es_de_tipo(DOTACION_HABITACION, CAPACIDAD_HABITACION, ID_TIPO)
VALUES('Dotacion1' ,3 ,1);

INSERT INTO a_es_de_tipo(DOTACION_HABITACION, CAPACIDAD_HABITACION, ID_TIPO)
VALUES('Dotacion1' ,4 ,1);

INSERT INTO a_es_de_tipo(DOTACION_HABITACION, CAPACIDAD_HABITACION, ID_TIPO)
VALUES('Dotacion2' ,2 ,2);

INSERT INTO a_es_de_tipo(DOTACION_HABITACION, CAPACIDAD_HABITACION, ID_TIPO)
VALUES('Dotacion3' ,1 ,3);


INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 101, 2, 'Dotacion1');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 102, 3, 'Dotacion1');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 103, 4, 'Dotacion1');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 201, 2, 'Dotacion2');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 202, 2, 'Dotacion2');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 203, 2, 'Dotacion2');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 301, 1, 'Dotacion3');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 302, 1, 'Dotacion3');

INSERT INTO a_habitacion(NUMERO_HABITACION, CAPACIDAD, DOTACION )
VALUES( 303, 1, 'Dotacion3');

-- RF5: Registrar un servicio de un hotel 

INSERT INTO a_piscina(ID, CAPACIDAD, PROFUNDIDAD, HORA_INICIO, HORA_FIN, COSTO, INCLUIDO)
VALUES( 1, 20, 150, '9:00','19:00', 12.000, 'Si');

INSERT INTO a_gimnasio(ID, CAPACIDAD, HORA_INICIO, HORA_FIN, COSTO, INCLUIDO)
VALUES( 1, 20, '9:00','19:00', 12.000, 'Si');

INSERT INTO a_internet(ID, CAPACIDAD, COSTO, INCLUIDO)
VALUES( 1, 30, 12.000, 'No');

INSERT INTO a_tipo_lavanderia(TIPO_LAVANDERIA, NUMERO_PRENDAS_LAVANDERIA, COSTO)
VALUES( 'Embolado', 2, 12.000);

INSERT INTO a_tipo_lavanderia(TIPO_LAVANDERIA, NUMERO_PRENDAS_LAVANDERIA, COSTO)
VALUES( 'Planchado', 4, 12.000);

INSERT INTO a_tipo_lavanderia(TIPO_LAVANDERIA, NUMERO_PRENDAS_LAVANDERIA, COSTO)
VALUES( 'Lavado', 5, 15.000);

INSERT INTO a_servicio_lavanderia(ID, TIPO, NUMERO_PRENDAS)
VALUES( 1, 'Lavado', 5);

INSERT INTO a_servicio_lavanderia(ID, TIPO, NUMERO_PRENDAS)
VALUES( 2, 'Planchado', 4);

INSERT INTO a_servicio_lavanderia(ID, TIPO, NUMERO_PRENDAS)
VALUES( 3, 'Embolado', 2);

INSERT INTO a_spa(ID, NOMBRE, CAPACIDAD)
VALUES( 1, 'Spa1', 10);

INSERT INTO a_servicio_spa(ID, NOMBRE, HORA_INICIO, HORA_FIN, COSTO, ID_SPA)
VALUES( 1, 'Servicio Spa 1', '7:00', '16:00', 12.000, 1);

INSERT INTO a_utencilio(ID, NOMBRE, COSTO)
VALUES( 1, 'Utencilio1', 10.000);

INSERT INTO a_tipo_salon(ID, NOMBRE, HORAS_ADICIONALES, COSTO)
VALUES( 1, 'Reunion', 1, 10.000);

INSERT INTO a_tipo_salon(ID, NOMBRE, HORAS_ADICIONALES, COSTO)
VALUES( 2, 'Conferencia', 12, 20.000);

INSERT INTO a_salon(ID, NOMBRE, CAPACIDAD, ID_TIPO)
VALUES( 1, 'SalonReunion1', 12, 1);

INSERT INTO a_salon(ID, NOMBRE, CAPACIDAD, ID_TIPO)
VALUES( 2, 'SalonConferencia1', 200, 2);

INSERT INTO a_servicio_salon(ID, NOMBRE, CARGAR_CONSUMO_SERVICIO_SALON, COSTO, ID_SALON)
VALUES( 1, 'ServicioSalon1', 'Si', 3.000, 1);

INSERT INTO a_servicio_salon(ID, NOMBRE, CARGAR_CONSUMO_SERVICIO_SALON, COSTO, ID_SALON)
VALUES( 2, 'ServicioSalon2', 'No', 4.000 ,2);

INSERT INTO a_bar(ID, NOMBRE, CAPACIDAD, ESTILO)
VALUES( 1, 'Bar1', 20, 'Estilo1');

INSERT INTO a_producto_bar(ID, NOMBRE, COSTO, ID_BAR)
VALUES( 1, 'Producto1', 20.000, 1);

INSERT INTO a_producto_bar(ID, NOMBRE, COSTO, ID_BAR)
VALUES( 2, 'Producto2', 20.000, 1);

INSERT INTO a_producto_bar(ID, NOMBRE, COSTO, ID_BAR)
VALUES( 3, 'Producto3', 20.000, 1);

INSERT INTO a_restaurante(ID, NOMBRE, CAPACIDAD, ESTILO)
VALUES( 1, 'Restaurante1', 20, 'Estilo1');

INSERT INTO a_producto_restaurante(ID, NOMBRE, COSTO, ID_RESTAURANTE)
VALUES( 1, 'Producto1', 20.000, 1);

INSERT INTO a_producto_restaurante(ID, NOMBRE, COSTO, ID_RESTAURANTE)
VALUES( 2, 'Producto2', 20.000, 1);

INSERT INTO a_producto_restaurante(ID, NOMBRE, COSTO, ID_RESTAURANTE)
VALUES( 3, 'Producto3', 20.000, 1);

INSERT INTO a_supermercado(ID, NOMBRE)
VALUES( 1, 'Supermercado1');

INSERT INTO a_anaquel(ID, NOMBRE, ID_SUPERMERCADO)
VALUES( 1, 'Anaquel1', 1);

INSERT INTO a_producto_supermercado(ID, NOMBRE, COSTO, ID_ANAQUEL)
VALUES( 1, 'Producto1', 20.000, 1);

INSERT INTO a_producto_supermercado(ID, NOMBRE, COSTO, ID_ANAQUEL)
VALUES( 2, 'Producto2', 20.000, 1);

INSERT INTO a_producto_supermercado(ID, NOMBRE, COSTO, ID_ANAQUEL)
VALUES( 3, 'Producto3', 20.000, 1);


INSERT INTO a_tienda(ID, NOMBRE)
VALUES( 1, 'tienda1');

INSERT INTO a_producto_tienda(ID, NOMBRE, COSTO, ID_TIENDA)
VALUES( 1, 'Producto1', 20.000, 1);

INSERT INTO a_producto_tienda(ID, NOMBRE, COSTO, ID_TIENDA)
VALUES( 2, 'Producto2', 20.000, 1);

INSERT INTO a_producto_tienda(ID, NOMBRE, COSTO, ID_TIENDA)
VALUES( 3, 'Producto3', 20.000, 1);

-- RF6: Registrar un plan de consumo

INSERT INTO a_plan_consumo(ID, NOMBRE, PORCENTAJE_DESCUENTO, COSTO_FIJO, FECHA_INICIO, FECHA_FIN)
VALUES( 1, 'Larga Estadía', 50, 0, '', '');

INSERT INTO a_plan_consumo(ID, NOMBRE, PORCENTAJE_DESCUENTO, COSTO_FIJO, FECHA_INICIO, FECHA_FIN)
VALUES( 2, 'Tiempo Compartido', 0, 300.000, '', '');

INSERT INTO a_plan_consumo(ID, NOMBRE, PORCENTAJE_DESCUENTO, COSTO_FIJO, FECHA_INICIO, FECHA_FIN)
VALUES( 3, 'Todo Incluido', 0, 300.000, '', '');








