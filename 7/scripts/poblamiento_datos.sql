-- Poblamiento de la tabla Cargo_empleado
INSERT INTO Cargo_empleado (ID_Cargo, Nombre_cargo) VALUES
(1, 'Jefe'),
(2, 'Técnico'),
(3, 'Supervisor');

-- Poblamiento de la tabla Criticidad
INSERT INTO Criticidad (Id_criticidad, Nivel) VALUES
(1, 'Bajo'),
(2, 'Medio'),
(3, 'Alto');

-- Poblamiento de la tabla Empleado
INSERT INTO Empleado (Codigo_empleado, Nombre, DNI, Direccion, Estado_civil, Sexo, Fecha_Nacimiento, Fecha_ingreso, Numero_contacto, Email_contacto, Contraseña, ID_Cargo) VALUES
(1, 'Juan Pérez', '12345678A', 'Av. Principal 123', 'Soltero', 'Masculino', '1985-03-12', '2010-01-15', '987654321', 'juan.perez@empresa.com', 'password123', 1),
(2, 'Ana Gómez', '23456789B', 'Calle Secundaria 45', 'Casado', 'Femenino', '1990-06-25', '2012-06-20', '987123456', 'ana.gomez@empresa.com', 'password456', 2),
(3, 'Carlos Sánchez', '34567890C', 'Jr. Terciario 678', 'Divorciado', 'Masculino', '1978-11-05', '2005-09-10', '987987654', 'carlos.sanchez@empresa.com', 'password789', 2),
(4, 'Lucía Fernández', '45678901D', 'Av. Los Álamos 89', 'Soltero', 'Femenino', '1982-02-19', '2008-04-01', '987321654', 'lucia.fernandez@empresa.com', 'password101', 3),
(5, 'Pedro Ramírez', '56789012E', 'Calle las Rosas 56', 'Casado', 'Masculino', '1995-09-30', '2015-03-10', '987654123', 'pedro.ramirez@empresa.com', 'password102', 1),
(6, 'María Torres', '67890123F', 'Jr. Los Pinos 33', 'Viudo', 'Femenino', '1988-07-17', '2011-11-20', '987321987', 'maria.torres@empresa.com', 'password103', 2),
(7, 'Diego Morales', '78901234G', 'Av. Los Cedros 12', 'Soltero', 'Masculino', '1992-05-25', '2014-05-05', '987987123', 'diego.morales@empresa.com', 'password104', 3),
(8, 'Laura Espinoza', '89012345H', 'Calle el Sol 77', 'Casado', 'Femenino', '1981-12-11', '2009-09-30', '987456321', 'laura.espinoza@empresa.com', 'password105', 2),
(9, 'Raúl Mendoza', '90123456I', 'Jr. Las Palmeras 9', 'Soltero', 'Masculino', '1996-01-15', '2016-07-15', '987123789', 'raul.mendoza@empresa.com', 'password106', 1),
(10, 'Elena Castillo', '12345679J', 'Av. Los Laureles 44', 'Casado', 'Femenino', '1983-04-10', '2010-10-25', '987654987', 'elena.castillo@empresa.com', 'password107', 3),
(11, 'Miguel Ángel', '23456780K', 'Calle Nueva 101', 'Soltero', 'Masculino', '1987-08-22', '2013-03-18', '987654322', 'miguel.angel@empresa.com', 'password108', 2),
(12, 'Sofía López', '34567891L', 'Av. Central 202', 'Casada', 'Femenino', '1991-11-30', '2014-07-12', '987654323', 'sofia.lopez@empresa.com', 'password109', 3),
(13, 'Andrés Ruiz', '45678902M', 'Jr. El Mirador 303', 'Divorciado', 'Masculino', '1984-05-14', '2009-02-20', '987654324', 'andres.ruiz@empresa.com', 'password110', 1),
(14, 'Valentina Díaz', '56789013N', 'Calle Las Flores 404', 'Soltera', 'Femenino', '1993-09-09', '2015-08-25', '987654325', 'valentina.diaz@empresa.com', 'password111', 2),
(15, 'Jorge Herrera', '67890124O', 'Av. Las Palmas 505', 'Casado', 'Masculino', '1980-12-01', '2007-06-30', '987654326', 'jorge.herrera@empresa.com', 'password112', 3),
(16, 'Isabella Morales', '78901235P', 'Calle San Juan 606', 'Soltera', 'Femenino', '1994-04-18', '2016-09-14', '987654327', 'isabella.morales@empresa.com', 'password113', 1),
(17, 'Luis Castillo', '89012346Q', 'Jr. Las Lomas 707', 'Viudo', 'Masculino', '1986-07-07', '2012-12-05', '987654328', 'luis.castillo@empresa.com', 'password114', 2),
(18, 'Camila Rojas', '90123457R', 'Av. El Sol 808', 'Casada', 'Femenino', '1992-10-20', '2013-11-22', '987654329', 'camila.rojas@empresa.com', 'password115', 3),
(19, 'Fernando Vega', '12345670S', 'Calle Luna 909', 'Soltero', 'Masculino', '1989-02-28', '2014-04-18', '987654330', 'fernando.vega@empresa.com', 'password116', 1),
(20, 'Gabriela Torres', '23456781T', 'Jr. Los Sauces 1010', 'Casada', 'Femenino', '1995-06-05', '2017-01-10', '987654331', 'gabriela.torres@empresa.com', 'password117', 2);

