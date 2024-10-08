-- Poblamiento de la tabla Cargo_empleado
INSERT INTO Cargo_empleado (Id_Cargo, Nombre_cargo) VALUES
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
(1, 'Juan Pérez', '12345678', 'Av. Principal 123', 'Soltero', 'Masculino', '1985-03-12', '2010-01-15', '987654321', 'juan.perez@empresa.com', 'password123', 1),
(2, 'Ana Gómez', '23456789', 'Calle Secundaria 45', 'Casado', 'Femenino', '1990-06-25', '2012-06-20', '987123456', 'ana.gomez@empresa.com', 'password456', 2),
(3, 'Carlos Sánchez', '34567890', 'Jr. Terciario 678', 'Divorciado', 'Masculino', '1978-11-05', '2005-09-10', '987987654', 'carlos.sanchez@empresa.com', 'password789', 2),
(4, 'Lucía Fernández', '45678901', 'Av. Los Álamos 89', 'Soltero', 'Femenino', '1982-02-19', '2008-04-01', '987321654', 'lucia.fernandez@empresa.com', 'password101', 3),
(5, 'Pedro Ramírez', '56789012', 'Calle las Rosas 56', 'Casado', 'Masculino', '1995-09-30', '2015-03-10', '987654123', 'pedro.ramirez@empresa.com', 'password102', 1),
(6, 'María Torres', '67890123', 'Jr. Los Pinos 33', 'Viudo', 'Femenino', '1988-07-17', '2011-11-20', '987321987', 'maria.torres@empresa.com', 'password103', 2),
(7, 'Diego Morales', '78901234', 'Av. Los Cedros 12', 'Soltero', 'Masculino', '1992-05-25', '2014-05-05', '987987123', 'diego.morales@empresa.com', 'password104', 3),
(8, 'Laura Espinoza', '89012345', 'Calle el Sol 77', 'Casado', 'Femenino', '1981-12-11', '2009-09-30', '987456321', 'laura.espinoza@empresa.com', 'password105', 2),
(9, 'Raúl Mendoza', '90123456', 'Jr. Las Palmeras 9', 'Soltero', 'Masculino', '1996-01-15', '2016-07-15', '987123789', 'raul.mendoza@empresa.com', 'password106', 1),
(10, 'Elena Castillo', '12345679', 'Av. Los Laureles 44', 'Casado', 'Femenino', '1983-04-10', '2010-10-25', '987654987', 'elena.castillo@empresa.com', 'password107', 3),
(11, 'Miguel Ángel', '23456780', 'Calle Nueva 101', 'Soltero', 'Masculino', '1987-08-22', '2013-03-18', '987654322', 'miguel.angel@empresa.com', 'password108', 2),
(12, 'Sofía López', '34567891', 'Av. Central 202', 'Casada', 'Femenino', '1991-11-30', '2014-07-12', '987654323', 'sofia.lopez@empresa.com', 'password109', 3),
(13, 'Andrés Ruiz', '45678902', 'Jr. El Mirador 303', 'Divorciado', 'Masculino', '1984-05-14', '2009-02-20', '987654324', 'andres.ruiz@empresa.com', 'password110', 1),
(14, 'Valentina Díaz', '56789013', 'Calle Las Flores 404', 'Soltera', 'Femenino', '1993-09-09', '2015-08-25', '987654325', 'valentina.diaz@empresa.com', 'password111', 2),
(15, 'Jorge Herrera', '67890124', 'Av. Las Palmas 505', 'Casado', 'Masculino', '1980-12-01', '2007-06-30', '987654326', 'jorge.herrera@empresa.com', 'password112', 3),
(16, 'Isabella Morales', '78901235', 'Calle San Juan 606', 'Soltera', 'Femenino', '1994-04-18', '2016-09-14', '987654327', 'isabella.morales@empresa.com', 'password113', 1),
(17, 'Luis Castillo', '89012346', 'Jr. Las Lomas 707', 'Viudo', 'Masculino', '1986-07-07', '2012-12-05', '987654328', 'luis.castillo@empresa.com', 'password114', 2),
(18, 'Camila Rojas', '90123457', 'Av. El Sol 808', 'Casada', 'Femenino', '1992-10-20', '2013-11-22', '987654329', 'camila.rojas@empresa.com', 'password115', 3),
(19, 'Fernando Vega', '12345670', 'Calle Luna 909', 'Soltero', 'Masculino', '1989-02-28', '2014-04-18', '987654330', 'fernando.vega@empresa.com', 'password116', 1),
(20, 'Gabriela Torres', '23456781', 'Jr. Los Sauces 1010', 'Casada', 'Femenino', '1995-06-05', '2017-01-10', '987654331', 'gabriela.torres@empresa.com', 'password117', 2);

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

-- Poblamiento de la tabla Categoria_Almacen
INSERT INTO Categoria_Almacen (Codigo_categoria, Nombre_categoria) 
VALUES 
(1, 'Lubricantes'),
(2, 'Repuestos'),
(3, 'Equipos de soporte');

-- Poblamiento de la tabla Estado_Almacen
INSERT INTO Estado_Almacen (Codigo_estado, Nombre_estado) 
VALUES 
(1, 'Operativo'),
(2, 'Inactivo');

