CREATE TABLE Cargo_empleado 
( 
    Id_cargo INT PRIMARY KEY, 
    Nombre_cargo VARCHAR(50) NOT NULL 
);

CREATE TABLE Criticidad
(
  Id_criticidad INT NOT NULL,
  Nivel VARCHAR(20) NOT NULL,
  PRIMARY KEY (Id_criticidad)
);

CREATE TABLE Empleado 
( 
    Codigo_empleado INT PRIMARY KEY, 
    Nombre VARCHAR(100) NOT NULL, 
    DNI VARCHAR(9) NOT NULL, 
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

CREATE TABLE Plan_de_mantenimiento
(
  Codigo_plan INT NOT NULL,
  Observaciones VARCHAR(5000) NOT NULL,
  Empleado_asigna INT NOT NULL,
  Id_criticidad INT NOT NULL,
  PRIMARY KEY (Codigo_plan),
  FOREIGN KEY (Empleado_asigna) REFERENCES Empleado(Codigo_empleado),
  FOREIGN KEY (Id_criticidad) REFERENCES Criticidad(Id_criticidad)
);

CREATE TABLE Actividad_empleado
(
  Id_actvempleado INT NOT NULL,
  Descripcion_actv VARCHAR(10000) NOT NULL,
  Codigo_empleado INT NOT NULL,
  PRIMARY KEY (Id_actvempleado),
  FOREIGN KEY (Codigo_empleado) REFERENCES Empleado(Codigo_empleado)
);

CREATE TABLE Orden_de_trabajo
(
  Id_Orden INT NOT NULL,
  Fecha_Orden DATE NOT NULL,
  Descripcion VARCHAR(10000) NOT NULL,
  Empleado_asigna INT NOT NULL,
  Id_criticidad INT NOT NULL,
  PRIMARY KEY (Id_Orden),
  FOREIGN KEY (Empleado_asigna) REFERENCES Empleado(Codigo_empleado),
  FOREIGN KEY (Id_criticidad) REFERENCES Criticidad(Id_criticidad)
);

CREATE TABLE ActvempleadoXOrdenTrabajo
(
  Id_actvempleado INT NOT NULL,
  ID_Orden INT NOT NULL,
  PRIMARY KEY (Id_actvempleado, ID_Orden),
  FOREIGN KEY (Id_actvempleado) REFERENCES Actividad_empleado(Id_actvempleado),
  FOREIGN KEY (ID_Orden) REFERENCES Orden_de_trabajo(ID_Orden)
);

CREATE TABLE Categoria_Almacen 
(
    Codigo_categoria INT PRIMARY KEY,
    Nombre_categoria VARCHAR(50) NOT NULL
);

CREATE TABLE Estado_Almacen 
(
    Codigo_estado INT PRIMARY KEY,
    Nombre_estado VARCHAR(50) NOT NULL
);

CREATE TABLE Almacen
(
    Cod_almacen INT PRIMARY KEY,
    Codigo_empleado INT,
    Codigo_categoria INT,
    Codigo_estado INT,
    Direccion VARCHAR(255),
    Capacidad INTEGER,
    CONSTRAINT fk_empleado_almacen FOREIGN KEY (Codigo_empleado) REFERENCES Empleado (Codigo_empleado),
    CONSTRAINT fk_categoria_almacen FOREIGN KEY (Codigo_categoria) REFERENCES Categoria_Almacen (Codigo_categoria),
    CONSTRAINT fk_estado_almacen FOREIGN KEY (Codigo_estado) REFERENCES Estado_Almacen (Codigo_estado)
);

CREATE TABLE Tipo_Equipo_Soporte
(
  Codigo_tipo INT PRIMARY KEY,
  Nombre_tipo VARCHAR(50) NOT NULL
);

CREATE TABLE Disponibilidad_Equipo_Soporte 
(
    Codigo_disponibilidad INT PRIMARY KEY,
    Nombre_disponibilidad VARCHAR(50) NOT NULL
);

CREATE TABLE Estado_Equipo_Soporte
(
    Codigo_estado INT PRIMARY KEY,
    Nombre_estado VARCHAR(50) NOT NULL
);

CREATE TABLE Equipo_de_Soporte 
(
    Id_equipo_soporte INT PRIMARY KEY,
    Nombre_equipo_soporte VARCHAR(100) NOT NULL,
    Codigo_tipo INT,
    Fecha_adquisicion DATE,
    Descripcion VARCHAR(255),
    Horas_uso INTEGER,
    Cod_almacen INT,
    Codigo_disponibilidad INT,
    Codigo_estado INT,
    Id_orden INT,
    CONSTRAINT fk_tipo FOREIGN KEY (Codigo_tipo) REFERENCES Tipo_Equipo_Soporte (Codigo_tipo),
    CONSTRAINT fk_almacen FOREIGN KEY (Cod_almacen) REFERENCES Almacen (Cod_almacen),
    CONSTRAINT fk_disponibilidad FOREIGN KEY (Codigo_disponibilidad) REFERENCES Disponibilidad_Equipo_Soporte (Codigo_disponibilidad),
    CONSTRAINT fk_estado FOREIGN KEY (Codigo_estado) REFERENCES Estado_Equipo_Soporte (Codigo_estado),
    CONSTRAINT fk_orden FOREIGN KEY (Id_orden) REFERENCES Orden_de_trabajo (Id_cargo)
);

CREATE TABLE EquipoSoporteXMantenimiento 
(
    Id_eqsoportexmantto INT PRIMARY KEY,
    Id_equipo_soporte INT,
    Cod_act_mantto INT,
    CONSTRAINT fk_equipo_soporte FOREIGN KEY (Id_equipo_soporte) REFERENCES Equipo_de_Soporte (Id_equipo_soporte)
);

CREATE TABLE Tipos_Contacto
(
  Id_tipo_contacto INT NOT NULL,
  Nombre_tipo_contacto CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_contacto)
);

