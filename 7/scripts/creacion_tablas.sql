DROP TABLE IF EXISTS Cargo_empleado CASCADE;
CREATE TABLE Cargo_empleado 
( 
    Id_cargo INT, 
    Nombre_cargo VARCHAR(50) NOT NULL,
    PRIMARY KEY (Id_cargo)
);

DROP TABLE IF EXISTS Acceso_empleado CASCADE;
CREATE TABLE Acceso_empleado
(
    Id_acceso INT,
    Nombre_acceso VARCHAR(50) NOT NULL,
    PRIMARY KEY (Id_acceso)
);

DROP TABLE IF EXISTS Proveedor CASCADE;
CREATE TABLE Proveedor
(
  Id_proveedor INT NOT NULL,
  RUC CHAR(11) NOT NULL,
  Empresa VARCHAR(255) NOT NULL,
  Nombre VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  Telefono CHAR(9) NOT NULL,
  Direccion VARCHAR(255) NOT NULL,
  Ciudad VARCHAR(255) NOT NULL,
  Categoria VARCHAR(30) NOT NULL,
  PRIMARY KEY (Id_proveedor)
);

DROP TABLE IF EXISTS Estado_reserva CASCADE;
CREATE TABLE Estado_reserva
(
  Id_estado_reserva INT NOT NULL,
  Nombre_estado VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_estado_reserva)
);
DROP TABLE IF EXISTS Insumo CASCADE;
CREATE TABLE Insumo
(
  Id_insumo Int NOT NULL,
  Cantidad INT NOT NULL,
  Nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_insumo)
);
DROP TABLE IF EXISTS Criticidad CASCADE;
CREATE TABLE Criticidad
(
  Id_criticidad INT NOT NULL,
  Nivel VARCHAR(50) NOT NULL,
  PRIMARY KEY (Id_criticidad)
);

DROP TABLE IF EXISTS Empleado CASCADE;
CREATE TABLE Empleado 
( 
    Id_empleado INT, 
    Nombre VARCHAR(100) NOT NULL, 
    DNI CHAR(8) NOT NULL, 
    Direccion VARCHAR(255), 
    Estado_civil VARCHAR(25), 
    Sexo VARCHAR(10), 
    Fecha_Nacimiento DATE, 
    Fecha_ingreso DATE, 
    Numero_contacto INT, 
    Email_contacto VARCHAR(100), 
    Contraseña VARCHAR(255), 
    Id_cargo INT, 
    Id_acceso INT,
    PRIMARY KEY (Id_empleado),
    FOREIGN KEY (Id_cargo) REFERENCES Cargo_empleado (Id_cargo),
    FOREIGN KEY (Id_acceso) REFERENCES Acceso_empleado (Id_acceso)
);

DROP TABLE IF EXISTS Plan_de_mantenimiento CASCADE;
CREATE TABLE Plan_de_mantenimiento
(
  Id_plan INT NOT NULL,
  Descripcion VARCHAR(255) NOT NULL,
  Fecha_plan DATE NOT NULL,
  Empleado_asigna INT NOT NULL,
  Id_criticidad INT NOT NULL,
  PRIMARY KEY (Id_plan),
  FOREIGN KEY (Empleado_asigna) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_criticidad) REFERENCES Criticidad(Id_criticidad)
);
DROP TABLE IF EXISTS Reserva CASCADE;
CREATE TABLE Reserva
(
  Id_reserva INT NOT NULL,
  Fecha DATE NOT NULL,
  Hora TIME NOT NULL,
  Id_estado_reserva INT NOT NULL,
  Id_empleado INT NOT NULL,
  PRIMARY KEY (Id_reserva),
  FOREIGN KEY (Id_estado_reserva) REFERENCES Estado_reserva(Id_estado_reserva),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado)
);
DROP TABLE IF EXISTS Detalle_reserva CASCADE;
CREATE TABLE Detalle_reserva
(
  Id_detalle SERIAL NOT NULL,
  Cant_reserv INT NOT NULL,
  Id_insumo INT NOT NULL,
  Id_reserva INT NOT NULL,
  PRIMARY KEY (Id_detalle),
  FOREIGN KEY (Id_insumo) REFERENCES Insumo(Id_insumo),
  FOREIGN KEY (Id_reserva) REFERENCES Reserva(Id_reserva)
);
DROP TABLE IF EXISTS Detalle_reserva_temporal CASCADE;
CREATE TABLE Detalle_reserva_temporal (
    id_temporal SERIAL PRIMARY KEY,
    Cant_reserv INT,
    Id_insumo INT,
    Id_reserva INT
);

DROP TABLE IF EXISTS Tipo_Urgencia CASCADE;
CREATE TABLE Tipo_Urgencia
(
  Id_urgencia INT NOT NULL,
  Tipo_urgencia VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_urgencia)
);

