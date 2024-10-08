DROP TABLE IF EXISTS Cargo_empleado;
CREATE TABLE Cargo_empleado 
( 
    Id_cargo INT PRIMARY KEY, 
    Nombre_cargo VARCHAR(50) NOT NULL 
);

DROP TABLE IF EXISTS Criticidad;
CREATE TABLE Criticidad
(
  Id_criticidad INT NOT NULL,
  Nivel VARCHAR(20) NOT NULL,
  PRIMARY KEY (Id_criticidad)
);

DROP TABLE IF EXISTS Empleado;
CREATE TABLE Empleado 
( 
    Codigo_empleado INT PRIMARY KEY, 
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
    CONSTRAINT fk_cargo_empleado FOREIGN KEY (Id_cargo) REFERENCES Cargo_empleado(Id_cargo) 
);

DROP TABLE IF EXISTS Plan_de_mantenimiento;
CREATE TABLE Plan_de_mantenimiento
(
  Codigo_plan INT NOT NULL,
  Observaciones VARCHAR(5000) NOT NULL,
  Fecha_plan DATE NOT NULL,
  Empleado_asigna INT NOT NULL,
  Criticidad INT NOT NULL,
  PRIMARY KEY (Codigo_plan),
  FOREIGN KEY (Empleado_asigna) REFERENCES Empleado(Codigo_empleado),
  FOREIGN KEY (Criticidad) REFERENCES Criticidad(Id_criticidad)
);

DROP TABLE IF EXISTS Actividad_empleado;
CREATE TABLE Actividad_empleado
(
  Id_actvempleado INT NOT NULL,
  Descripcion_actv VARCHAR(10000) NOT NULL,
  Codigo_empleado INT NOT NULL,
  PRIMARY KEY (Id_actvempleado),
  FOREIGN KEY (Codigo_empleado) REFERENCES Empleado(Codigo_empleado)
);

DROP TABLE IF EXISTS Orden_de_trabajo;
CREATE TABLE Orden_de_trabajo
(
  Id_Orden INT NOT NULL,
  Fecha_Orden DATE NOT NULL,
  Descripcion VARCHAR(10000) NOT NULL,
  Empleado_asigna INT NOT NULL,
  Prioridad INT NOT NULL,
  PRIMARY KEY (Id_Orden),
  FOREIGN KEY (Empleado_asigna) REFERENCES Empleado(Codigo_empleado),
  FOREIGN KEY (Prioridad) REFERENCES Criticidad(Id_criticidad)
);

DROP TABLE IF EXISTS ActvempleadoXOrdenTrabajo;
CREATE TABLE ActvempleadoXOrdenTrabajo
(
  Id_actvempleado INT NOT NULL,
  Id_Orden INT NOT NULL,
  PRIMARY KEY (Id_actvempleado, ID_Orden),
  FOREIGN KEY (Id_actvempleado) REFERENCES Actividad_empleado(Id_actvempleado),
  FOREIGN KEY (Id_Orden) REFERENCES Orden_de_trabajo(Id_Orden)
);