CREATE TABLE Tipos_Peligro
(
  Id_tipo_peligro INT NOT NULL,
  Nombre_tipo_peligro CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_peligro)
);

CREATE TABLE Tipo_Riesgo
(
  Id_tipo_riesgo INT NOT NULL,
  Nombre_tipo_riesgo CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_riesgo)
);

CREATE TABLE Tipo_Medida_Control
(
  Id_tipo_med_control INT NOT NULL,
  Nombre_tipo_med_control CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_med_control)
);

CREATE TABLE Tipo_Estrategia_Control
(
  Id_tipo_estgia_control INT NOT NULL,
  Nombre_tipo_estgia_control CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_estgia_control)
);

CREATE TABLE Tipo_Probabilidad
(
  Id_tipo_probabilidad INT NOT NULL,
  Nombre_tipo_probabilidad CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_probabilidad)
);

CREATE TABLE Tipo_Severidad
(
  Id_tipo_severidad INT NOT NULL,
  Nombre_tipo_severidad CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_severidad)
);

CREATE TABLE Tipo_Q_control
(
  Id_tipo_Qcontrol INT NOT NULL,
  Nombre_tipo_Qcontrol CHAR NOT NULL,
  PRIMARY KEY (Id_tipo_Qcontrol)
);

CREATE TABLE Equipo_evaluador
(
  Id_equipo_evaluador INT NOT NULL,
  Cant_empleados INT NOT NULL,
  Codigo_empleado INT NOT NULL,
  PRIMARY KEY (Id_equipo_evaluador),
  FOREIGN KEY (Codigo_empleado) REFERENCES Empleado(Codigo_empleado)
);

CREATE TABLE proceso
(
  Descripcion_proceso CHAR NOT NULL,
  Id_proceso INT NOT NULL,
  Id_equipo_evaluador INT NOT NULL,
  PRIMARY KEY (Id_proceso),
  FOREIGN KEY (Id_equipo_evaluador) REFERENCES Equipo_evaluador(Id_equipo_evaluador)
);

CREATE TABLE Identificacion_del_riesgo
(
  Descripcion_peligro CHAR NOT NULL,
  Evento_no_deseado CHAR NOT NULL,
  Id_riesgo INT NOT NULL,
  PRIMARY KEY (Id_riesgo)
);

CREATE TABLE Analisis_riesgo
(
  Afectado CHAR NOT NULL,
  Consecuencia CHAR NOT NULL,
  Id_analisis INT NOT NULL,
  Id_riesgo INT NOT NULL,
  Id_tipo_contacto INT NOT NULL,
  Id_tipo_peligro INT NOT NULL,
  PRIMARY KEY (Id_analisis),
  FOREIGN KEY (Id_riesgo) REFERENCES Identificacion_del_riesgo(Id_riesgo),
  FOREIGN KEY (Id_tipo_contacto) REFERENCES Tipos_Contacto(Id_tipo_contacto),
  FOREIGN KEY (Id_tipo_peligro) REFERENCES Tipos_Peligro(Id_tipo_peligro)
);