DROP TABLE IF EXISTS Estado_Pedido CASCADE;
CREATE TABLE Estado_Pedido
(
  Id_estado_pedido INT NOT NULL,
  nombre_estado_pedido VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_estado_pedido)
);

DROP TABLE IF EXISTS Orden_de_compra CASCADE;
CREATE TABLE Orden_de_compra
(
  Id_orden_compra INT NOT NULL,
  Fecha_emision_orden DATE NOT NULL,
  Fecha_posible_entrega DATE NOT NULL,
  Id_proveedor INT NOT NULL,
  Id_empleado INT NOT NULL,
  PRIMARY KEY (Id_orden_compra),
  FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado)
);

DROP TABLE IF EXISTS Tipo_producto CASCADE;
CREATE TABLE Tipo_producto
(
  Id_tipo_producto INT NOT NULL,
  nombre_tipo_producto VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_tipo_producto)
);

DROP TABLE IF EXISTS Pedido_Compra CASCADE;
CREATE TABLE Pedido_Compra
(
  Id_pedido_compra INT NOT NULL,
  Descripción VARCHAR(255) NOT NULL,
  Id_urgencia INT NOT NULL,
  Id_estado_pedido INT NOT NULL,
  Id_empleado INT NOT NULL,
  Id_orden_compra INT NULL,
  Id_tipo_producto INT NOT NULL,
  PRIMARY KEY (Id_pedido_compra),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_urgencia) REFERENCES Tipo_Urgencia(Id_urgencia),
  FOREIGN KEY (Id_estado_pedido) REFERENCES Estado_Pedido(Id_estado_pedido),
  FOREIGN KEY (Id_orden_compra) REFERENCES Orden_de_compra(Id_orden_compra),
  FOREIGN KEY (Id_tipo_producto) REFERENCES Tipo_producto(Id_tipo_producto)
);

DROP TABLE IF EXISTS Estado_actv CASCADE;
CREATE TABLE Estado_actv
(
  id_estado INT NOT NULL,
  estado VARCHAR(20) NOT NULL,
  PRIMARY KEY (id_estado)
);

DROP TABLE IF EXISTS Categoria_Almacen CASCADE;
CREATE TABLE Categoria_Almacen
(
  Id_categoria INT NOT NULL,
  Nombre_categoria VARCHAR(50) NOT NULL,
  PRIMARY KEY (Id_categoria)
);

DROP TABLE IF EXISTS Estado_Almacen CASCADE;
CREATE TABLE Estado_Almacen
(
  Id_estado INT NOT NULL,
  Nombre_estado VARCHAR(50) NOT NULL,
  PRIMARY KEY (Id_estado)
);

DROP TABLE IF EXISTS Almacen CASCADE;
CREATE TABLE Almacen
(
  Id_almacen INT NOT NULL,
  Direccion VARCHAR(255) NOT NULL,
  Capacidad INT NOT NULL,
  Id_empleado INT NOT NULL,
  Id_estado INT NOT NULL,
  Id_categoria INT NOT NULL,
  PRIMARY KEY (Id_almacen),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_estado) REFERENCES Estado_Almacen(Id_estado),
  FOREIGN KEY (Id_categoria) REFERENCES Categoria_Almacen(Id_categoria)
);
DROP TABLE IF EXISTS Insumoxalmacen CASCADE;
CREATE TABLE Insumoxalmacen
(
  Id_insum_alm SERIAL NOT NULL,
  Cantidad INT NOT NULL,
  Id_insumo INT NOT NULL,
  Id_almacen INT NOT NULL,
  PRIMARY KEY (Id_insum_alm),
  FOREIGN KEY (Id_insumo) REFERENCES Insumo(Id_insumo),
  FOREIGN KEY (Id_almacen) REFERENCES Almacen(Id_almacen)
);
DROP TABLE IF EXISTS Tipo_Equipo_Soporte CASCADE;
CREATE TABLE Tipo_Equipo_Soporte
(
  Id_tipo INT NOT NULL,
  Nombre_tipo VARCHAR(100) NOT NULL,
  PRIMARY KEY (Id_tipo)
);

DROP TABLE IF EXISTS Disponibilidad_Equipo_Soporte CASCADE;
CREATE TABLE Disponibilidad_Equipo_Soporte
(
  Id_disponibilidad INT NOT NULL,
  Nombre_disponibilidad VARCHAR(100) NOT NULL,
  PRIMARY KEY (Id_disponibilidad)
);

DROP TABLE IF EXISTS Estado_Equipo_Soporte CASCADE;
CREATE TABLE Estado_Equipo_Soporte
(
  Id_estado INT NOT NULL,
  Nombre_estado VARCHAR(100) NOT NULL,
  PRIMARY KEY (Id_estado)
);