-- Poblamiento de la tabla Almacen
INSERT INTO Almacen (Cod_almacen, Codigo_empleado, Codigo_categoria, Codigo_estado, Direccion, Capacidad) 
VALUES 
(1, 5, 2, 1, 'Lugar 1', 1000),
(2, 2, 2, 1, 'Lugar 2', 500),
(3, 6, 3, 1, 'Lugar 3', 800),
(4, 10, 1, 2, 'Lugar 4', 300);

-- Poblamiento de la tabla Tipo_equipo_soporte
INSERT INTO tipo_equipo_soporte  (Codigo_tipo, nombre_tipo)
VALUES
(1, 'Equipo de vigilancia y monitorio'),
(2, 'Equipo de comunicación'),
(3, 'EPP'),
(4, 'Sistema de iluminación'),
(5, 'Equipo de apoyo mecánico');

-- Poblamiento de la tabla Disponibilidad_equipo_soporte
INSERT INTO Disponibilidad_Equipo_Soporte (Codigo_disponibilidad, Nombre_disponibilidad) 
VALUES 
(1, 'Disponible'),
(2, 'Ocupado');

-- Poblamiento de la tabla Estado_equipo_soporte 
INSERT INTO Estado_Equipo_Soporte (Codigo_estado, Nombre_estado) 
VALUES 
(1, 'Optimo'),
(2, 'Bueno'),
(3, 'Regular'),
(4, 'Deficiente');

-- Poblamiento de la tabla Equipo_de_Soporte
INSERT INTO Equipo_de_Soporte (Id_equipo_soporte, Nombre_equipo_soporte, Codigo_tipo, Fecha_adquisicion, Descripcion, Horas_uso, Cod_almacen, Codigo_disponibilidad, Codigo_estado, Id_actvempleado, Id_orden) 
VALUES 
(1, 'Generador electrico', 5, '2020-05-10', 'Generador electrico de emergencia', 1000, 3, 1, 1, 1, 1),
(2, 'Radio portatil', 2, '2018-07-15', 'Radios para comunicación en zonas remotas', 1200, 3, 1, 3, 10, 1),
(3, 'Torre de iluminación', 4, '2021-03-05', 'Torre movil de iluminación', 300, 3, 2, 4, 20, 1),
(4, 'Cámara de Seguridad', 1 , '2023-01-15', 'Cámara de alta resolución para monitoreo de áreas críticas', 500, 3, 1, 1, 2, 2),
(5, 'Detector de Gas', 3, '2023-02-10', 'Detector de gases tóxicos para asegurar la calidad del aire', 300, 3, 1, 1, 11 , 2),
(6, 'Botiquín de Primeros Auxilios', 4, '2023-05-20', 'Contiene suministros básicos para atención médica de emergencia', 100, 3, 1, 1, NULL, NULL),
(7, 'Dispositivo de Monitoreo de Fatiga', 4, '2023-06-15', 'Monitorea el estado de alerta de los trabajadores', 250, 3, 2, 1, NULL, NULL),
(8, 'Carretillas elevadoras (montacargas)', 5, '2022-04-12', 'Vehículos diseñados para levantar, transportar y organizar materiales pesados dentro del almacén o la planta', 500, 3, 1, 1, NULL, NULL),
(9, 'Comprensor de aire', 5, '2021-05-10', 'Equipos que generan aire comprimido para alimentar herramientas neumáticas o para tareas de limpieza en entornos industriales', 400, 3, 1, 2, NULL, NULL),
(10, 'Bomba de agua portatil', 5, '2023-12-10', 'Equipos utilizados para desaguar áreas de trabajo o para transportar agua en situaciones de emergencia o mantenimiento', 1000, 3, 1, 2, NULL, NULL);

INSERT INTO Tipo_Severidad (Id_tipo_severidad, Nombre_tipo_severidad) VALUES
('CA', 'Catastrófico'),
('MO', 'Mortalidad'),
('PE', 'Permanente'),
('TE', 'Temporal'),
('ME', 'Menor');

INSERT INTO Tipo_Estrategia_Control (Id_tipo_estgia_control, Nombre_tipo_estgia_control) VALUES
('P', 'Preventivo'),
('M', 'Mitigación');

INSERT INTO Tipo_Medida_Control (Id_tipo_med_control, Nombre_tipo_med_control) VALUES
('ELI', 'Eliminación'),
('SUS', 'Sustitución'),
('INH', 'Ingeniería'),
('ADM', 'Administrativo'),
('EPP', 'Equipo de Protección Personal (EPP)');

INSERT INTO Tipo_Q_Control (Id_tipo_Qcontrol, Nombre_tipo_Qcontrol) VALUES
('A', 'TOTAL: Los controles eliminan prácticamente la probabilidad o impacto del riesgo'),
('B', 'ALTA: Los controles limitan significativamente la probabilidad o impacto del riesgo'),
('C', 'MEDIA: Los controles limitan la probabilidad o impacto, pero no drásticamente'),
('D', 'BAJA: Los controles muestran debilidades significativas'),
('E', 'MUY BAJA: Controles inexistentes o poco efectivos');

INSERT INTO Tipo_Riesgo (Id_tipo_riesgo, Nombre_tipo_riesgo) VALUES
('BA', 'Bajo'),
('ME', 'Medio'),
('AL', 'Alto');

