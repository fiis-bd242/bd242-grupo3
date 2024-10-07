CREATE TABLE Cargo_empleado
(
  ID_Cargo INT NOT NULL,
  Nombre_cargo INT NOT NULL,
  PRIMARY KEY (ID_Cargo)
);

CREATE TABLE Criticidad
(
  Id_criticidad INT NOT NULL,
  Nivel VARCHAR(20) NOT NULL,
  PRIMARY KEY (Id_criticidad)
);

CREATE TABLE Empleado
(
  Codigo_empleado INT NOT NULL,
  Nombre INT NOT NULL,
  DNI INT NOT NULL,
  Direccion INT NOT NULL,
  Estado_civil INT NOT NULL,
  Sexo INT NOT NULL,
  Fecha_Nacimiento INT NOT NULL,
  Fecha_ingreso INT NOT NULL,
  Numero_contacto INT NOT NULL,
  Email_contacto INT NOT NULL,
  Contraseña INT NOT NULL,
  ID_Cargo INT NOT NULL,
  PRIMARY KEY (Codigo_empleado),
  FOREIGN KEY (ID_Cargo) REFERENCES Cargo_empleado(ID_Cargo)
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