DROP TABLE IF EXISTS Equipo_de_Soporte CASCADE;
CREATE TABLE Equipo_de_Soporte
(
  Id_equipo_soporte INT NOT NULL,
  Nombre_equipo_soporte VARCHAR(100) NOT NULL,
  Id_estado INT NOT NULL,
  Id_disponibilidad INT NOT NULL,
  Id_tipo INT NOT NULL,
  Horas_Uso INT NOT NULL,
  Id_almacen INT NOT NULL,
  Descripcion VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_equipo_soporte),
  FOREIGN KEY (Id_estado) REFERENCES Estado_Equipo_Soporte(Id_estado),
  FOREIGN KEY (Id_disponibilidad) REFERENCES Disponibilidad_Equipo_Soporte(Id_disponibilidad),
  FOREIGN KEY (Id_tipo) REFERENCES Tipo_Equipo_Soporte(Id_tipo),
  FOREIGN KEY (Id_almacen) REFERENCES Almacen(Id_almacen)
);

DROP TABLE IF EXISTS Detalle_Pedido_Compra CASCADE;
CREATE TABLE Detalle_Pedido_Compra
(
  Id_detalle_pedido INT NOT NULL,
  Id_producto INT NOT NULL,
  Precio_unitario FLOAT NULL,
  Cantidad INT NOT NULL,
  Id_pedido_compra INT NOT NULL,
  PRIMARY KEY (Id_detalle_pedido),
  FOREIGN KEY (Id_pedido_compra) REFERENCES Pedido_Compra(Id_pedido_compra)
);

DROP TABLE IF EXISTS Historial_Estados_Pedido CASCADE;
CREATE TABLE Historial_Estados_Pedido
(
  Id_historial INT NOT NULL,  
  Fecha_cambio DATE NOT NULL,
  Hora_cambio TIME NOT NULL,
  Estado_anterior INT NOT NULL,
  Estado_nuevo INT NOT NULL,
  Id_pedido_compra INT NOT NULL,
  PRIMARY KEY (id_historial),
  FOREIGN KEY (Id_pedido_compra) REFERENCES Pedido_Compra(Id_pedido_compra)
);

DROP TABLE IF EXISTS Orden_de_trabajo CASCADE;
CREATE TABLE Orden_de_trabajo
(
  Id_Orden INT NOT NULL,
  Descripcion VARCHAR(255) NOT NULL,
  Fecha_Orden DATE NOT NULL,
  visible VARCHAR(1) NOT NULL,
  PRIMARY KEY (Id_Orden)
);

DROP TABLE IF EXISTS Actividad_empleado CASCADE;
CREATE TABLE Actividad_empleado
(
  Id_actvempleado INT NOT NULL,
  nombre_actv VARCHAR(100) ,
  Descripcion_actv VARCHAR(255) ,
  fecha_inicio DATE,
  fecha_fin DATE,
  Id_equipo_soporte INT,
  Id_empleado INT,
  id_estado INT,
  Id_Orden INT NOT NULL,
  PRIMARY KEY (Id_actvempleado),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (id_estado) REFERENCES Estado_actv(id_estado),
  FOREIGN KEY (Id_equipo_soporte) REFERENCES Equipo_de_Soporte(Id_equipo_soporte),
  FOREIGN KEY (Id_Orden) REFERENCES Orden_de_trabajo(Id_Orden)
);

DROP TABLE IF EXISTS ActvempleadoXOrdenTrabajo CASCADE;
CREATE TABLE ActvempleadoXOrdenTrabajo
(
  Id_actvempleado INT NOT NULL,
  Id_Orden INT NOT NULL,
  Id_equipo_soporte INT NOT NULL,
  PRIMARY KEY (Id_actvempleado, ID_Orden),
  FOREIGN KEY (Id_actvempleado) REFERENCES Actividad_empleado(Id_actvempleado),
  FOREIGN KEY (Id_Orden) REFERENCES Orden_de_trabajo(Id_Orden),
  FOREIGN KEY (Id_equipo_soporte) REFERENCES Equipo_de_Soporte(Id_equipo_soporte)
);

DROP TABLE IF EXISTS Tipo_maquina CASCADE;
CREATE TABLE Tipo_maquina
(
    id_tipo_maquina INT NOT NULL,
    nombre_tipo VARCHAR(300),
    PRIMARY KEY (id_tipo_maquina)
);

DROP TABLE IF EXISTS Estado_maquina CASCADE;
CREATE TABLE Estado_maquina
(
    id_estado_maquina INT NOT NULL,
    nombre_estado VARCHAR(300),
    PRIMARY KEY (id_estado_maquina)
);

DROP TABLE IF EXISTS Marca_maquina CASCADE;
CREATE TABLE Marca_maquina
(
    id_marca_maquina INT NOT NULL,
    nombre_marca VARCHAR(100) NOT NULL,
	PRIMARY KEY (id_marca_maquina)
);