INSERT INTO Tipos_Contacto (Id_tipo_contacto, Nombre_tipo_contacto) VALUES
('TC01', 'Golpear contra (corriendo hacia o tropezando con)'),
('TC02', 'Golpeado por (objeto en movimiento)'),
('TC03', 'Caída al mismo nivel (resbalar, tropezar, volcarse)'),
('TC04', 'Caída a distinto nivel (el cuerpo cae)'),
('TC05', 'Atrapado en (enganchado, agarrado)'),
('TC06', 'Objetos punzantes o cortantes'),
('TC07', 'Aplastado/Chancado entre o debajo de objetos'),
('TC08', 'Energía (neumática, radiación, etc.)'),
('TC09', 'Materiales químicos'),
('TC10', 'Materiales biológicos'),
('TC11', 'Ergonómicos'),
('TC12', 'Psicosociales');

INSERT INTO Tipos_Peligro (Id_tipo_peligro, Nombre_tipo_peligro) VALUES
('TC01.01', 'Materiales'),
('TC01.02', 'Pisos, hastiales y carga'),
('TC02.01', 'Operación de vehículos y equipos móviles'),
('TC02.02', 'Estabilidad de presas y depósitos'),
('TC02.03', 'Estabilidad de macizo rocoso'),
('TC02.04', 'Carro minero'),
('TC02.05', 'Tubería, manguera de aire comprimido o accesorios'),
('TC02.06', 'Herramientas'),
('TC02.07', 'Materiales (que caen)'),
('TC02.08', 'Proyección de partículas'),
('TC02.09', 'Proyección de balas'),
('TC02.10', 'Equipo de izaje, grúas o carga'),
('TC03.01', 'Pisos resbaladizos'),
('TC03.02', 'Piso desnivelado'),
('TC04.01', 'Izaje en piques'),
('TC04.02', 'Subir/Bajar escaleras'),
('TC04.03', 'Espacios abiertos'),
('TC04.04', 'Andamios y plataformas elevadas'),
('TC04.05', 'Tolvas/Echaderos'),
('TC05.01', 'Fajas transportadoras'),
('TC05.02', 'Partes móviles o giratorias'),
('TC06.01', 'Herramientas punzo cortantes'),
('TC06.02', 'Mallas electrosoldadas'),
('TC07.01', 'Carga suspendida'),
('TC07.02', 'Succión (tolvas, echaderos, labores antiguas, subsidencias)'),
('TC07.03', 'Material de excavaciones y zanjas'),
('TC07.04', 'Materiales apilados'),
('TC08.01', 'Energía eléctrica'),
('TC08.02', 'Transporte de personal'),
('TC08.03', 'Tormenta eléctrica'),
('TC08.04', 'Equipos/Instalaciones neumáticos o hidráulicos (gases comprimidos, calderos, otros)'),
('TC08.05', 'Temperaturas extremas (calor, frío)'),
('TC08.06', 'Radiación (solar, radioactiva, soldadura, otros)'),
('TC08.07', 'Ruido (que sobrepase el LMP)'),
('TC09.01', 'Gases interior mina (Ventilación)'),
('TC09.02', 'Explosivos'),
('TC09.03', 'Materiales químicos peligrosos'),
('TC09.04', 'Otros gases'),
('TC09.05', 'Otros químicos'),
('TC09.06', 'Humo (combustión, soldadura, otros)'),
('TC09.07', 'Hidrocarburos'),
('TC09.08', 'Material inflamable / Combustible'),
('TC09.09', 'Polvo'),
('TC10.01', 'Fluidos Corporales'),
('TC10.02', 'Animales'),
('TC10.03', 'Vectores (roedores, insectos, otros)'),
('TC10.04', 'Microorganismos'),
('TC10.05', 'COVID-19'),
('TC11.01', 'Iluminación (excesiva/deficiente)'),
('TC11.02', 'Vibraciones'),
('TC11.03', 'Movimientos repetitivos'),
('TC11.04', 'Manipulación manual de cargas'),
('TC11.05', 'Posturas'),
('TC12.01', 'Agresiones físicas y verbales'),
('TC12.02', 'Delincuencia común'),
('TC12.03', 'Acoso laboral (hostilización)'),
('TC12.04', 'Carga de trabajo (horarios, sobretiempo, descanso, otros)'),
('TC12.05', 'Nivel de Cultura de Seguridad (Liderazgo)'),
('TC12.06', 'Hostigamiento sexual');

INSERT INTO Tipo_Probabilidad (Id_tipo_probabilidad, Nombre_tipo_probabilidad) VALUES
('P-ALT', 'Común'),
('P-MED', 'Ha sucedido'),
('P-BAJ', 'Podría suceder'),
('P-RAR', 'Raro que suceda'),
('P-IMP', 'Prácticamente imposible que suceda');

INSERT INTO equipo_evaluador (Id_equipo_evaluador, Cant_empleados, Codigo_empleado) VALUES
(1, 5, 1),
(2, 6, 2),
(3, 4, 3),
(4, 7, 4),
(5, 5, 5),
(6, 6, 6),
(7, 4, 7),
(8, 7, 8);

-- Poblar la tabla Proceso
INSERT INTO proceso (Id_proceso, Descripcion_proceso, Id_equipo_evaluador) VALUES
(1, 'MANTTO PLANTA', 1),
(2, 'MTTO.ELECTRICO', 2),
(3, 'MTTO. EQUIPOS', 3),
(4, 'MTTO. SERVICIOS', 4),
(5, 'INSPECCIÓN DE SEGURIDAD', 2),
(6, 'MANTENIMIENTO PREDICTIVO', 3),
(7, 'REVISION DE SISTEMAS ELÉCTRICOS', 4),
(8, 'PRUEBAS DE CARGA', 1);



