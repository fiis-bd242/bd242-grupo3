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
  ID_Orden INT NOT NULL,
  Fecha_Orden DATE NOT NULL,
  Descripcion VARCHAR(10000) NOT NULL,
  Empleado_asigna INT NOT NULL,
  Id_criticidad INT NOT NULL,
  PRIMARY KEY (ID_Orden),
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

CREATE TABLE Almacen
(
    Cod_almacen INT PRIMARY KEY,
    Codigo_empleado INT,
    Codigo_categoria INT,
    Codigo_estado INT,
    Direccion VARCHAR(255),
    Capacidad INTEGER,
    CONSTRAINT fk_categoria FOREIGN KEY (Codigo_categoria) REFERENCES Categoria_Almacen (Codigo_categoria),
    CONSTRAINT fk_estado_almacen FOREIGN KEY (Codigo_estado) REFERENCES Estado_Almacen (Codigo_estado)
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

CREATE TABLE Equipo_de_Soporte 
(
    Id_equipo_soporte INT PRIMARY KEY,
    Nombre_equipo_soporte VARCHAR(100) NOT NULL,
    Tipo_equipo_soporte VARCHAR(100),
    Fecha_adquisicion DATE,
    Descripcion VARCHAR(255),
    Horas_uso INTEGER,
    Cod_almacen INT,
    Codigo_disponibilidad INT,
    Codigo_estado INT,
    CONSTRAINT fk_almacen FOREIGN KEY (Cod_almacen) REFERENCES Almacen (Cod_almacen),
    CONSTRAINT fk_disponibilidad FOREIGN KEY (Codigo_disponibilidad) REFERENCES Disponibilidad_Equipo_Soporte (Codigo_disponibilidad),
    CONSTRAINT fk_estado FOREIGN KEY (Codigo_estado) REFERENCES Estado_Equipo_Soporte (Codigo_estado)
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

CREATE TABLE EquipoSoporteXMantenimiento 
(
    Id_eqsoportexmantto INT PRIMARY KEY,
    Id_equipo_soporte INT,
    Cod_act_mantto INT,
    CONSTRAINT fk_equipo_soporte FOREIGN KEY (Id_equipo_soporte) REFERENCES Equipo_de_Soporte (Id_equipo_soporte)
);