DROP TABLE IF EXISTS Maquina CASCADE;
CREATE TABLE Maquina
(
    id_maquina INT NOT NULL,
    Fecha_ultima_inspeccion DATE NOT NULL,
    Fecha_adquisicion DATE NOT NULL,
    id_tipo_maquina INT NOT NULL, 
    id_estado_maquina INT NOT NULL,
    id_marca_maquina INT NOT NULL,
    PRIMARY KEY (Id_maquina),
    FOREIGN KEY (id_marca_maquina) REFERENCES Marca_maquina(id_marca_maquina),
    FOREIGN KEY (id_tipo_maquina) REFERENCES Tipo_maquina(id_tipo_maquina),
    FOREIGN KEY (id_estado_maquina) REFERENCES Estado_maquina(id_estado_maquina)
);

DROP TABLE IF EXISTS Tipo_mantenimiento CASCADE;
CREATE TABLE Tipo_mantenimiento
(
  id_tipo_mant INT NOT NULL,
  nombre_tipo_mant VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_tipo_mant)
);

DROP TABLE IF EXISTS Estado_mantto CASCADE;
CREATE TABLE Estado_mantto
(
  id_estado INT NOT NULL,
  estado VARCHAR(20) NOT NULL,
  PRIMARY KEY (id_estado)
);

DROP TABLE IF EXISTS Mantenimiento CASCADE;
CREATE TABLE Mantenimiento
(
  Id_Act_mantto INT NOT NULL,
  Fecha_inicio_programado DATE NOT NULL,
  Fecha_fin_programado DATE NOT NULL,
  Id_Orden INT,
  Id_plan INT NOT NULL,
  id_tipo_mant INT NOT NULL,
  Id_maquina INT NOT NULL,
  id_estado INT NOT NULL,
  PRIMARY KEY (Id_Act_mantto),
  FOREIGN KEY (Id_Orden) REFERENCES Orden_de_trabajo(Id_Orden) ON DELETE CASCADE,
  FOREIGN KEY (Id_plan) REFERENCES Plan_de_mantenimiento(Id_plan) ON DELETE CASCADE,
  FOREIGN KEY (id_tipo_mant) REFERENCES Tipo_mantenimiento(id_tipo_mant),
  FOREIGN KEY (id_maquina) REFERENCES Maquina(Id_maquina),
  FOREIGN KEY (id_estado) REFERENCES Estado_mantto(id_estado)
);
DROP TABLE IF EXISTS InsumoXMantenimiento CASCADE;
CREATE TABLE InsumoXMantenimiento
(
  Id_insum_mant SERIAL NOT NULL,
  Cantidad INT NOT NULL,
  Id_Act_mantto INT NOT NULL,
  Id_insumo INT NOT NULL,
  PRIMARY KEY (Id_insum_mant),
  FOREIGN KEY (Id_Act_mantto) REFERENCES Mantenimiento(Id_Act_mantto),
  FOREIGN KEY (Id_insumo) REFERENCES Insumo(Id_insumo)
);

DROP TABLE IF EXISTS EquipoSXMantenimiento CASCADE;
CREATE TABLE EquipoSXMantenimiento
(
  id_equipo_mant INT NOT NULL,
  Id_Act_mantto INT NOT NULL,
  Id_equipo_soporte INT NOT NULL,
  PRIMARY KEY (id_equipo_mant),
  FOREIGN KEY (Id_Act_mantto) REFERENCES Mantenimiento(Id_Act_mantto),
  FOREIGN KEY (Id_equipo_soporte) REFERENCES Equipo_de_Soporte(Id_equipo_soporte)
);

DROP TABLE IF EXISTS Tipos_Contacto CASCADE;
CREATE TABLE Tipos_Contacto
(
  Id_tipo_contacto CHAR(4) NOT NULL,
  Nombre_tipo_contacto CHAR(255) NOT NULL,
  PRIMARY KEY (Id_tipo_contacto)
);

DROP TABLE IF EXISTS Tipos_Peligro CASCADE;
CREATE TABLE Tipos_Peligro
(
  Id_tipo_peligro CHAR(7) NOT NULL,
  Nombre_tipo_peligro CHAR(255) NOT NULL,
  PRIMARY KEY (Id_tipo_peligro)
);

DROP TABLE IF EXISTS Tipo_Riesgo CASCADE;
CREATE TABLE Tipo_Riesgo
(
  Id_tipo_riesgo CHAR(2) NOT NULL,
  Nombre_tipo_riesgo CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_riesgo)
);

DROP TABLE IF EXISTS Tipo_Medida_Control CASCADE;
CREATE TABLE Tipo_Medida_Control
(
  Id_tipo_med_control CHAR(3) NOT NULL,
  Nombre_tipo_med_control CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_med_control)
);

DROP TABLE IF EXISTS Tipo_Estrategia_Control CASCADE;
CREATE TABLE Tipo_Estrategia_Control
(
  Id_tipo_estgia_control CHAR(1) NOT NULL,
  Nombre_tipo_estgia_control CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_estgia_control)
);