-- Poblar la tabla Actividades
INSERT INTO Actividades (Id_actividad, Descripcion_actividad, Id_proceso) VALUES
(1, 'Revisión de equipos mecánicos', 1),
(2, 'Mantenimiento preventivo eléctrico', 2),
(3, 'Inspección de sistemas de seguridad', 3),
(4, 'Pruebas de funcionamiento', 4),
(5, 'Supervisión del personal', 5),
(6, 'Evaluación de procedimientos operativos', 6),
(7, 'Control de calidad en planta', 7),
(8, 'Verificación de condiciones de trabajo', 8);

INSERT INTO Tareas (Id_tarea, Descripcion_tarea, Puesto_trabajo, Cond_operacional, Id_actividad) 
VALUES
(1, 'Lubricar los equipos mecánicos', 'Técnico de Mantenimiento', 'El equipo debe estar apagado y bloqueado antes de la lubricación', 1),
(2, 'Revisar el estado de los cables eléctricos', 'Electricista', 'Uso de guantes dieléctricos y herramientas aisladas', 2),
(3, 'Inspeccionar las alarmas de seguridad', 'Supervisor de Seguridad', 'Realizar pruebas sin interferir con la operación', 3),
(4, 'Verificar el correcto funcionamiento de las válvulas', 'Técnico de Procesos', 'El sistema debe estar presurizado', 4),
(5, 'Supervisar el uso de los equipos de protección personal (EPP)', 'Jefe de Seguridad', 'Asegurar que todo el personal cuente con el EPP adecuado', 5),
(6, 'Evaluar los procedimientos de operación', 'Supervisor de Planta', 'Todos los trabajadores deben conocer los procedimientos', 6),
(7, 'Controlar la calidad del producto terminado', 'Inspector de Calidad', 'Inspeccionar visualmente y realizar pruebas de laboratorio', 7),
(8, 'Asegurar que los equipos están en condiciones adecuadas para trabajar', 'Técnico de Seguridad', 'Verificar antes del inicio de cada turno', 8),
(9, 'Verificar el estado de los extintores en el área de producción', 'Supervisor de Seguridad', 'Revisar fechas de vencimiento y estado de los equipos', 4),
(10, 'Supervisar el correcto uso de las herramientas de trabajo', 'Jefe de Producción', 'Inspeccionar herramientas antes y después de cada turno', 7);


-- Poblar la tabla Identificacion_del_riesgo
INSERT INTO Identificacion_del_riesgo (Id_riesgo, Descripcion_peligro, Evento_no_deseado) VALUES
(1, 'Caída de objetos', 'Golpeado por objeto caído'),
(2, 'Fallo eléctrico', 'Electrocución'),
(3, 'Exposición a productos químicos', 'Inhalación de químicos'),
(4, 'Deslizamiento de materiales', 'Resbalones en la superficie'),
(5, 'Mal uso de maquinaria', 'Atrapado en maquinaria'),
(6, 'Explosión de gas', 'Quemaduras'),
(7, 'Derrame de líquidos peligrosos', 'Contacto con sustancias tóxicas'),
(8, 'Sobrecalentamiento de equipos', 'Incendio en planta');


-- Poblar la tabla Valoracion_del_riesgo_inicial
INSERT INTO Valoracion_del_riesgo_inicial (Id_valoracion_inicial, Id_tipo_severidad, Id_tipo_probabilidad, Id_tipo_riesgo) VALUES
(1, 'CA', 'P-ALT', 'AL'),  -- Riesgo alto con severidad catastrófica y alta probabilidad
(2, 'MO', 'P-MED', 'ME'),  -- Riesgo medio con severidad mortalidad y probabilidad media
(3, 'PE', 'P-BAJ', 'BA'),  -- Riesgo bajo con severidad permanente y baja probabilidad
(4, 'TE', 'P-RAR', 'ME'),  -- Riesgo medio con severidad temporal y rara probabilidad
(5, 'ME', 'P-IMP', 'BA'),  -- Riesgo bajo con severidad menor e imposible probabilidad
(6, 'CA', 'P-ALT', 'AL'),  -- Riesgo alto con severidad catastrófica y alta probabilidad
(7, 'MO', 'P-MED', 'ME'),  -- Riesgo medio con severidad mortalidad y probabilidad media
(8, 'TE', 'P-IMP', 'BA');  -- Riesgo bajo con severidad temporal e imposible probabilidad


-- Poblar la tabla Control
INSERT INTO Control (Id_control, Descripcion_control, Id_tipo_estgia_control, Id_tipo_med_control, Id_tipo_Qcontrol) VALUES
(1, 'Instalación de barandillas en plataformas elevadas', 'P', 'INH', 'A'),
(2, 'Mantenimiento preventivo de equipos eléctricos', 'P', 'ADM', 'B'),
(3, 'Uso obligatorio de equipo de protección personal (EPP)', 'M', 'EPP', 'C'),
(4, 'Sustitución de sustancias químicas peligrosas', 'P', 'SUS', 'B'),
(5, 'Implementación de procedimientos de seguridad', 'M', 'ADM', 'A'),
(6, 'Eliminación de riesgos en zonas de trabajo', 'P', 'ELI', 'A'),
(7, 'Monitoreo constante de niveles de gases peligrosos', 'P', 'INH', 'B'),
(8, 'Capacitación regular del personal en seguridad', 'M', 'ADM', 'C');