-- Poblamiento de la tabla Plan_de_mantenimiento
INSERT INTO Plan_de_mantenimiento (Codigo_plan, Observaciones, Empleado_asigna, Id_criticidad) VALUES
(1, 'Mantenimiento preventivo mensual de maquinaria principal', 1, 3),
(2, 'Revisión trimestral de sistemas eléctricos', 2, 2),
(3, 'Inspección anual de seguridad en planta', 3, 3),
(4, 'Mantenimiento correctivo de línea de producción', 4, 1),
(5, 'Verificación bimestral de sistemas de ventilación', 5, 2),
(6, 'Actualización semestral de software de control', 6, 3),
(7, 'Reemplazo trimestral de piezas desgastadas', 7, 1),
(8, 'Optimización de procesos de ensamblaje', 8, 2),
(9, 'Evaluación anual de eficiencia energética', 9, 3),
(10, 'Ajustes mensuales de configuración de maquinaria', 10, 1),
(11, 'Inspección preventiva de sistemas hidráulicos', 11, 2),
(12, 'Mantenimiento preventivo de equipos de refrigeración', 12, 3),
(13, 'Revisión trimestral de sistemas de alarma', 13, 1),
(14, 'Mantenimiento correctivo de equipos de seguridad', 14, 2),
(15, 'Verificación semestral de sistemas de control de calidad', 15, 3),
(16, 'Actualización anual de protocolos de seguridad', 16, 1),
(17, 'Reemplazo de componentes electrónicos defectuosos', 17, 2),
(18, 'Optimización de la línea de empaquetado', 18, 3),
(19, 'Evaluación bimestral de sistemas de iluminación', 19, 1),
(20, 'Ajustes preventivos en maquinaria de ensamblaje', 20, 2);

-- Poblamiento de la tabla Actividad_empleado
INSERT INTO Actividad_empleado (Id_actvempleado, Descripcion_actv, Codigo_empleado) VALUES
(1, 'Responsable: Supervisar el mantenimiento preventivo de maquinaria principal', 1),
(2, 'Verificar el estado de los sistemas eléctricos', 2),
(3, 'Responsable: Realizar inspección anual de seguridad en la planta', 3),
(4, 'Diagnosticar fallos en la línea de producción', 4),
(5, 'Responsable: Revisar sistemas de ventilación bimestralmente', 5),
(6, 'Actualizar el software de control semestralmente', 6),
(7, 'Responsable: Reemplazar piezas desgastadas trimestralmente', 7),
(8, 'Optimizar procesos de ensamblaje para mejorar la eficiencia', 8),
(9, 'Responsable: Evaluar la eficiencia energética anual', 9),
(10, 'Ajustar configuraciones de maquinaria mensualmente', 10),
(11, 'Responsable: Inspeccionar sistemas hidráulicos preventivamente', 11),
(12, 'Mantener equipos de refrigeración en buen estado', 12),
(13, 'Responsable: Revisar sistemas de alarma trimestralmente', 13),
(14, 'Realizar mantenimiento correctivo en equipos de seguridad', 14),
(15, 'Responsable: Verificar sistemas de control de calidad semestralmente', 15),
(16, 'Actualizar protocolos de seguridad anualmente', 16),
(17, 'Responsable: Reemplazar componentes electrónicos defectuosos', 17),
(18, 'Optimizar línea de empaquetado para mayor eficiencia', 18),
(19, 'Responsable: Evaluar sistemas de iluminación bimestralmente', 19),
(20, 'Ajustar maquinaria de ensamblaje preventivamente', 20);