DROP TABLE IF EXISTS Tipo_Probabilidad CASCADE;
CREATE TABLE Tipo_Probabilidad
(
  Id_tipo_probabilidad CHAR(5) NOT NULL,
  Nombre_tipo_probabilidad CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_probabilidad)
);

DROP TABLE IF EXISTS Tipo_Severidad CASCADE;
CREATE TABLE Tipo_Severidad
(
  Id_tipo_severidad CHAR(2) NOT NULL,
  Nombre_tipo_severidad CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_severidad)
);

DROP TABLE IF EXISTS Tipo_Q_control CASCADE;
CREATE TABLE Tipo_Q_control
(
  Id_tipo_Qcontrol CHAR(1) NOT NULL,
  Nombre_tipo_Qcontrol CHAR(255) NOT NULL,
  PRIMARY KEY (Id_tipo_Qcontrol)
);

DROP TABLE IF EXISTS Equipo_evaluador CASCADE;
CREATE TABLE Equipo_evaluador
(
  Id_equipo_evaluador SERIAL PRIMARY KEY,
  Cant_empleados INT
);

DROP TABLE IF EXISTS proceso CASCADE;
CREATE TABLE proceso
(
  Descripcion_proceso CHAR(255) NOT NULL,
  Id_proceso INT NOT NULL,
  Id_equipo_evaluador INT NOT NULL,
  PRIMARY KEY (Id_proceso),
  FOREIGN KEY (Id_equipo_evaluador) REFERENCES Equipo_evaluador(Id_equipo_evaluador)
);

DROP TABLE IF EXISTS Actividades CASCADE;
CREATE TABLE Actividades
(
  Id_actividad INT NOT NULL,
  Descripcion_actividad CHAR(255) NOT NULL,
  Id_proceso INT NOT NULL,
  PRIMARY KEY (Id_actividad),
  FOREIGN KEY (Id_proceso) REFERENCES proceso(Id_proceso)
);

DROP TABLE IF EXISTS Tareas CASCADE;
CREATE TABLE Tareas
(
  Descripcion_tarea CHAR(255) NOT NULL,
  Puesto_trabajo CHAR(255) NOT NULL,
  Cond_operacional CHAR(255) NOT NULL,
  Id_tarea INT NOT NULL,
  Id_actividad INT NOT NULL,
  PRIMARY KEY (Id_tarea),
  FOREIGN KEY (Id_actividad) REFERENCES Actividades(Id_actividad)
);

DROP TABLE IF EXISTS Identificacion_del_riesgo CASCADE;
CREATE TABLE Identificacion_del_riesgo
(
  Descripcion_peligro CHAR(255) NOT NULL,
  Evento_no_deseado CHAR(255) NOT NULL,
  id_tarea INT NOT NULL,
  Id_riesgo INT NOT NULL,
  PRIMARY KEY (Id_riesgo),
  FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea)
);

DROP TABLE IF EXISTS Analisis_riesgo CASCADE;
CREATE TABLE Analisis_riesgo
(
  Afectado CHAR(255) NOT NULL,
  Consecuencia CHAR(255) NOT NULL,
  Id_analisis INT NOT NULL,
  Id_riesgo INT NOT NULL,
  Id_tipo_contacto CHAR(4) NOT NULL,
  Id_tipo_peligro CHAR(7) NOT NULL,
  PRIMARY KEY (Id_analisis),
  FOREIGN KEY (Id_riesgo) REFERENCES Identificacion_del_riesgo(Id_riesgo),
  FOREIGN KEY (Id_tipo_contacto) REFERENCES Tipos_Contacto(Id_tipo_contacto),
  FOREIGN KEY (Id_tipo_peligro) REFERENCES Tipos_Peligro(Id_tipo_peligro)
);

DROP TABLE IF EXISTS Valoracion_del_riesgo_inicial CASCADE;
CREATE TABLE Valoracion_del_riesgo_inicial
(
  id_valoracion_inicial INT NOT NULL,
  Id_tipo_severidad CHAR(2) NOT NULL,
  Id_tipo_probabilidad CHAR(5) NOT NULL,
  Id_tipo_riesgo CHAR(2) NOT NULL,
  Id_analisis INT NOT NULL,  -- Relación con Analisis_riesgo
  PRIMARY KEY (id_valoracion_inicial),
  FOREIGN KEY (Id_tipo_severidad) REFERENCES Tipo_Severidad(Id_tipo_severidad),
  FOREIGN KEY (Id_tipo_probabilidad) REFERENCES Tipo_Probabilidad(Id_tipo_probabilidad),
  FOREIGN KEY (Id_tipo_riesgo) REFERENCES Tipo_Riesgo(Id_tipo_riesgo),
  FOREIGN KEY (Id_analisis) REFERENCES Analisis_riesgo(Id_analisis)
);




