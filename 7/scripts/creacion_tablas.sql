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
  RUC CHAR(12) NOT NULL,
  Empresa VARCHAR(255) NOT NULL,
  Contacto VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  Categoria VARCHAR(255) NOT NULL,
  Telefono CHAR(10) NOT NULL,
  Direccion VARCHAR(255) NOT NULL,
  PRIMARY KEY (RUC)
);

DROP TABLE IF EXISTS Recurso CASCADE;
CREATE TABLE Recurso
(
  Id_recurso INT NOT NULL,
  Cantidad INT NOT NULL,
  Nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY ( Id_recurso)
);

DROP TABLE IF EXISTS Estado CASCADE;
CREATE TABLE Estado
(
  Id_estado INT NOT NULL,
  Tipo_estado VARCHAR(255) NOT NULL,
  PRIMARY KEY (Id_estado)
);

DROP TABLE IF EXISTS Criticidad CASCADE;
CREATE TABLE Criticidad
(
  Id_criticidad INT NOT NULL,
  Nivel VARCHAR(20) NOT NULL,
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
  Observaciones VARCHAR(5000) NOT NULL,
  Fecha_plan DATE NOT NULL,
  Empleado_asigna INT NOT NULL,
  Id_criticidad INT NOT NULL,
  PRIMARY KEY (Id_plan),
  FOREIGN KEY (Empleado_asigna) REFERENCES Empleado (Id_empleado),
  FOREIGN KEY (Id_criticidad) REFERENCES Criticidad (Id_criticidad)
);

DROP TABLE IF EXISTS Pedido CASCADE;
CREATE TABLE Pedido
(
  Numero VARCHAR(255) NOT NULL,
  Cant_pedid INT NOT NULL,
  Fecha DATE NOT NULL,
  Est_inactividad CHAR(2) NOT NULL,
  Id_recurso INT NOT NULL,
  Id_empleado INT NOT NULL,
  Id_estado INT NOT NULL,
  PRIMARY KEY (Numero),
  FOREIGN KEY (Id_recurso) REFERENCES Recurso (Id_recurso),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado (Id_empleado),
  FOREIGN KEY (Id_estado) REFERENCES Estado (Id_estado)
);

DROP TABLE IF EXISTS Orden_de_compra CASCADE;
CREATE TABLE Orden_de_compra
(
  Fecha_emision DATE NOT NULL,
  Fecha_posible_entrega DATE NOT NULL,
  Descripcion VARCHAR(255) NOT NULL,
  Cantidad INT NOT NULL,
  Prioridad VARCHAR NOT NULL,
  Precio_unitario FLOAT NOT NULL,
  Id_orden_compra INT NOT NULL,
  Descripcion_product VARCHAR(255) NOT NULL,
  RUC_proveedor CHAR(12) NOT NULL,
  Id_empleado INT NOT NULL,
  PRIMARY KEY (Id_orden_compra),
  FOREIGN KEY (RUC_proveedor) REFERENCES Proveedor(RUC),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado)
);

DROP TABLE IF EXISTS Actividad_empleado CASCADE;
CREATE TABLE Actividad_empleado
(
  Id_actvempleado INT NOT NULL,
  Descripcion_actv VARCHAR(10000) NOT NULL,
  Id_empleado INT NOT NULL,
  PRIMARY KEY (Id_actvempleado),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado)
);

DROP TABLE IF EXISTS Orden_de_trabajo CASCADE;
CREATE TABLE Orden_de_trabajo
(
  Id_Orden INT NOT NULL,
  Fecha_Orden DATE NOT NULL,
  Descripcion VARCHAR(10000) NOT NULL,
  Empleado_asigna INT NOT NULL,
  Id_criticidad INT NOT NULL,
  PRIMARY KEY (Id_Orden),
  FOREIGN KEY (Empleado_asigna) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_criticidad) REFERENCES Criticidad(Id_criticidad)
);