-- Poblamiento de la tabla Orden_de_trabajo
INSERT INTO Orden_de_trabajo (ID_Orden, Fecha_Orden, Descripcion, Empleado_asigna, Id_criticidad) VALUES
(1, '2024-01-10', 'Reemplazo de motor en línea de producción', 1, 3),
(2, '2024-02-15', 'Revisión de sistema de control eléctrico', 2, 2),
(3, '2024-03-20', 'Inspección de seguridad en planta de ensamblaje', 3, 3),
(4, '2024-04-25', 'Mantenimiento preventivo en área de pintura', 4, 1),
(5, '2024-05-30', 'Reemplazo de filtros en sistema de ventilación', 5, 2),
(6, '2024-06-05', 'Actualización de software de monitoreo', 6, 3),
(7, '2024-07-10', 'Inspección de maquinaria pesada', 7, 1),
(8, '2024-08-15', 'Reparación de sistema hidráulico', 8, 2),
(9, '2024-09-20', 'Evaluación de sistemas de refrigeración', 9, 3),
(10, '2024-10-25', 'Verificación de sistemas de alarma', 10, 1),
(11, '2024-11-05', 'Mantenimiento de sistemas de iluminación', 11, 2),
(12, '2024-12-10', 'Revisión de protocolos de seguridad', 12, 3),
(13, '2025-01-15', 'Actualización de maquinaria de ensamblaje', 13, 1),
(14, '2025-02-20', 'Inspección de sistemas de control de calidad', 14, 2),
(15, '2025-03-25', 'Reemplazo de componentes electrónicos', 15, 3),
(16, '2025-04-30', 'Optimización de línea de empaquetado', 16, 1),
(17, '2025-05-05', 'Evaluación bimestral de sistemas de ventilación', 17, 2),
(18, '2025-06-10', 'Mantenimiento preventivo de equipos de refrigeración', 18, 3),
(19, '2025-07-15', 'Revisión de sistemas de alarma trimestral', 19, 1),
(20, '2025-08-20', 'Actualización de software de control semestral', 20, 2);

-- Poblamiento de la tabla ActvempleadoXOrdenTrabajo
INSERT INTO ActvempleadoXOrdenTrabajo (Id_actvempleado, ID_Orden) VALUES
-- Orden 1
(1, 1),
(10, 1),
(20, 1),

-- Orden 2
(2, 2),
(11, 2),

-- Orden 3
(3, 3),
(12, 3),
(19, 3),

-- Orden 4
(4, 4),
(13, 4),

-- Orden 5
(5, 5),
(14, 5),
(18, 5),

-- Orden 6
(6, 6),
(15, 6),

-- Orden 7
(7, 7),
(16, 7),
(17, 7),

-- Orden 8
(8, 8),
(18, 8),

-- Orden 9
(9, 9),
(19, 9),
(20, 9),

-- Orden 10
(10, 10),
(20, 10),

-- Orden 11
(11, 11),
(1, 11),
(5, 11),

-- Orden 12
(12, 12),
(3, 12),

-- Orden 13
(13, 13),
(4, 13),
(7, 13),

-- Orden 14
(14, 14),
(2, 14),

-- Orden 15
(15, 15),
(6, 15),
(9, 15),

-- Orden 16
(16, 16),
(8, 16),

-- Orden 17
(17, 17),
(5, 17),
(10, 17),

-- Orden 18
(18, 18),
(12, 18),
(14, 18),

-- Orden 19
(19, 19),
(1, 19),

-- Orden 20
(20, 20),
(10, 20),
(15, 20);