DROP TABLE IF EXISTS Valoracion_del_riesgo CASCADE;
CREATE TABLE Valoracion_del_riesgo
(
  Id_valoracion_residual INT NOT NULL,
  Cantidad_controles INT NOT NULL,
  Id_valoracion_inicial INT NOT NULL,
  Id_tipo_riesgo CHAR(2) NOT NULL,
  PRIMARY KEY (Id_valoracion_residual),
  FOREIGN KEY (Id_valoracion_inicial) REFERENCES Valoracion_del_riesgo_inicial(id_valoracion_inicial),
  FOREIGN KEY (Id_tipo_riesgo) REFERENCES Tipo_Riesgo(Id_tipo_riesgo)
);


DROP TABLE IF EXISTS Control CASCADE;
CREATE TABLE Control
(
  Id_control INT NOT NULL,
  Descripcion_control CHAR(400) NOT NULL,
  Id_tipo_estgia_control CHAR(1) NOT NULL,
  Id_tipo_med_control CHAR(3) NOT NULL,
  Id_tipo_Qcontrol CHAR(1) NOT NULL,
  Id_valoracion_residual INT NOT NULL,
  PRIMARY KEY (Id_control),
  FOREIGN KEY (Id_tipo_estgia_control) REFERENCES Tipo_Estrategia_Control(Id_tipo_estgia_control),
  FOREIGN KEY (Id_tipo_med_control) REFERENCES Tipo_Medida_Control(Id_tipo_med_control),
  FOREIGN KEY (Id_tipo_Qcontrol) REFERENCES Tipo_Q_control(Id_tipo_Qcontrol),
  FOREIGN KEY (Id_valoracion_residual) REFERENCES Valoracion_del_riesgo(Id_valoracion_residual)
);

DROP TABLE IF EXISTS Plan_de_accion_de_mejora CASCADE;
CREATE TABLE Plan_de_accion_de_mejora
(
  Accion_que CHAR(255) NOT NULL,
  Fecha_cuando DATE NOT NULL,
  Cantidad_empleados INT NOT NULL,
  Id_plan_mejora INT NOT NULL,
  Id_valoracion_residual INT NOT NULL,
  PRIMARY KEY (Id_plan_mejora),
  FOREIGN KEY (Id_valoracion_residual) REFERENCES Valoracion_del_riesgo(Id_valoracion_residual)
);

DROP TABLE IF EXISTS PlanaccionxEmpleado CASCADE;
CREATE TABLE PlanaccionxEmpleado
(
  id_plan_mejora INT NOT NULL,
  id_empleado INT NOT NULL,
  PRIMARY KEY (id_plan_mejora, id_empleado),
  FOREIGN KEY (id_plan_mejora) REFERENCES Plan_de_accion_de_mejora(Id_plan_mejora),
  FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);

DROP TABLE IF EXISTS EquipoEvaluadorXEmpleado CASCADE;
CREATE TABLE EquipoEvaluadorXEmpleado (
    id_equipo_evaluador INT NOT NULL,
    id_empleado INT NOT NULL,
    PRIMARY KEY (id_equipo_evaluador, id_empleado),
    FOREIGN KEY (id_equipo_evaluador) REFERENCES Equipo_evaluador(id_equipo_evaluador),
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);

DROP TABLE IF EXISTS Informe_IPERC CASCADE;
CREATE TABLE Informe_IPERC (
  id_informe_iperc SERIAL PRIMARY KEY,
  id_proceso INT NOT NULL,
  fecha_registro DATE NOT NULL,
  fecha_act_sup DATE NOT NULL,
  fecha_act_seg DATE NOT NULL,
  unidad_minera CHAR(50) NOT NULL,
  area CHAR(50) NOT NULL,
  FOREIGN KEY (id_proceso) REFERENCES proceso(id_proceso)
);


-- Eliminar y crear la tabla Reg_riesgo_analizado
DROP TABLE IF EXISTS Reg_riesgo_analizado CASCADE;
CREATE TABLE Reg_riesgo_analizado
(
  Id_reg_riesgo_analizado INT NOT NULL,
  id_valoracion_inicial INT NOT NULL,
  Id_plan_mejora INT NOT NULL,
  Id_analisis INT NOT NULL,
  Id_valoracion_residual INT NOT NULL,
  Id_informe_iperc INT NOT NULL,
  PRIMARY KEY (Id_reg_riesgo_analizado),
  FOREIGN KEY (id_valoracion_inicial) REFERENCES Valoracion_del_riesgo_inicial(id_valoracion_inicial),
  FOREIGN KEY (Id_plan_mejora) REFERENCES Plan_de_accion_de_mejora(Id_plan_mejora),
  FOREIGN KEY (Id_analisis) REFERENCES Analisis_riesgo(Id_analisis),
  FOREIGN KEY (Id_valoracion_residual) REFERENCES Valoracion_del_riesgo(Id_valoracion_residual),
  FOREIGN KEY (Id_informe_iperc) REFERENCES Informe_IPERC(Id_informe_iperc)
);