-- Poblar la tabla Valorizacion_del_riesgo
INSERT INTO Valorizacion_del_riesgo (Id_valoracion_residual, Cantidad_controles, Id_valoracion_inicial, Id_control, Id_tipo_riesgo) 
VALUES
(1, 3, 1, 1, 'AL'),  -- Riesgo alto
(2, 2, 2, 2, 'ME'),  -- Riesgo medio
(3, 5, 3, 3, 'BA'),  -- Riesgo bajo
(4, 4, 4, 4, 'ME'),  -- Riesgo medio
(5, 3, 5, 5, 'BA'),  -- Riesgo bajo
(6, 2, 6, 6, 'AL'),  -- Riesgo alto
(7, 1, 7, 7, 'ME'),  -- Riesgo medio
(8, 4, 8, 8, 'BA');  -- Riesgo bajo

-- Poblar la tabla Plan_de_accion_de_mejora
INSERT INTO Plan_de_accion_de_mejora (Id_plan_mejora, Id_valoracion_residual, Accion_que, Cantidad_empleados, Fecha_cuando) VALUES
(1, 1, 'Implementar señalización de seguridad en áreas críticas', 5, '2024-01-10'),
(2, 2, 'Realizar mantenimiento preventivo en los equipos eléctricos', 3, '2024-02-15'),
(3, 3, 'Capacitar al personal sobre manejo de químicos peligrosos', 10, '2024-03-20'),
(4, 4, 'Instalar barreras físicas para evitar caídas de materiales', 4, '2024-04-25'),
(5, 5, 'Revisar y ajustar los procedimientos de trabajo con maquinaria', 7, '2024-05-05'),
(6, 6, 'Actualizar los sistemas de detección de gases en planta', 6, '2024-06-10'),
(7, 7, 'Implementar sistemas de ventilación en áreas confinadas', 8, '2024-07-18'),
(8, 8, 'Realizar simulacros de emergencia ante incendios', 12, '2024-08-30');

-- Poblar la tabla Analisis_riesgo
INSERT INTO Analisis_riesgo (Id_analisis, Afectado, Consecuencia, Id_riesgo, Id_tipo_contacto, Id_tipo_peligro) VALUES
(1, 'Trabajador de Mantenimiento', 'Lesiones por caída al mismo nivel', 1, 'TC03', 'TC03.01'), -- Caída al mismo nivel
(2, 'Operador de Máquina', 'Golpes por objetos en movimiento', 2, 'TC02', 'TC02.07'), -- Golpes por objetos
(3, 'Personal de Limpieza', 'Exposición a materiales peligrosos', 3, 'TC09', 'TC09.01'), -- Contacto con sustancias químicas
(4, 'Supervisor de Área', 'Caída desde altura', 4, 'TC04', 'TC04.01'), -- Caída a distinto nivel
(5, 'Trabajador de Almacén', 'Aplastamiento por cargas pesadas', 5, 'TC07', 'TC07.01'), -- Atrapado o aplastado por objetos
(6, 'Personal de Oficina', 'Estrés por acoso laboral', 6, 'TC12', 'TC12.03'), -- Psicosocial
(7, 'Trabajador Externo', 'Quemaduras por contacto con objetos calientes', 7, 'TC08', 'TC08.05'), -- Energía térmica
(8, 'Operador de Grúa', 'Golpes por caída de materiales suspendidos', 8, 'TC07', 'TC07.04'); -- Aplastamiento

-- Poblar la tabla TareasXIdentRiesgo
INSERT INTO TareasXIdentRiesgo (Id_tarea, Id_riesgo) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

-- Poblar la tabla PlanaccionxEmpleado
INSERT INTO PlanaccionxEmpleado (Id_plan_mejora, Codigo_empleado) VALUES
(1, 1),  -- Juan Pérez participa en el plan de mejora 1
(2, 2),  -- Ana Gómez participa en el plan de mejora 2
(3, 3),  -- Carlos Sánchez participa en el plan de mejora 3
(4, 4),  -- Lucía Fernández participa en el plan de mejora 4
(5, 5),  -- Pedro Ramírez participa en el plan de mejora 5
(6, 6),  -- María Torres participa en el plan de mejora 6
(7, 7),  -- Diego Morales participa en el plan de mejora 7
(8, 8);  -- Laura Espinoza participa en el plan de mejora 8


-- Poblar la tabla Registros_por_Riesgos
INSERT INTO Registros_por_Riesgos (Id_registro_riesgo, id_valoracion_inicial, Id_plan_mejora, Id_analisis, Id_valoracion_residual) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 2),
(3, 3, 3, 3, 3),
(4, 4, 4, 4, 4),
(5, 5, 5, 5, 5),
(6, 6, 6, 6, 6),
(7, 7, 7, 7, 7),
(8, 8, 8, 8, 8);

