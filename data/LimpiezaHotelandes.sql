--- Sentencias SQL para la creaci�n del esquema de HOTEL ANDES
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido deseado de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada
    
-- Eliminar todas las tablas de la base de datos
DROP TABLE "A_HOTEL" CASCADE CONSTRAINTS;
DROP TABLE "A_TIPO_HABITACION" CASCADE CONSTRAINTS;
DROP TABLE "A_ES_DE_TIPO" CASCADE CONSTRAINTS;
DROP TABLE "A_HABITACION" CASCADE CONSTRAINTS;
DROP TABLE "A_PISCINA" CASCADE CONSTRAINTS;
DROP TABLE "A_GIMNASIO" CASCADE CONSTRAINTS;
DROP TABLE "A_INTERNET" CASCADE CONSTRAINTS;
DROP TABLE "A_TIPO_LAVANDERIA" CASCADE CONSTRAINTS;
DROP TABLE "A_SERVICIO_LAVANDERIA" CASCADE CONSTRAINTS;
DROP TABLE "A_SPA" CASCADE CONSTRAINTS;
DROP TABLE "A_SERVICIO_SPA" CASCADE CONSTRAINTS;
DROP TABLE "A_ESTADO_UTENCILIO" CASCADE CONSTRAINTS;
DROP TABLE "A_UTENCILIO" CASCADE CONSTRAINTS;
DROP TABLE "A_TIPO_SALON" CASCADE CONSTRAINTS;
DROP TABLE "A_SALON" CASCADE CONSTRAINTS;
DROP TABLE "A_SERVICIO_SALON" CASCADE CONSTRAINTS;
DROP TABLE "A_BAR" CASCADE CONSTRAINTS;
DROP TABLE "A_PRODUCTO_BAR" CASCADE CONSTRAINTS;
DROP TABLE "A_RESTAURANTE" CASCADE CONSTRAINTS;
DROP TABLE "A_PRODUCTO_RESTAURANTE" CASCADE CONSTRAINTS;
DROP TABLE "A_SUPERMERCADO" CASCADE CONSTRAINTS;
DROP TABLE "A_ANAQUEL" CASCADE CONSTRAINTS;
DROP TABLE "A_PRODUCTO_SUPERMERCADO" CASCADE CONSTRAINTS;
DROP TABLE "A_TIENDA" CASCADE CONSTRAINTS;
DROP TABLE "A_PRODUCTO_TIENDA" CASCADE CONSTRAINTS;
DROP TABLE "A_CLIENTE" CASCADE CONSTRAINTS;
DROP TABLE "A_RESERVA_CLIENTE" CASCADE CONSTRAINTS;
DROP TABLE "A_RESERVA_HABITACION" CASCADE CONSTRAINTS;
DROP TABLE "A_CUENTA" CASCADE CONSTRAINTS;
DROP TABLE "A_RECEPCIONISTA" CASCADE CONSTRAINTS;
DROP TABLE "A_ADMINISTRADOR_DATOS" CASCADE CONSTRAINTS;
DROP TABLE "A_ADMINISTRADOR_SISTEMA" CASCADE CONSTRAINTS;
DROP TABLE "A_EMPLEADO" CASCADE CONSTRAINTS;
DROP TABLE "A_GERENTE" CASCADE CONSTRAINTS;
DROP TABLE "A_ROL_USUARIO" CASCADE CONSTRAINTS;
DROP TABLE "A_USUARIO" CASCADE CONSTRAINTS;
DROP TABLE "A_PLAN_CONSUMO" CASCADE CONSTRAINTS;
DROP TABLE "A_PLAN_CONSUMO_RESERVA" CASCADE CONSTRAINTS;
DROP TABLE "A_INCLUIDO_EN_PLAN_BAR" CASCADE CONSTRAINTS;
DROP TABLE "A_INCLUIDO_EN_PLAN_RESTAURANTE" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_PISCINA" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_GIMNASIO" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_INTERNET" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_LAVANDERIA" CASCADE CONSTRAINTS;
DROP TABLE "A_RESERVA_SERVICIO_SPA" CASCADE CONSTRAINTS;
DROP TABLE "A_PRESTAMO_UTENCILIO" CASCADE CONSTRAINTS;
DROP TABLE "A_RESERVA_SALON" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_SERVICIO_SALON" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_BAR" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_RESTAURANTE" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_SUPERMERCADO" CASCADE CONSTRAINTS;
DROP TABLE "A_CONSUMO_TIENDA" CASCADE CONSTRAINTS;
COMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos
-- El orden es importante. Por qu�?
delete from a_consumo_tienda;
delete from a_consumo_supermercado;
delete from a_consumo_restaurante;
delete from a_consumo_bar;
delete from a_consumo_servicio_salon;
delete from a_reserva_salon;
delete from a_prestamo_utencilio;
delete from a_reserva_servicio_spa;
delete from a_consumo_lavanderia;
delete from a_consumo_internet;
delete from a_consumo_gimnasio;
delete from a_consumo_piscina;
delete from a_incluido_en_plan_restaurante;
delete from a_incluido_en_plan_bar;
delete from a_plan_consumo_reserva;
delete from a_plan_consumo;
delete from a_usuario;
delete from a_rol_usuario;
delete from a_gerente;
delete from a_empleado;
delete from a_administrador_sistema;
delete from a_administrador_datos;
delete from a_recepcionista;
delete from a_cuenta;
delete from a_reserva_habitacion;
delete from a_reserva_cliente;
delete from a_cliente;
delete from a_producto_tienda;
delete from a_tienda;
delete from a_producto_supermercado;
delete from a_anaquel;
delete from a_supermercado;
delete from a_producto_restaurante;
delete from a_restaurante;
delete from a_producto_bar;
delete from a_bar;
delete from a_servicio_salon;
delete from a_salon;
delete from a_tipo_salon;
delete from a_utencilio;
delete from a_estado_utencilio;
delete from a_servicio_spa;
delete from a_spa;
delete from a_servicio_lavanderia;
delete from a_tipo_lavanderia;
delete from a_internet;
delete from a_gimnasio;
delete from a_piscina;
delete from a_habitacion;
delete from a_es_de_tipo;
delete from a_tipo_habitacion;
delete from a_hotel;
commit;