DROP TABLE IF EXISTS Auditoria CASCADE;
CREATE TABLE Auditoria
(
    Id_Auditoria INT NOT NULL,
    Id_Act_mantto INT NOT NULL,
    Fecha_auditoria DATE,
    ubi_auditoria VARCHAR(2000),
    resp_auditoria VARCHAR(1000),
    Descripcion VARCHAR(3000),
    objetivo VARCHAR(2000),
    PRIMARY KEY (Id_Auditoria),
    FOREIGN KEY (Id_Act_mantto) REFERENCES Mantenimiento (Id_Act_mantto)
);

DROP TABLE IF EXISTS Capacitaciones CASCADE;
CREATE TABLE Capacitaciones
(
    Id_Capacitacion INT NOT NULL,
    Fecha_Capacitacion DATE,
    Descripcion VARCHAR(1000),
    Duracion FLOAT,
    Instructor VARCHAR(20000),
    Evaluador VARCHAR(20000),
    PRIMARY KEY (Id_Capacitacion)
);

DROP TABLE IF EXISTS EmpleadoxCapacitacion CASCADE;
CREATE TABLE EmpleadoxCapacitacion
(
    Id_empleado INT NOT NULL,
    Id_Capacitacion INT NOT NULL,
    FOREIGN KEY (Id_empleado) REFERENCES Empleado (Id_empleado),
    FOREIGN KEY (Id_Capacitacion) REFERENCES Capacitaciones (Id_Capacitacion)
);

DROP TABLE IF EXISTS Estado_Reporte CASCADE;
CREATE TABLE Estado_Reporte
(
  id_estado_reporte INT NOT NULL,
  nombre_estado VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_estado_reporte)
);

DROP TABLE IF EXISTS Reportes CASCADE;
CREATE TABLE Reportes
(
  Id_reporte SERIAL,
  Fecha_reporte DATE NOT NULL,
  id_estado_reporte INT NOT NULL,
  Id_supervisor INT,
  id_jefe INT NOT NULL,
  PRIMARY KEY (Id_reporte),
  FOREIGN KEY (id_estado_reporte) REFERENCES Estado_Reporte (id_estado_reporte),
  FOREIGN KEY (id_jefe) REFERENCES Empleado (Id_empleado),
  FOREIGN KEY (id_supervisor) REFERENCES Empleado (Id_empleado)
);

DROP TABLE IF EXISTS Registro CASCADE;
CREATE TABLE Registro
(
  Id_registro SERIAL,
  Fecha_registro TIMESTAMP NOT NULL,
  Fecha_inicial TIMESTAMP NOT NULL,
  Id_empleado INT NOT NULL,
  Id_Act_mantto INT NOT NULL,
  Calificacion INT NOT NULL,
  Observaciones VARCHAR(255),
  PRIMARY KEY (Id_registro),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_Act_mantto) REFERENCES Mantenimiento(Id_Act_mantto)
);

DROP TABLE IF EXISTS Incidencias_Tag CASCADE;
CREATE TABLE Incidencias_Tag
(
  Id_incidencias SERIAL,
  Incidencia VARCHAR(50) NOT NULL,
  id_Registro INT NOT NULL, 
  PRIMARY KEY (Id_incidencias),
  FOREIGN KEY (id_Registro) REFERENCES Registro(Id_registro) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Tipo_notificacion CASCADE;
CREATE TABLE Tipo_notificacion
(
  id_tipo INT NOT NULL,
  tipo VARCHAR(50),
  PRIMARY KEY (id_tipo)
);

DROP TABLE IF EXISTS Notificaciones CASCADE;
CREATE TABLE Notificaciones
(
  id_notificacion INT NOT NULL,
  Asunto VARCHAR(255) NOT NULL,
  mensaje VARCHAR(255) NOT NULL,
  fecha_notificacion TIMESTAMP NOT NULL,
  id_remitente INT NOT NULL,
  id_destinatario INT NOT NULL,
  Id_registro INT,
  Id_reporte INT,
  id_tipo INT NOT NULL,
  PRIMARY KEY (id_notificacion),
  FOREIGN KEY (id_remitente) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (id_destinatario) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_registro) REFERENCES Registro(Id_registro),
  FOREIGN KEY (Id_reporte) REFERENCES Reportes(Id_reporte),
  FOREIGN KEY (id_tipo) REFERENCES Tipo_notificacion(id_tipo)
);

CREATE SEQUENCE IF NOT EXISTS notificaciones_id_notificacion_seq;