-- Poblar la tabla Registro_IPERC
INSERT INTO Registro_IPERC (Id_reg_iperc, Id_proceso, Fecha_registro, Cant_riesgos_analizados, Id_registro_riesgo) VALUES
(1, 1, '2022-01-02', 5, 1),
(2, 1, '2023-12-12', 3, 2),
(3, 2, '2024-02-01', 4, 3),
(4, 3, '2024-03-05', 2, 4),
(5, 3, '2024-04-10', 6, 5),
(6, 4, '2024-05-15', 3, 6),
(7, 5, '2024-06-20', 5, 7),
(8, 6, '2024-07-25', 4, 8);

-- Poblamiento de la tabla Tipo_maquina
INSERT INTO Tipo_maquina (id_tipo_maquina, nombre_tipo) VALUES
(A, 'Excavadora'),
(B, 'Tractor'),
(C, 'Topador'),
(D, 'Perforadora'),
(E, 'Cargador');

-- Poblamiento de la tabla Estado_maquina
INSERT INTO Estado_maquina (id_estado, nombre_estado) VALUES
(A, 'Operativa'),
(B, 'Mantenimiento'),
(C, 'Fuera de servicio');

-- Poblamiento de la tabla Maquina
INSERT INTO Maquina (Num_serie, Fecha_ultima_inspeccion, Fecha_adquisicion, Modelo, Marca, id_tipo_maquina, id_estado) VALUES
('SN001', '2024-05-01', '2021-08-15', 'MX200', 'Caterpillar', 'A', 'A'),
('SN002', '2024-03-10', '2020-07-22', 'B34', 'Komatsu', 'E', 'B'),
('SN003', '2023-12-22', '2019-01-05', 'ZT100', 'Hitachi', 'C', 'C'),
('SN004', '2024-01-17', '2018-09-17', 'X120', 'Volvo', 'D', 'B'),
('SN005', '2024-02-14', '2022-03-10', 'HD200', 'JCB', 'A', 'A'),
('SN006', '2023-11-21', '2020-10-25', 'G50', 'John Deere', 'B', 'C'),
('SN007', '2024-04-19', '2021-04-05', 'TX400', 'Caterpillar', 'C', 'A'),
('SN008', '2024-06-07', '2022-06-25', 'K200', 'Komatsu', 'D', 'B'),
('SN009', '2023-12-03', '2019-12-11', 'MT500', 'Hitachi', 'A', 'C'),
('SN010', '2024-02-09', '2021-09-09', 'RT300', 'Volvo', 'E', 'A'),
('SN011', '2023-10-28', '2018-12-02', 'ZT500', 'JCB', 'C', 'B'),
('SN012', '2024-03-18', '2021-11-23', 'VZ400', 'John Deere', 'D', 'A'),
('SN013', '2023-11-06', '2020-08-14', 'PX200', 'Caterpillar', 'A', 'B'),
('SN014', '2024-04-26', '2019-05-17', 'X500', 'Komatsu', 'B', 'C'),
('SN015', '2023-12-19', '2020-03-20', 'G700', 'Hitachi', 'C', 'A'),
('SN016', '2024-02-23', '2022-01-15', 'L450', 'Volvo', 'D', 'B'),
('SN017', '2023-10-30', '2021-07-05', 'HD300', 'JCB', 'A', 'C'),
('SN018', '2024-03-11', '2020-10-30', 'R300', 'John Deere', 'B', 'A'),
('SN019', '2023-11-26', '2019-09-10', 'TX600', 'Caterpillar', 'C', 'B'),
('SN020', '2024-05-08', '2022-04-28', 'K300', 'Komatsu', 'E', 'A');

-- Poblamiento de la tabla Tipo_mantenimiento
INSERT INTO Tipo_mantenimiento (id_tipo_mant, nombre_tipo_mant) VALUES
('PR', 'Preventivo'),
('CR', 'Correctivo'),
('PM', 'Predictivo');