CREATE TABLE Valoracion_del_riesgo_inicial
(
  id_valoracion_inicial INT NOT NULL,
  Id_tipo_severidad INT NOT NULL,
  Id_tipo_probabilidad INT NOT NULL,
  Id_tipo_riesgo INT NOT NULL,
  PRIMARY KEY (id_valoracion_inicial),
  FOREIGN KEY (Id_tipo_severidad) REFERENCES Tipo_Severidad(Id_tipo_severidad),
  FOREIGN KEY (Id_tipo_probabilidad) REFERENCES Tipo_Probabilidad(Id_tipo_probabilidad),
  FOREIGN KEY (Id_tipo_riesgo) REFERENCES Tipo_Riesgo(Id_tipo_riesgo)
);

CREATE TABLE Actividades
(
  Id_actividad INT NOT NULL,
  Descripcion_actividad CHAR NOT NULL,
  Id_proceso INT NOT NULL,
  PRIMARY KEY (Id_actividad),
  FOREIGN KEY (Id_proceso) REFERENCES proceso(Id_proceso)
);

CREATE TABLE Tareas
(
  Descriçion_tarea CHAR NOT NULL,
  Puesto_trabajo CHAR NOT NULL,
  Cond_operacional CHAR NOT NULL,
  Id_tarea INT NOT NULL,
  Id_actividad INT NOT NULL,
  PRIMARY KEY (Id_tarea),
  FOREIGN KEY (Id_actividad) REFERENCES Actividades(Id_actividad)
);

CREATE TABLE Control
(
  Id_control INT NOT NULL,
  Descripcion_control CHAR NOT NULL,
  Id_tipo_estgia_control INT NOT NULL,
  Id_tipo_med_control INT NOT NULL,
  Id_tipo_Qcontrol INT NOT NULL,
  PRIMARY KEY (Id_control),
  FOREIGN KEY (Id_tipo_estgia_control) REFERENCES Tipo_Estrategia_Control(Id_tipo_estgia_control),
  FOREIGN KEY (Id_tipo_med_control) REFERENCES Tipo_Medida_Control(Id_tipo_med_control),
  FOREIGN KEY (Id_tipo_Qcontrol) REFERENCES Tipo_Q_control(Id_tipo_Qcontrol)
);

CREATE TABLE TareasXIdentRiesgo
(
  Id_tarea INT NOT NULL,
  Id_riesgo INT NOT NULL,
  PRIMARY KEY (Id_tarea, Id_riesgo),
  FOREIGN KEY (Id_tarea) REFERENCES Tareas(Id_tarea),
  FOREIGN KEY (Id_riesgo) REFERENCES Identificacion_del_riesgo(Id_riesgo)
);

CREATE TABLE Valorizacion_del_riesgo
(
  Id_valoracion_residual INT NOT NULL,
  Cantidad_controles INT NOT NULL,
  Id_valoracion_inicial INT NOT NULL,
  Id_control INT NOT NULL,
  Id_tipo_riesgo INT NOT NULL,
  PRIMARY KEY (Id_valoracion_residual),
  FOREIGN KEY (Id_valoracion_inicial) REFERENCES Valoracion_del_riesgo_inicial(id_valoracion_inicial),
  FOREIGN KEY (Id_control) REFERENCES Control(Id_control),
  FOREIGN KEY (Id_tipo_riesgo) REFERENCES Tipo_Riesgo(Id_tipo_riesgo)
);

CREATE TABLE Plan_de_accion_de_mejora
(
  Accion_que CHAR NOT NULL,
  Fecha_cuando DATE NOT NULL,
  Cantidad_empleados INT NOT NULL,
  Id_plan_mejora INT NOT NULL,
  Id_valoracion_residual INT NOT NULL,
  PRIMARY KEY (Id_plan_mejora),
  FOREIGN KEY (Id_valoracion_residual) REFERENCES Valorizacion_del_riesgo(Id_valoracion_residual)
);

CREATE TABLE PlanaccionxEmpleado
(
  id_plan_mejora INT NOT NULL,
  Codigo_empleado INT NOT NULL,
  PRIMARY KEY (id_plan_mejora, Codigo_empleado),
  FOREIGN KEY (id_plan_mejora) REFERENCES Plan_de_accion_de_mejora(Id_plan_mejora),
  FOREIGN KEY (Codigo_empleado) REFERENCES Empleado(Codigo_empleado)
);

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