DROP TABLE IF EXISTS Categoria_Almacen CASCADE;
CREATE TABLE Categoria_Almacen 
(
    Id_categoria INT PRIMARY KEY,
    Nombre_categoria VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Estado_Almacen CASCADE;
CREATE TABLE Estado_Almacen 
(
    Id_estado INT PRIMARY KEY,
    Nombre_estado VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Almacen CASCADE;
CREATE TABLE Almacen
(
    Id_almacen INT PRIMARY KEY,
    Id_empleado INT,
    Id_categoria INT,
    Id_estado INT,
    Direccion VARCHAR(255),
    Capacidad INT,
    FOREIGN KEY (Id_empleado) REFERENCES Empleado (Id_empleado),
    FOREIGN KEY (Id_categoria) REFERENCES Categoria_Almacen (Id_categoria),
    FOREIGN KEY (Id_estado) REFERENCES Estado_Almacen (Id_estado)
);

DROP TABLE IF EXISTS Tipo_Equipo_Soporte CASCADE;
CREATE TABLE Tipo_Equipo_Soporte
(
  Id_tipo INT PRIMARY KEY,
  Nombre_tipo VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Disponibilidad_Equipo_Soporte CASCADE;
CREATE TABLE Disponibilidad_Equipo_Soporte 
(
    Id_disponibilidad INT PRIMARY KEY,
    Nombre_disponibilidad VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Estado_Equipo_Soporte CASCADE;
CREATE TABLE Estado_Equipo_Soporte
(
    Id_estado INT PRIMARY KEY,
    Nombre_estado VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Equipo_de_Soporte CASCADE;
CREATE TABLE Equipo_de_Soporte 
(
    Id_equipo_soporte INT PRIMARY KEY,
    Nombre_equipo_soporte VARCHAR(100) NOT NULL,
    Fecha_adquisicion DATE,
    Descripcion VARCHAR(255),
    Horas_uso INT NOT NULL,
    Id_almacen INT NOT NULL,
    Id_estado INT NOT NULL,
    Id_disponibilidad INT NOT NULL,
    Id_tipo INT NOT NULL,
    FOREIGN KEY (Id_tipo) REFERENCES Tipo_Equipo_Soporte (Id_tipo),
    FOREIGN KEY (Id_almacen) REFERENCES Almacen (Id_almacen),
    FOREIGN KEY (Id_disponibilidad) REFERENCES Disponibilidad_Equipo_Soporte (Id_disponibilidad),
    FOREIGN KEY (Id_estado) REFERENCES Estado_Equipo_Soporte (Id_estado)
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

DROP TABLE IF EXISTS Registro_compra_recursos CASCADE;
CREATE TABLE Registro_compra_recursos
(
  Fecha_registro DATE NOT NULL,
  Descripcion VARCHAR(255) NOT NULL,
  Id_reg_recurso INT NOT NULL,
  Cantidad INT NOT NULL,
  Id_almacen INT NOT NULL,
  Id_orden_compra INT NOT NULL,
  Id_recurso INT NOT NULL,
  PRIMARY KEY (Id_reg_recurso),
  FOREIGN KEY (Id_almacen) REFERENCES Almacen(Id_almacen),
  FOREIGN KEY (Id_orden_compra) REFERENCES Orden_de_compra(Id_orden_compra),
  FOREIGN KEY (Id_recurso) REFERENCES Recurso(Id_recurso)
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
    id_marca_maquina INT NOT NULL,
    id_tipo_maquina INT NOT NULL, 
    id_estado_maquina INT NOT NULL,
    PRIMARY KEY (Id_maquina),
    FOREIGN KEY (id_marca_maquina) REFERENCES Marca_maquina(id_marca_maquina),
    FOREIGN KEY (id_tipo_maquina) REFERENCES Tipo_maquina(id_tipo_maquina),
    FOREIGN KEY (id_estado_maquina) REFERENCES Estado_maquina(id_estado_maquina)
);

DROP TABLE IF EXISTS Tipo_mantenimiento CASCADE;
CREATE TABLE Tipo_mantenimiento
(
    id_tipo_mant INT NOT NULL,
    nombre_tipo_mant VARCHAR(300),
    PRIMARY KEY (id_tipo_mant)
);

DROP TABLE IF EXISTS Mantenimiento CASCADE;
CREATE TABLE Mantenimiento
(
    Id_Act_mantto INT NOT NULL,
    Descripcion VARCHAR(1000),
    Tarea VARCHAR(1000),
    Peligros VARCHAR(1000), 
    Fecha_inicio_programado DATE, 
    Fecha_fin_programado DATE, 
    Id_Orden INT NOT NULL,
    Id_plan INT NOT NULL,
    Id_maquina INT NOT NULL,
    id_tipo_mant INT NOT NULL,
    PRIMARY KEY (Id_Act_mantto),
    FOREIGN KEY (Id_Orden) REFERENCES Orden_de_trabajo (Id_Orden),
    FOREIGN KEY (Id_plan) REFERENCES Plan_de_mantenimiento (Id_plan),
    FOREIGN KEY (Id_maquina) REFERENCES Maquina (Id_maquina),
    FOREIGN KEY (id_tipo_mant) REFERENCES Tipo_mantenimiento (id_tipo_mant)
);

DROP TABLE IF EXISTS RecursoXMantenimiento CASCADE;
CREATE TABLE RecursoXMantenimiento
(
  Id_RecursoXMantto INT NOT NULL,
  Id_Act_mantto INT NOT NULL,
  Id_recurso INT NOT NULL,
  PRIMARY KEY (Id_RecursoXMantto),
  FOREIGN KEY (Id_Act_mantto) REFERENCES Mantenimiento(Id_Act_mantto),
  FOREIGN KEY (Id_recurso) REFERENCES Recurso(Id_recurso)
);

DROP TABLE IF EXISTS Identificacion_del_riesgo CASCADE;
CREATE TABLE Identificacion_del_riesgo
(
  Descripcion_peligro CHAR(255) NOT NULL,
  Evento_no_deseado CHAR(255) NOT NULL,
  Id_riesgo INT NOT NULL,
  PRIMARY KEY (Id_riesgo)
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
  Id_equipo_evaluador INT NOT NULL,
  Cant_empleados INT NOT NULL,
  Id_empleado INT NOT NULL,
  PRIMARY KEY (Id_equipo_evaluador),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado)
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
  PRIMARY KEY (id_valoracion_inicial),
  FOREIGN KEY (Id_tipo_severidad) REFERENCES Tipo_Severidad(Id_tipo_severidad),
  FOREIGN KEY (Id_tipo_probabilidad) REFERENCES Tipo_Probabilidad(Id_tipo_probabilidad),
  FOREIGN KEY (Id_tipo_riesgo) REFERENCES Tipo_Riesgo(Id_tipo_riesgo)
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



DROP TABLE IF EXISTS TareasXIdentRiesgo CASCADE;
CREATE TABLE TareasXIdentRiesgo
(
  Id_tarea INT NOT NULL,
  Id_riesgo INT NOT NULL,
  PRIMARY KEY (Id_tarea, Id_riesgo),
  FOREIGN KEY (Id_tarea) REFERENCES Tareas(Id_tarea),
  FOREIGN KEY (Id_riesgo) REFERENCES Identificacion_del_riesgo(Id_riesgo)
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
  Descripcion_control CHAR(255) NOT NULL,
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

-- Eliminar y crear la tabla Informe_IPERC
DROP TABLE IF EXISTS Informe_IPERC CASCADE;
CREATE TABLE Informe_IPERC
(
  Fecha_registro DATE NOT NULL,
  Id_informe_iperc INT NOT NULL,
  Cant_riesgos_analizados INT NOT NULL,
  Id_proceso INT NOT NULL,
  PRIMARY KEY (Id_informe_iperc),
  FOREIGN KEY (Id_proceso) REFERENCES proceso(Id_proceso)
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
  Id_reporte INT NOT NULL,
  Fecha_reporte DATE NOT NULL,
  id_estado_reporte INT NOT NULL,
  Comentarios VARCHAR(255),
  Id_supervisor INT NOT NULL,
  PRIMARY KEY (Id_reporte),
  FOREIGN KEY (id_estado_reporte) REFERENCES Estado_Reporte (id_estado_reporte),
  FOREIGN KEY (id_supervisor) REFERENCES Empleado (Id_empleado)
);

DROP TABLE IF EXISTS Registro CASCADE;
CREATE TABLE Registro
(
  Id_registro INT NOT NULL,
  Fecha_registro DATE NOT NULL,
  Fecha_inicial DATE NOT NULL,
  Duracion FLOAT NOT NULL,
  Id_empleado INT NOT NULL,
  Id_Act_mantto INT NOT NULL,
  Costos NUMERIC(10, 2) CHECK (Costos >= 0),
  Observaciones VARCHAR(255),
  PRIMARY KEY (Id_registro),
  FOREIGN KEY (Id_empleado) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_Act_mantto) REFERENCES Mantenimiento(Id_Act_mantto)
);

DROP TABLE IF EXISTS Notificaciones CASCADE;
CREATE TABLE Notificaciones
(
  Id_Notificacion INT NOT NULL,
  Fecha_notificacion DATE NOT NULL,
  Mensaje TEXT NOT NULL,
  id_remitente INT NOT NULL,
  id_destinatario INT NOT NULL,
  id_registro INT NOT NULL,
  id_reporte INT NOT NULL,
  PRIMARY KEY (Id_Notificacion),
  FOREIGN KEY (id_registro) REFERENCES Registro(Id_registro),
  FOREIGN KEY (Id_reporte) REFERENCES Reportes(Id_Reporte),
  FOREIGN KEY (Id_remitente) REFERENCES Empleado(Id_empleado),
  FOREIGN KEY (Id_destinatario) REFERENCES Empleado(Id_empleado)
);


DROP TABLE IF EXISTS Analisis_Reporte CASCADE;
CREATE TABLE AnalisisReporte
(
  Id_Analisis_reporte INT NOT NULL,
  Id_reporte INT NOT NULL,
  Fecha_analisis DATE NOT NULL,
  Analisis TEXT NOT NULL,
  Id_supervisor INT NOT NULL,
  PRIMARY KEY (Id_Analisis_reporte),
  FOREIGN KEY (Id_reporte) REFERENCES Reportes(Id_reporte),
  FOREIGN KEY (Id_supervisor) REFERENCES Empleado(Id_empleado)
);

-- Eliminar la tabla Sesion_sospechosa si existe
DROP TABLE IF EXISTS Sesion_sospechosa CASCADE;
CREATE TABLE Sesion_sospechosa (
    Id_Sesion_sospechosa INT NOT NULL,
    Id_autenticacion INT NOT NULL,
    Estado_sesion VARCHAR(20) NOT NULL,
    Direccion_mac VARCHAR(17) NOT NULL,
    Tipo_Dispositivo VARCHAR(50) NOT NULL,
    Fecha_Hora_sospecha TIMESTAMP NOT NULL,  
    Direccion_ip VARCHAR(25) NOT NULL,
    Ubicacion VARCHAR(100) NOT NULL,
    Acciones_tomadas VARCHAR(255) NOT NULL,
    PRIMARY KEY (Id_Sesion_sospechosa, Id_autenticacion)
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
    Id_Sesion_sospechosa INT NOT NULL,
    Id_autenticacion INT NOT NULL,
    FOREIGN KEY (Id_Sesion_sospechosa, Id_autenticacion) REFERENCES Sesion_sospechosa(Id_Sesion_sospechosa, Id_autenticacion)
);

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
    FOREIGN KEY (Id_Empleado) REFERENCES Empleado(Id_Empleado),
    FOREIGN KEY (Id_estado_sesion) REFERENCES Estado_Sesion(Id_estado_sesion),
    FOREIGN KEY (Cargo) REFERENCES Tiempo_max_sesion(Cargo)
);

DROP TABLE IF EXISTS Autenticacion_en_2_pasos CASCADE;
CREATE TABLE Autenticacion_en_2_pasos (
    Id_Autenticacion INT PRIMARY KEY,
    Fecha_Hora_Envio DATE NOT NULL,
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