-- Poblamiento de la tabla Mantenimiento
INSERT INTO Mantenimiento (Cod_Act_mantto, Descripcion, Tarea, Peligros, Fecha_inicio_programado, Fecha_fin_programado, ID_Orden, Codigo_plan, Num_serie, id_tipo_mant)
VALUES
(1, 'Reemplazo de motor principal en la línea de producción', 'Cambio de motor', 'Golpes y caídas durante la instalación', '2024-01-08', '2024-01-10', 1, 1, 'SN001', 'CR'),
(2, 'Revisión del sistema de control eléctrico y ajuste de cables', 'Inspección y ajuste de conexiones', 'Descarga eléctrica', '2024-02-10', '2024-02-15', 2, 2, 'SN002', 'CR'),
(3, 'Inspección de seguridad de maquinaria en la planta de ensamblaje', 'Revisión de sistemas de seguridad', 'Golpes y aplastamientos', '2024-03-15', '2024-03-20', 3, 3, 'SN003', 'PM'),
(4, 'Mantenimiento preventivo en maquinaria del área de pintura', 'Limpieza y lubricación de componentes', 'Inhalación de productos químicos', '2024-04-20', '2024-04-25', 4, 4, 'SN004', 'PR'),
(5, 'Reemplazo de filtros del sistema de ventilación', 'Cambio de filtros', 'Inhalación de polvo', '2024-05-25', '2024-05-30', 5, 5, 'SN005', 'CR'),
(6, 'Actualización de software de monitoreo del sistema', 'Instalación y configuración de software', 'Caída del sistema', '2024-06-01', '2024-06-05', 6, 6, 'SN006', 'PM'),
(7, 'Inspección y revisión de maquinaria pesada', 'Evaluación estructural y funcional', 'Aplastamientos y caídas', '2024-07-05', '2024-07-10', 7, 7, 'SN007', 'PR'),
(8, 'Reparación del sistema hidráulico', 'Sustitución de mangueras y ajuste de válvulas', 'Fugas de fluidos y sobrepresión', '2024-08-10', '2024-08-15', 8, 8, 'SN008', 'CR'),
(9, 'Evaluación del sistema de refrigeración en toda la planta', 'Pruebas de rendimiento y ajuste', 'Sobrecarga térmica', '2024-09-15', '2024-09-20', 9, 9, 'SN009', 'PM'),
(10, 'Verificación y ajuste de sistemas de alarma en el área de producción', 'Revisión de sensores y conexiones', 'Falsos positivos', '2024-10-20', '2024-10-25', 10, 10, 'SN010', 'PR'),
(11, 'Mantenimiento del sistema de iluminación de la planta', 'Sustitución de lámparas y ajuste de conexiones', 'Cortes eléctricos', '2024-11-01', '2024-11-05', 11, 11, 'SN011', 'CR'),
(12, 'Revisión de protocolos de seguridad y sistemas asociados', 'Inspección de procedimientos de emergencia', 'Fallas en la detección de emergencias', '2024-12-05', '2024-12-10', 12, 12, 'SN012', 'PM'),
(13, 'Actualización y ajuste de maquinaria en la línea de ensamblaje', 'Instalación de nuevos componentes', 'Golpes y atrapamientos', '2025-01-10', '2025-01-15', 13, 13, 'SN013', 'PR'),
(14, 'Inspección del sistema de control de calidad', 'Revisión de calibración de equipos', 'Errores de medición', '2025-02-15', '2025-02-20', 14, 14, 'SN014', 'CR'),
(15, 'Reemplazo de componentes electrónicos obsoletos', 'Sustitución de tarjetas y circuitos', 'Cortocircuitos y fallos eléctricos', '2025-03-20', '2025-03-25', 15, 15, 'SN015', 'PM'),
(16, 'Optimización de la línea de empaquetado', 'Reconfiguración de maquinaria', 'Fallas mecánicas y paradas imprevistas', '2025-04-25', '2025-04-30', 16, 16, 'SN016', 'PR'),
(17, 'Evaluación bimestral del sistema de ventilación', 'Limpieza de ductos y ajuste de ventiladores', 'Inhalación de polvo', '2025-05-01', '2025-05-05', 17, 17, 'SN017', 'CR'),
(18, 'Mantenimiento preventivo de sistemas de refrigeración', 'Ajuste de compresores y verificación de fluidos', 'Sobrecarga de sistemas', '2025-06-05', '2025-06-10', 18, 18, 'SN018', 'PM'),
(19, 'Revisión trimestral de sistemas de alarma', 'Verificación y ajuste de sensores', 'Falsos positivos y fallos de detección', '2025-07-10', '2025-07-15', 19, 19, 'SN019', 'PR'),
(20, 'Actualización de software de control semestral', 'Instalación de nuevas versiones de software', 'Errores en el sistema', '2025-08-15', '2025-08-20', 20, 20, 'SN020', 'CR');