DROP TABLE IF EXISTS Estado_Sesion CASCADE;
CREATE TABLE Estado_Sesion (
    Id_estado_sesion INT PRIMARY KEY,
    Descripcion VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Tiempo_max_sesion CASCADE;
CREATE TABLE Tiempo_max_sesion (
    Cargo VARCHAR(50) PRIMARY KEY,
    T_max_cargo INT NOT NULL,
    Codigo INT NOT NULL
);

DROP TABLE IF EXISTS Estado_autenticador CASCADE;
CREATE TABLE Estado_autenticador (
    Id_estado_autenticador INT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Sesion_Empleado CASCADE;
CREATE TABLE Sesion_Empleado (
    Id_Sesion INT PRIMARY KEY,
    Id_Empleado INT NOT NULL,
    Fecha_Hora_Inicio TIMESTAMP NOT NULL,  
    Fecha_Hora_Final TIMESTAMP,            
    Direccion_IP VARCHAR(45),
    Id_estado_sesion INT,            
    Cargo VARCHAR(50),
    t_inactividad INTERVAL,  
    FOREIGN KEY (Id_Empleado) REFERENCES Empleado(Id_Empleado),
    FOREIGN KEY (Id_estado_sesion) REFERENCES Estado_Sesion(Id_estado_sesion),
    FOREIGN KEY (Cargo) REFERENCES Tiempo_max_sesion(Cargo)
);


DROP TABLE IF EXISTS Autenticacion_en_2_pasos CASCADE;
CREATE TABLE Autenticacion_en_2_pasos (
    Id_Autenticacion INT PRIMARY KEY,
    Fecha_Hora_Envio TIMESTAMP NOT NULL,
    Contador_Intentos INT DEFAULT 0,
    Cod_Verificacion INT NOT NULL,
    Id_sesion INT NOT NULL,
    Id_estado_autenticador INT NOT NULL,
    FOREIGN KEY (Id_sesion) REFERENCES Sesion_empleado(Id_sesion),
    FOREIGN KEY (Id_estado_autenticador) REFERENCES Estado_autenticador(Id_estado_autenticador)

);

DROP TABLE IF EXISTS Estado_codigo CASCADE;
CREATE TABLE Estado_codigo (
    Id_estado_codigo VARCHAR(20) PRIMARY KEY,  
    Descripcion VARCHAR(50) NOT NULL      
);

DROP TABLE IF EXISTS Recuperacion_de_contraseña CASCADE;
CREATE TABLE Recuperacion_de_contraseña (
    Id_recupcontra INT PRIMARY KEY,               
    Id_sesion INT,                               
    Fecha_Hora_Envio TIMESTAMP NOT NULL,  
    Nueva_contraseña VARCHAR(40) NOT NULL,      
    Email_envio VARCHAR(100) NOT NULL,           
    Id_estado_codigo VARCHAR(20),                   
    FOREIGN KEY (Id_sesion) REFERENCES Sesion_Empleado (Id_Sesion),  
    FOREIGN KEY (Id_estado_codigo) REFERENCES Estado_codigo (Id_estado_codigo)  
);

-- Eliminar la tabla Sesion_sospechosa si existe
DROP TABLE IF EXISTS Sesion_sospechosa CASCADE;
CREATE TABLE Sesion_sospechosa (
    Id_Sesion INT NOT NULL,
    Id_autenticacion INT NOT NULL,
    Estado_sesion VARCHAR(20) NOT NULL,
    Direccion_mac VARCHAR(17) NOT NULL,
    Tipo_Dispositivo VARCHAR(50) NOT NULL,
    Fecha_Hora_sospecha TIMESTAMP NOT NULL,  
    Direccion_ip VARCHAR(25) NOT NULL,
    Ubicacion VARCHAR(100) NOT NULL,
    Acciones_tomadas VARCHAR(255) NOT NULL,
    PRIMARY KEY (Id_autenticacion),
    FOREIGN KEY (Id_sesion) REFERENCES Sesion_empleado(Id_sesion)
);

-- Eliminar la tabla Notificacion_Administrador si existe
DROP TABLE IF EXISTS Notificacion_Administrador CASCADE;
CREATE TABLE Notificacion_Administrador (
    Id_Notificacion INT PRIMARY KEY,
    Id_Administrador INT NOT NULL,
    Tipo_Evento VARCHAR(50) NOT NULL,
    Fecha_Hora_Notificacion TIMESTAMP NOT NULL,  
    Estado_Notificacion VARCHAR(20) NOT NULL,
    Mensaje_Notificacion VARCHAR(255) NOT NULL,
    Prioridad VARCHAR(10) NOT NULL,
    Id_Sesion INT NOT NULL,
    Id_autenticacion INT NOT NULL,
    FOREIGN KEY (Id_autenticacion) REFERENCES Sesion_sospechosa(Id_autenticacion),
    FOREIGN KEY (Id_sesion) REFERENCES Sesion_empleado(Id_sesion)
);