DROP TABLE IF EXISTS Categoria_Almacen;
CREATE TABLE Categoria_Almacen 
(
    Codigo_categoria INT PRIMARY KEY,
    Nombre_categoria VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Estado_Almacen;
CREATE TABLE Estado_Almacen 
(
    Codigo_estado INT PRIMARY KEY,
    Nombre_estado VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Almacen;
CREATE TABLE Almacen
(
    Cod_almacen INT PRIMARY KEY,
    Codigo_empleado INT,
    Codigo_categoria INT,
    Codigo_estado INT,
    Direccion VARCHAR(255),
    Capacidad INT,
    CONSTRAINT fk_empleado_almacen FOREIGN KEY (Codigo_empleado) REFERENCES Empleado (Codigo_empleado),
    CONSTRAINT fk_categoria_almacen FOREIGN KEY (Codigo_categoria) REFERENCES Categoria_Almacen (Codigo_categoria),
    CONSTRAINT fk_estado_almacen FOREIGN KEY (Codigo_estado) REFERENCES Estado_Almacen (Codigo_estado)
);

DROP TABLE IF EXISTS Tipo_Equipo_Soporte;
CREATE TABLE Tipo_Equipo_Soporte
(
  Codigo_tipo INT PRIMARY KEY,
  Nombre_tipo VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Disponibilidad_Equipo_Soporte;
CREATE TABLE Disponibilidad_Equipo_Soporte 
(
    Codigo_disponibilidad INT PRIMARY KEY,
    Nombre_disponibilidad VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Estado_Equipo_Soporte;
CREATE TABLE Estado_Equipo_Soporte
(
    Codigo_estado INT PRIMARY KEY,
    Nombre_estado VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Equipo_de_Soporte;
CREATE TABLE Equipo_de_Soporte 
(
    Id_equipo_soporte INT PRIMARY KEY,
    Nombre_equipo_soporte VARCHAR(100) NOT NULL,
    Codigo_tipo INT,
    Fecha_adquisicion DATE,
    Descripcion VARCHAR(255),
    Horas_uso INT,
    Cod_almacen INT,
    Codigo_disponibilidad INT,
    Codigo_estado INT,
    Id_actvempleado INT NULL,
    Id_orden INT NULL,
    CONSTRAINT fk_tipo FOREIGN KEY (Codigo_tipo) REFERENCES Tipo_Equipo_Soporte (Codigo_tipo),
    CONSTRAINT fk_almacen FOREIGN KEY (Cod_almacen) REFERENCES Almacen (Cod_almacen),
    CONSTRAINT fk_disponibilidad FOREIGN KEY (Codigo_disponibilidad) REFERENCES Disponibilidad_Equipo_Soporte (Codigo_disponibilidad),
    CONSTRAINT fk_estado FOREIGN KEY (Codigo_estado) REFERENCES Estado_Equipo_Soporte (Codigo_estado),
    CONSTRAINT fk_orden FOREIGN KEY (Id_orden) REFERENCES ActvempleadoXOrdenTrabajo (Id_orden),
    CONSTRAINT fk_actvempleado FOREIGN KEY (Id_actvempleado) REFERENCES ActvempleadoXOrdenTrabajo (Id_actvempleado)
);

DROP TABLE IF EXISTS Identificacion_del_riesgo;
CREATE TABLE Identificacion_del_riesgo
(
  Descripcion_peligro CHAR(255) NOT NULL,
  Evento_no_deseado CHAR(255) NOT NULL,
  Id_riesgo INT NOT NULL,
  PRIMARY KEY (Id_riesgo)
);

DROP TABLE IF EXISTS Tipos_Contacto;
CREATE TABLE Tipos_Contacto
(
  Id_tipo_contacto CHAR(4) NOT NULL,
  Nombre_tipo_contacto CHAR(255) NOT NULL,
  PRIMARY KEY (Id_tipo_contacto)
);

DROP TABLE IF EXISTS Tipos_Peligro;
CREATE TABLE Tipos_Peligro
(
  Id_tipo_peligro CHAR(7) NOT NULL,
  Nombre_tipo_peligro CHAR(255) NOT NULL,
  PRIMARY KEY (Id_tipo_peligro)
);

DROP TABLE IF EXISTS Tipo_Riesgo;
CREATE TABLE Tipo_Riesgo
(
  Id_tipo_riesgo CHAR(2) NOT NULL,
  Nombre_tipo_riesgo CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_riesgo)
);

DROP TABLE IF EXISTS Tipo_Medida_Control;
CREATE TABLE Tipo_Medida_Control
(
  Id_tipo_med_control CHAR(3) NOT NULL,
  Nombre_tipo_med_control CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_med_control)
);

DROP TABLE IF EXISTS Tipo_Estrategia_Control;
CREATE TABLE Tipo_Estrategia_Control
(
  Id_tipo_estgia_control CHAR(1) NOT NULL,
  Nombre_tipo_estgia_control CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_estgia_control)
);

DROP TABLE IF EXISTS Tipo_Probabilidad;
CREATE TABLE Tipo_Probabilidad
(
  Id_tipo_probabilidad CHAR(5) NOT NULL,
  Nombre_tipo_probabilidad CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_probabilidad)
);

DROP TABLE IF EXISTS Tipo_Severidad;
CREATE TABLE Tipo_Severidad
(
  Id_tipo_severidad CHAR(2) NOT NULL,
  Nombre_tipo_severidad CHAR(50) NOT NULL,
  PRIMARY KEY (Id_tipo_severidad)
);

DROP TABLE IF EXISTS Tipo_Q_control;
CREATE TABLE Tipo_Q_control
(
  Id_tipo_Qcontrol CHAR(1) NOT NULL,
  Nombre_tipo_Qcontrol CHAR(255) NOT NULL,
  PRIMARY KEY (Id_tipo_Qcontrol)
);

DROP TABLE IF EXISTS Equipo_evaluador;
CREATE TABLE Equipo_evaluador
(
  Id_equipo_evaluador INT NOT NULL,
  Cant_empleados INT NOT NULL,
  Codigo_empleado INT NOT NULL,
  PRIMARY KEY (Id_equipo_evaluador),
  FOREIGN KEY (Codigo_empleado) REFERENCES Empleado(Codigo_empleado)
);

DROP TABLE IF EXISTS proceso;
CREATE TABLE proceso
(
  Descripcion_proceso CHAR(255) NOT NULL,
  Id_proceso INT NOT NULL,
  Id_equipo_evaluador INT NOT NULL,
  PRIMARY KEY (Id_proceso),
  FOREIGN KEY (Id_equipo_evaluador) REFERENCES Equipo_evaluador(Id_equipo_evaluador)
);

DROP TABLE IF EXISTS Analisis_riesgo;
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

DROP TABLE IF EXISTS Valoracion_del_riesgo_inicial;
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

DROP TABLE IF EXISTS Actividades;
CREATE TABLE Actividades
(
  Id_actividad INT NOT NULL,
  Descripcion_actividad CHAR(255) NOT NULL,
  Id_proceso INT NOT NULL,
  PRIMARY KEY (Id_actividad),
  FOREIGN KEY (Id_proceso) REFERENCES proceso(Id_proceso)
);

DROP TABLE IF EXISTS Tareas;
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

DROP TABLE IF EXISTS Control;
CREATE TABLE Control
(
  Id_control INT NOT NULL,
  Descripcion_control CHAR(255) NOT NULL,
  Id_tipo_estgia_control CHAR(1) NOT NULL,
  Id_tipo_med_control CHAR(3) NOT NULL,
  Id_tipo_Qcontrol CHAR(1) NOT NULL,
  PRIMARY KEY (Id_control),
  FOREIGN KEY (Id_tipo_estgia_control) REFERENCES Tipo_Estrategia_Control(Id_tipo_estgia_control),
  FOREIGN KEY (Id_tipo_med_control) REFERENCES Tipo_Medida_Control(Id_tipo_med_control),
  FOREIGN KEY (Id_tipo_Qcontrol) REFERENCES Tipo_Q_control(Id_tipo_Qcontrol)
);

DROP TABLE IF EXISTS TareasXIdentRiesgo;
CREATE TABLE TareasXIdentRiesgo
(
  Id_tarea INT NOT NULL,
  Id_riesgo INT NOT NULL,
  PRIMARY KEY (Id_tarea, Id_riesgo),
  FOREIGN KEY (Id_tarea) REFERENCES Tareas(Id_tarea),
  FOREIGN KEY (Id_riesgo) REFERENCES Identificacion_del_riesgo(Id_riesgo)
);

DROP TABLE IF EXISTS Valorizacion_del_riesgo;
CREATE TABLE Valorizacion_del_riesgo
(
  Id_valoracion_residual INT NOT NULL,
  Cantidad_controles INT NOT NULL,
  Id_valoracion_inicial INT NOT NULL,
  Id_control INT NOT NULL,
  Id_tipo_riesgo CHAR(2) NOT NULL,
  PRIMARY KEY (Id_valoracion_residual),
  FOREIGN KEY (Id_valoracion_inicial) REFERENCES Valoracion_del_riesgo_inicial(id_valoracion_inicial),
  FOREIGN KEY (Id_control) REFERENCES Control(Id_control),
  FOREIGN KEY (Id_tipo_riesgo) REFERENCES Tipo_Riesgo(Id_tipo_riesgo)
);

DROP TABLE IF EXISTS Plan_de_accion_de_mejora;
CREATE TABLE Plan_de_accion_de_mejora
(
  Accion_que CHAR(255) NOT NULL,
  Fecha_cuando DATE NOT NULL,
  Cantidad_empleados INT NOT NULL,
  Id_plan_mejora INT NOT NULL,
  Id_valoracion_residual INT NOT NULL,
  PRIMARY KEY (Id_plan_mejora),
  FOREIGN KEY (Id_valoracion_residual) REFERENCES Valorizacion_del_riesgo(Id_valoracion_residual)
);

DROP TABLE IF EXISTS PlanaccionxEmpleado;
CREATE TABLE PlanaccionxEmpleado
(
  id_plan_mejora INT NOT NULL,
  Codigo_empleado INT NOT NULL,
  PRIMARY KEY (id_plan_mejora, Codigo_empleado),
  FOREIGN KEY (id_plan_mejora) REFERENCES Plan_de_accion_de_mejora(Id_plan_mejora),
  FOREIGN KEY (Codigo_empleado) REFERENCES Empleado(Codigo_empleado)
);

DROP TABLE IF EXISTS Registros_por_Riesgos;
CREATE TABLE Registros_por_Riesgos
(
  Id_registro_riesgo INT NOT NULL,
  id_valoracion_inicial INT NOT NULL,
  Id_plan_mejora INT NOT NULL,
  Id_analisis INT NOT NULL,
  Id_valoracion_residual INT NOT NULL,
  PRIMARY KEY (Id_registro_riesgo),
  FOREIGN KEY (id_valoracion_inicial) REFERENCES Valoracion_del_riesgo_inicial(id_valoracion_inicial),
  FOREIGN KEY (Id_plan_mejora) REFERENCES Plan_de_accion_de_mejora(Id_plan_mejora),
  FOREIGN KEY (Id_analisis) REFERENCES Analisis_riesgo(Id_analisis),
  FOREIGN KEY (Id_valoracion_residual) REFERENCES Valorizacion_del_riesgo(Id_valoracion_residual)
);

DROP TABLE IF EXISTS Registro_IPERC;
CREATE TABLE Registro_IPERC
(
  Fecha_registro DATE NOT NULL,
  Id_reg_iperc INT NOT NULL,
  Cant_riesgos_analizados INT NOT NULL,
  Id_proceso INT NOT NULL,
  Id_registro_riesgo INT NOT NULL,
  PRIMARY KEY (Id_reg_iperc),
  FOREIGN KEY (Id_proceso) REFERENCES proceso(Id_proceso),
  FOREIGN KEY (Id_registro_riesgo) REFERENCES Registros_por_Riesgos(Id_registro_riesgo)
);

DROP TABLE IF EXISTS Tipo_maquina;
CREATE TABLE Tipo_maquina
(
    id_tipo_maquina CHAR(1) NOT NULL,
    nombre_tipo VARCHAR(30),
    PRIMARY KEY (id_tipo_maquina)
);

DROP TABLE IF EXISTS Estado_maquina;
CREATE TABLE Estado_maquina
(
    id_estado CHAR(1) NOT NULL,
    nombre_estado VARCHAR(30),
    PRIMARY KEY (id_estado)
);

DROP TABLE IF EXISTS Maquina;
CREATE TABLE Maquina
(
    Num_serie VARCHAR NOT NULL,
    Fecha_ultima_inspeccion DATE NOT NULL,
    Fecha_adquisicion DATE NOT NULL,
    Modelo VARCHAR NOT NULL,
    Marca VARCHAR NOT NULL,
    id_tipo_maquina CHAR(1) NOT NULL, 
    id_estado CHAR(1) NOT NULL,
    PRIMARY KEY (Num_serie),
    CONSTRAINT fk_tipo_maquina FOREIGN KEY (id_tipo_maquina) REFERENCES Tipo_maquina(id_tipo_maquina),
    CONSTRAINT fk_estado_maquina FOREIGN KEY (id_estado) REFERENCES Estado_maquina(id_estado)
);

DROP TABLE IF EXISTS Tipo_mantenimiento;
CREATE TABLE Tipo_mantenimiento
(
    id_tipo_mant CHAR(2) NOT NULL,
    nombre_tipo_mant VARCHAR(30),
    PRIMARY KEY (id_tipo_mant)
);

DROP TABLE IF EXISTS Mantenimiento;
CREATE TABLE Mantenimiento
(
    Cod_Act_mantto INT NOT NULL,
    Descripcion VARCHAR(300),
    Tarea VARCHAR(40),
    Peligros VARCHAR(30),
    Fecha_inicio programado DATE,
    Fecha_fin_programado DATE,
    Id_Orden INT NOT NULL,
    Codigo_plan INT NOT NULL,
    Num_serie VARCHAR NOT NULL,
    id_tipo_mant CHAR(2) NOT NULL,
    PRIMARY KEY (Cod_Act_mantto),
    CONSTRAINT fk_orden_mant FOREIGN KEY (Id_Orden) REFERENCES Orden_de_trabajo (Id_Orden),
    CONSTRAINT fk_plan_mant FOREIGN KEY (Codigo_plan) REFERENCES Plan_de_mantenimiento (Codigo_plan),
    CONSTRAINT fk_maquina_mant FOREIGN KEY (Num_serie) REFERENCES Maquina (Num_serie),
    CONSTRAINT fk_orden_mant FOREIGN KEY (id_tipo_mant) REFERENCES Tipo_mantenimiento (id_tipo_mant),
);

DROP TABLE IF EXISTS Auditoria;
CREATE TABLE Auditoria
(
    Codigo_Auditoria INT NOT NULL,
    Cod_Act_mantto INT NOT NULL,
    Fecha_auditoria DATE,
    ubi_auditoria VARCHAR(200),
    resp_auditoria VARCHAR(64),
    Descripcion VARCHAR(300),
    objetivo VARCHAR(200),
    PRIMARY KEY (Codigo_Auditoria),
    CONSTRAINT fk_mant_auditoria FOREIGN KEY (Cod_Act_mantto) REFERENCES Mantenimiento (Cod_Act_mantto)
);

DROP TABLE IF EXISTS Capacitaciones;
CREATE TABLE Capacitaciones
(
    Codigo_Capacitacion INT NOT NULL,
    Fecha_Capacitacion DATE,
    Descripcion VARCHAR(1000),
    Duracion FLOAT,
    Instructor VARCHAR(200),
    Evaluador VARCHAR(200),
    PRIMARY KEY (Codigo_Capacitacion)
);

DROP TABLE IF EXISTS EmpleadoxCapacitacion;
CREATE TABLE EmpleadoxCapacitacion
(
    Codigo_empleado INT NOT NULL,
    Codigo_Capacitacion INT NOT NULL,
    CONSTRAINT fk_empleado_capacitacion_1 FOREIGN KEY (Codigo_empleado) REFERENCES Empleado (Codigo_empleado),
    CONSTRAINT fk_empleado_capacitacion_2 FOREIGN KEY (Codigo_Capacitacion) REFERENCES Capacitaciones (Codigo_Capacitacion)
);

    