-- Poblamiento de la tabla Auditoria
INSERT INTO Auditoria (Codigo_Auditoria, Cod_Act_mantto, Fecha_auditoria, ubi_auditoria, resp_auditoria, Descripcion, objetivo)
VALUES
(1, 1, '2024-01-12', 'Planta de producción - Línea 1', 'Juan Pérez', 'Auditoría para verificar el reemplazo adecuado del motor en la línea de producción', 'Verificar cumplimiento de los estándares de instalación del motor'),
(2, 2, '2024-02-18', 'Planta de sistemas eléctricos', 'Ana Rodríguez', 'Revisión de la correcta inspección y ajuste del sistema de control eléctrico', 'Asegurar la seguridad eléctrica y el funcionamiento continuo del sistema'),
(3, 3, '2024-03-23', 'Planta de ensamblaje', 'Carlos Gómez', 'Auditoría de los sistemas de seguridad tras la inspección en la planta de ensamblaje', 'Comprobar la efectividad de las medidas de seguridad implementadas'),
(4, 4, '2024-04-28', 'Área de pintura', 'Laura Fernández', 'Verificación del mantenimiento preventivo en los equipos del área de pintura', 'Asegurar la funcionalidad y seguridad de los equipos de pintura'),
(5, 5, '2024-06-02', 'Sistema de ventilación - Planta 3', 'Miguel Ramírez', 'Auditoría del reemplazo de filtros en el sistema de ventilación', 'Comprobar el adecuado funcionamiento del sistema tras el cambio de filtros'),
(6, 6, '2024-06-07', 'Centro de monitoreo - Planta principal', 'Luis Sánchez', 'Verificación de la actualización del software de monitoreo', 'Evaluar la correcta instalación y funcionamiento del software actualizado'),
(7, 7, '2024-07-15', 'Área de maquinaria pesada', 'Carolina Díaz', 'Revisión post-inspección de maquinaria pesada', 'Verificar el estado funcional y de seguridad de la maquinaria revisada'),
(8, 8, '2024-08-20', 'Taller de hidráulica - Planta 4', 'Ricardo Torres', 'Auditoría de la reparación del sistema hidráulico', 'Asegurar la funcionalidad del sistema tras las reparaciones realizadas'),
(9, 9, '2024-09-25', 'Planta de refrigeración', 'Elena Ruiz', 'Verificación de la evaluación del sistema de refrigeración', 'Evaluar la eficiencia del sistema tras las pruebas de rendimiento realizadas'),
(10, 10, '2024-10-28', 'Área de alarmas - Planta 2', 'Fernando Gutiérrez', 'Auditoría de la verificación de sistemas de alarma', 'Comprobar el correcto funcionamiento y ajuste de los sistemas de alarma'),
(11, 11, '2024-11-10', 'Planta de iluminación', 'Sofía Martínez', 'Auditoría del mantenimiento de sistemas de iluminación', 'Asegurar la correcta instalación y operación de las lámparas sustituidas'),
(12, 12, '2024-12-15', 'Centro de seguridad - Planta 5', 'Daniel Ortega', 'Verificación de la revisión de protocolos de seguridad', 'Comprobar que los protocolos revisados cumplan con los estándares de seguridad'),
(13, 13, '2025-01-20', 'Línea de ensamblaje - Planta 1', 'Gabriela Vega', 'Auditoría de la actualización de maquinaria en la línea de ensamblaje', 'Verificar la instalación de los nuevos componentes y su correcto funcionamiento'),
(14, 14, '2025-02-25', 'Control de calidad - Planta 2', 'Martín Salazar', 'Revisión de la inspección de sistemas de control de calidad', 'Asegurar la calibración adecuada de los equipos de control de calidad'),
(15, 15, '2025-03-28', 'Área de sistemas electrónicos', 'Jorge López', 'Auditoría del reemplazo de componentes electrónicos', 'Comprobar que los componentes reemplazados funcionen correctamente'),
(16, 16, '2025-05-02', 'Línea de empaquetado - Planta 6', 'Mariana Herrera', 'Auditoría de la optimización de la línea de empaquetado', 'Asegurar que la reconfiguración de maquinaria optimice la producción'),
(17, 17, '2025-05-07', 'Sistema de ventilación - Planta 4', 'César García', 'Revisión post-mantenimiento del sistema de ventilación', 'Comprobar el buen funcionamiento del sistema tras la limpieza de ductos'),
(18, 18, '2025-06-15', 'Planta de refrigeración', 'Olga Vázquez', 'Auditoría del mantenimiento preventivo del sistema de refrigeración', 'Evaluar la condición del sistema de refrigeración tras el mantenimiento'),
(19, 19, '2025-07-18', 'Área de alarmas - Planta 2', 'Rubén Méndez', 'Verificación trimestral de sistemas de alarma', 'Comprobar el correcto funcionamiento de los sensores tras la revisión'),
(20, 20, '2025-08-25', 'Centro de control - Planta 5', 'Gloria Paredes', 'Auditoría de la actualización del software de control semestral', 'Evaluar la instalación y operación del nuevo software en el sistema de control');

--Poblamiento de la tabla Capacitaciones
INSERT INTO Capacitaciones (Codigo_Capacitacion, Fecha_Capacitacion, Descripcion, Duracion, Instructor, Evaluador)
VALUES
(1, '2024-01-15', 'Capacitación en seguridad laboral y manejo de maquinaria pesada', 8.5, 'Juan Rodríguez', 'Ana Gómez'),
(2, '2024-03-10', 'Capacitación sobre actualización de software de control de producción', 6.0, 'Carlos Pérez', 'Lucía Fernández'),
(3, '2024-05-05', 'Taller de prevención de riesgos laborales y primeros auxilios', 4.5, 'Marta Ruiz', 'Pedro Ramírez'),
(4, '2024-06-20', 'Capacitación técnica en mantenimiento de maquinaria industrial', 7.0, 'Luis Vargas', 'Carlos Sánchez'),
(5, '2024-08-30', 'Capacitación avanzada en optimización de líneas de producción', 5.5, 'Laura Espinoza', 'Diego Morales'),
(6, '2024-09-15', 'Capacitación sobre manejo de sistemas de control eléctrico', 9.0, 'Sofía López', 'Andrés Ruiz'),
(7, '2024-10-25', 'Curso de actualización en normas de seguridad y prevención de incendios', 4.0, 'Fernando Vega', 'Gabriela Torres'),
(8, '2024-11-10', 'Taller práctico en eficiencia energética y sostenibilidad', 5.0, 'María Torres', 'Raúl Mendoza'),
(9, '2025-01-18', 'Capacitación sobre el uso de equipos hidráulicos', 6.5, 'Jorge Herrera', 'Camila Rojas'),
(10, '2025-03-05', 'Taller sobre gestión de mantenimiento predictivo', 3.0, 'Elena Castillo', 'Valentina Díaz');

--Poblamiento de la tabla EmpleadoxCapacitacion
INSERT INTO EmpleadoxCapacitacion (Codigo_empleado, Codigo_Capacitacion)
VALUES
(1, 1), (2, 1), (3, 1),
(4, 2), (5, 2), (6, 2),
(7, 3), (8, 3), (9, 3),
(10, 4), (11, 4), (12, 4),
(13, 5), (14, 5), (15, 5),
(16, 6), (17, 6), (18, 6),
(19, 7), (20, 7), (1, 7),
(2, 8), (3, 8), (4, 8),
(5, 9), (6, 9), (7, 9),
(8, 10), (9, 10), (10, 10);


