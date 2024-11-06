--- Eliminación de datos anteriores ---
DO $$ DECLARE
    row RECORD;
BEGIN
    FOR row IN
        SELECT tablename
        FROM pg_tables
        WHERE schemaname = 'public'
    LOOP
        EXECUTE 'TRUNCATE TABLE ' || quote_ident(row.tablename) || ' CASCADE';
    END LOOP;
END $$;


--- Poblamiento de la tabla Cargo_empleado
INSERT INTO Cargo_empleado (Id_Cargo, Nombre_cargo) VALUES
(1, 'Jefe'),
(2, 'Técnico'),
(3, 'Supervisor'),
(4, 'Encargado de sistema');

INSERT INTO Acceso_empleado (Id_acceso, Nombre_acceso) VALUES
(1, 'Master'),
(2, 'Administrador'),
(3, 'Usuario');

-- Poblamiento de la tabla Criticidad
INSERT INTO Criticidad (Id_criticidad, Nivel) VALUES
(1, 'Bajo'),
(2, 'Medio'),
(3, 'Alto');

-- Poblamiento de la tabla Estado_mantto
INSERT INTO Estado_mantto (id_estado, estado) VALUES
(1, 'Pendiente'),
(2, 'En curso'),
(3, 'Completa');

-- Poblamiento de la tabla Empleado
COPY empleado FROM 'C:\DBD-2024-2\datos\Empleado.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de la tabla Plan_de_mantenimiento con fechas aleatorias de los últimos 2 meses
COPY plan_de_mantenimiento FROM 'C:\DBD-2024-2\datos\Plan_mantenimiento.csv' DELIMITER ',' CSV HEADER;

INSERT INTO Estado_actv (Id_estado, estado) VALUES
(1, 'Borrador'),
(2, 'Notificado'),
(3, 'Aceptado'),
(4, 'En ejecución'),
(5, 'Ejecutado'),
(6, 'En auditoría'),
(7, 'Auditado');

-- Poblamiento de la tabla Orden_de_trabajo
COPY orden_de_trabajo FROM 'C:\DBD-2024-2\datos\Orden_trabajo.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de la tabla Categoria_Almacen
INSERT INTO Categoria_Almacen (Id_categoria, Nombre_categoria) 
VALUES
(1, 'Insumos'),
(2, 'Herramientas'),
(3, 'Equipos Pesados');

-- Poblamiento de la tabla Estado_Almacen
INSERT INTO Estado_Almacen (Id_estado, Nombre_estado) 
VALUES 
(1, 'Activo'),
(2, 'Inactivo');

-- Poblamiento de la tabla Almacen
COPY Almacen FROM 'C:\DBD-2024-2\datos\Almacen.csv' DELIMITER ',' CSV HEADER;
--Poblamiento insumo
COPY Insumo (Cantidad, Nombre)
FROM 'C:\Users\Joseph\documents\bd4\bd242-grupo3\7\datos\Insumo.csv'
DELIMITER ','
CSV HEADER;
---Poblamiento Estado_reserva
INSERT INTO Estado_reserva (Id_estado_reserva, Nombre_estado) VALUES
(0, 'Inactivo'),
(1, 'Aprobado'),
(2, 'En proceso de envío'),
(3, 'Enviado');
--Poblamiento Reserva
COPY Reserva (Id_reserva, Fecha,Hora,Id_estado_reserva,Id_empleado)
FROM 'C:\Users\Joseph\documents\bd4\bd242-grupo3\7\datos\Reserva.csv'
DELIMITER ','
CSV HEADER;
--Poblamiento Detalle_reserva
COPY Detalle_reserva (Cant_reserv, Id_insumo,Id_reserva)
FROM 'C:\Users\Joseph\documents\bd4\bd242-grupo3\7\datos\detalle_reserva.csv'
DELIMITER ','
CSV HEADER;
-- Poblamiento de la tabla Tipo_equipo_soporte
INSERT INTO Tipo_Equipo_Soporte (Id_tipo, Nombre_tipo) VALUES
(1, 'Generador'),
(2, 'Compresor de Aire'),
(3, 'Torre de Iluminación'),
(4, 'Sistema de Bombeo'),
(5, 'Equipos Pesados');

-- Poblamiento de la tabla Disponibilidad_equipo_soporte
INSERT INTO Disponibilidad_Equipo_Soporte (Id_disponibilidad, Nombre_disponibilidad) 
VALUES
(1, 'Disponible'),
(2, 'Ocupado');

-- Poblamiento de la tabla Estado_equipo_soporte 
INSERT INTO Estado_Equipo_Soporte (Id_estado, Nombre_estado) 
VALUES 
(1, 'Optimo'),
(2, 'Bueno'),
(3, 'Regular'),
(4, 'Deficiente');

-- Poblamiento de la tabla Equipo_de_Soporte
COPY Equipo_de_Soporte FROM 'C:\DBD-2024-2\datos\Equipo_de_Soporte.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de la tabla EquipoSXAlmacen
COPY equiposxalmacen FROM 'C:\DBD-2024-2\datos\EquiposXAlmacen.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de la tabla Actividad_empleado
COPY actividad_empleado FROM 'C:\DBD-2024-2\datos\Actividad_empleado.csv' DELIMITER ',' CSV HEADER;

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


-- Poblamiento de tabla Equipo_evaluador
COPY Equipo_evaluador FROM 'C:\DBD-2024-2\datos\Equipo_evaluador.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla proceso
COPY proceso FROM 'C:\DBD-2024-2\datos\proceso.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla actividades
COPY actividades FROM 'C:\DBD-2024-2\datos\actividades.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla tareas
COPY tareas FROM 'C:\DBD-2024-2\datos\tareas.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla Identificacion_del_riesgo
COPY Identificacion_del_riesgo FROM 'C:\DBD-2024-2\datos\Identificacion_del_riesgo.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla analisis_riesgo
COPY analisis_riesgo FROM 'C:\DBD-2024-2\datos\analisis_riesgo.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla Valoracion_del_riesgo_inicial
COPY Valoracion_del_riesgo_inicial FROM 'C:\DBD-2024-2\datos\Valoracion_del_riesgo_inicial.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla valoracion_del_riesgo
COPY valoracion_del_riesgo FROM 'C:\DBD-2024-2\datos\Valoracion_del_riesgo.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla control
COPY control FROM 'C:\DBD-2024-2\datos\control.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla Plan_de_accion_de_mejora
COPY Plan_de_accion_de_mejora FROM 'C:\DBD-2024-2\datos\Plan_de_accion_de_mejora.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla EquipoEvaluadorXEmpleado
COPY EquipoEvaluadorXEmpleado FROM 'C:\DBD-2024-2\datos\EquipoEvaluadorXEmpleado.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de tabla PlanaccionxEmpleado
COPY PlanaccionxEmpleado FROM 'C:\DBD-2024-2\datos\PlanaccionxEmpleado.csv' DELIMITER ',' CSV HEADER;



-- Poblamiento de la tabla Tipo_maquina
INSERT INTO Tipo_maquina (id_tipo_maquina, nombre_tipo) VALUES
(1, 'Excavadora'),
(2, 'Tractor'),
(3, 'Topador'),
(4, 'Perforadora'),
(5, 'Cargador');

-- Poblamiento de la tabla Estado_maquina
INSERT INTO Estado_maquina (id_estado_maquina, nombre_estado) VALUES
(1, 'Operativa'),
(2, 'Mantenimiento'),
(3, 'Fuera de servicio');

INSERT INTO Marca_maquina (id_marca_maquina, nombre_marca) VALUES
( 1, 'Caterpellir (CAT)' ),
( 2 , 'Komatsu' ),
( 3 , 'Sandvik' ),
( 4 , 'Atlas Copco' ),
( 5 , 'John Deere' ),
( 6 , 'Volvo' ),
( 7 , 'JLG' ),
( 8 , 'Manitou' ),
( 9 , 'Epiroc' );

-- Poblamiento de la tabla Maquina
COPY maquina FROM 'C:\DBD-2024-2\datos\Maquina.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de la tabla Tipo_mantenimiento
INSERT INTO Tipo_mantenimiento (id_tipo_mant, nombre_tipo_mant) VALUES
(1, 'Preventivo'),
(2, 'Correctivo'),
(3, 'Predictivo');

-- Poblamiento de la tabla Estado_mantenimiento
INSERT INTO Estado_mantto (id_estado, estado) VALUES
(1, 'Borrador'),
(2, 'Notificado'),
(3, 'Aceptado'),
(4, 'En ejecución'),
(5, 'Ejecutado'),
(6, 'En auditoría'),
(7, 'Auditado');

-- Poblamiento de la tabla Mantenimiento
COPY mantenimiento FROM 'C:\DBD-2024-2\datos\Mantenimiento.csv' DELIMITER ',' CSV HEADER;


-- Poblamiento de la tabla EquipoSXMantenimiento
COPY equiposxmantenimiento FROM 'C:\DBD-2024-2\datos\EquipoSXMantenimiento.csv' DELIMITER ',' CSV HEADER;


-- Poblamiento de la tabla Auditoria
INSERT INTO Auditoria (Id_Auditoria, Id_Act_mantto, Fecha_auditoria, ubi_auditoria, resp_auditoria, Descripcion, objetivo)
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
INSERT INTO Capacitaciones (Id_Capacitacion, Fecha_Capacitacion, Descripcion, Duracion, Instructor, Evaluador)
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
INSERT INTO EmpleadoxCapacitacion (Id_empleado, Id_Capacitacion)
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


-- EstadoReporte
INSERT INTO Estado_Reporte (nombre_estado) VALUES 
('No Verificado'),
('Verificado'),
('Notificado');

-- Reportes
INSERT INTO Reportes (Fecha_reporte, Id_estado_reporte, Id_supervisor, id_jefe) VALUES 
('2024-09-15', 1, 1, 2),
('2024-09-16', 2, 1, 2),
('2024-09-17', 3, 1, 3),
('2024-09-18', 1, 2, 3),
('2024-09-19', 2, 2, 2),
('2024-09-20', 3, 2, 2),
('2024-09-21', 1, 3, 3),
('2024-09-22', 2, 3, 2),
('2024-09-23', 3, 3, 2),
( '2024-09-24',1, 1, 2);

-- Registro
INSERT INTO Registro ( Fecha_registro, Fecha_inicial, Id_empleado, Id_act_mantto, Calificacion, Observaciones) VALUES 
('2024-09-15', '2024-09-14', 1, 1, 2, 'Reparación de caldera'),
('2024-09-16', '2024-09-15', 1, 2, 3, 'Mantenimiento preventivo'),
('2024-09-17', '2024-09-16', 2, 3, 5, 'Cambio de filtros'),
('2024-09-18', '2024-09-17', 2, 1, 4, 'Reparación de válvulas'),
('2024-09-19', '2024-09-18', 3, 2, 1, 'Mantenimiento general'),
('2024-09-20', '2024-09-19', 3, 3, 5, 'Inspección de equipos'),
('2024-09-21', '2024-09-20', 1, 1, 1, 'Instalación de nuevos sistemas'),
('2024-09-22', '2024-09-21', 2, 2, 2, 'Mantenimiento correctivo'),
('2024-09-23', '2024-09-22', 3, 3, 2, 'Actualización de software'),
( '2024-09-24', '2024-09-23', 1, 1, 3, 'Reparación de fugas');

-- Incidencias Tags
INSERT INTO Incidencias_Tags (Incidencia,id_Registro) VALUES
('Lentitud Personal', 1),
('Falta de Material', 2),
('Maquinaria Insuficiente',1),
('Falta de Personal', 1),
('Complicaciones Climaticas', 2);

-- Notificaciones
INSERT INTO Notificaciones (Fecha_notificacion, Asunto,Mensaje, Id_remitente, Id_destinatario, Id_registro, Id_reporte) VALUES 
('2024-09-15', 'Importante','Se ha creado un nuevo reporte.', 1, 2, 1, 1),
('2024-09-16', 'Sigue con Errores','Actualización en el estado del reporte.', 1, 3, 2, 2),
('2024-09-17', 'Escoge uno','El reporte ha sido cerrado.', 2, 1, 3, 3),
('2024-09-18', 'Asunto Generico','Nuevo registro de mantenimiento.', 1, 2, 4, 4),
('2024-09-19', 'Asunto Generico','Requiere revisión.', 2, 3, 5, 5),
('2024-09-20', 'Asunto Generico', 'Se ha actualizado el registro.', 3, 1, 6, 6),
('2024-09-21', 'Asunto Generico', 'Notificación de mantenimiento programado.', 1, 2, 7, 7),
('2024-09-22', 'Asunto Generico', 'Se ha cerrado el registro.', 2, 3, 8, 8),
('2024-09-23', 'Asunto Generico', 'Se ha generado un nuevo análisis.', 3, 1, 9, 9),
( '2024-09-24','Asunto Generico',  'El registro ha sido actualizado.', 1, 2, 10, 10);

-- Poblamiento de la tabla tipo_urgencia
INSERT INTO Tipo_urgencia (Id_urgencia, Tipo_urgencia)
VALUES
(1, 'Muy urgente'),
(2, 'Medianamente urgente'),
(3, 'Poco urgente');

-- Poblamiento de la tabla estado_pedido
INSERT INTO Estado_pedido (Id_estado_pedido, nombre_estado)
VALUES
(1, 'Sin orden de compra'),
(2, 'Con orden de compra'),
(3, 'Lista para envío'),
(4, 'En camino'),
(5, 'Recibido');

-- Poblamiento de la tabla pedido_compra
COPY pedido_compra FROM 'C:\Users\fabia\Desktop\DBD-2024-2\bd242-grupo3\7\datos\Pedido_Compra.csv' DELIMITER ',' CSV HEADER;

-- Poblamiento de la tabla tipo_producto
INSERT INTO Tipo_producto (Id_tipo_producto, nombre_producto)
VALUES
(1, 'Insumos'),
(2, 'Equipos de Soporte');

-- Poblamiento de la tabla detalle_pedido_compra
COPY detalle_pedido_compra FROM 'C:\Users\fabia\Desktop\DBD-2024-2\bd242-grupo3\7\datos\Detalle_Pedido_Compra.csv' DELIMITER ',' CSV HEADER;


-- Poblamiento de la tabla Proveedor
COPY proveedor FROM 'C:\Users\Joseph\documents\bd4\bd242-grupo3\7\datos\Proveedor.csv' DELIMITER ',' CSV HEADER;


-- Poblamiento de la tabla Orden_de_compra
COPY orden_de_compra FROM 'C:\Users\Joseph\documents\bd4\bd242-grupo3\7\datos\Orden_de_Compra.csv' DELIMITER ',' CSV HEADER;




COPY Registro FROM '..\datos\Orden_trabajo.csv' DELIMITER ',' CSV HEADER;
COPY Incidencias_Tags FROM '..\datos\Incidencias_Tags.csv' DELIMITER ',' CSV HEADER;
COPY Reportes FROM '..\datos\Reportes.csv' DELIMITER ',' CSV HEADER;
COPY Notificaciones FROM '..\datos\Notificaciones.csv' DELIMITER ',' CSV HEADER;




-- Estado_autenticador 
INSERT INTO Estado_autenticador (Id_estado_autenticador, Descripcion)
VALUES 
(1, 'Generado'),
(2, 'Utilizado'),
(3, 'Expirado');

-- Estado_sesion
INSERT INTO Estado_Sesion (Id_estado_sesion, Descripcion)
VALUES 
(1, 'Activa'),
(2, 'Inactiva'),
(3, 'Bloqueada'),
(4, 'Proceso de logeo');

-- Tiempo_max_sesion 
INSERT INTO Tiempo_max_sesion (Cargo, T_max_cargo, Codigo)
VALUES 
('Usuario', 60, 101),
('Administrador', 120, 102),
('Master', 180, 103);


-- Poblamiento de la tabla Sesion_Empleado 

COPY Sesion_Empleado FROM '..\datos\sesion_empleado.csv' DELIMITER ',' CSV HEADER;

/*
INSERT INTO Sesion_Empleado (Id_Sesion, Id_Empleado, Fecha_Hora_Inicio, Fecha_Hora_Final, Direccion_IP, Id_estado_Sesion, Cargo, t_inactividad)
VALUES 
(1, 1, '2024-10-01 08:00:00', '2024-10-01 10:00:00', '192.168.0.1', 2, 'Usuario', INTERVAL '10 minutes'),
(2, 2, '2024-10-02 09:30:00', '2024-10-02 11:30:00', '192.168.0.2', 2, 'Administrador', INTERVAL '15 minutes'),
(3, 3, '2024-10-03 11:00:00', '2024-10-03 12:30:00', '192.168.0.3', 2, 'Master', INTERVAL '5 minutes'),
(4, 4, '2024-10-04 13:00:00', '2024-10-04 14:45:00', '192.168.0.4', 2, 'Usuario', INTERVAL '20 minutes'),
(5, 5, '2024-10-05 14:15:00', '2024-10-05 15:30:00', '192.168.0.5', 2, 'Administrador', INTERVAL '12 minutes'),
(6, 6, '2024-10-06 09:00:00', '2024-10-06 11:00:00', '192.168.0.6', 2, 'Administrador', INTERVAL '18 minutes'),
(7, 7, '2024-10-07 12:00:00', '2024-10-07 13:30:00', '192.168.0.7', 2, 'Usuario', INTERVAL '8 minutes'),
(8, 8, '2024-10-08 10:00:00', '2024-10-08 12:00:00', '192.168.0.8', 2, 'Usuario', INTERVAL '25 minutes'),
(9, 9, '2024-10-09 14:00:00', '2024-10-09 15:00:00', '192.168.0.9', 2, 'Master', INTERVAL '7 minutes'),
(10, 10, '2024-10-10 11:00:00', '2024-10-10 12:30:00', '192.168.0.10', 2, 'Usuario', INTERVAL '10 minutes');
*/

--Autenticacion_en_2_pasos

COPY Autenticacion_en_2_pasos  FROM '..\datos\autenticacion_en_2_pasos.csv' DELIMITER ',' CSV HEADER;

/*
INSERT INTO Autenticacion_en_2_pasos (Id_Autenticacion, Fecha_Hora_Envio, Contador_Intentos, cod_Verificacion, Id_sesion, Id_Estado_autenticador)
VALUES 
(1, '2024-10-01 08:05:00', 1, 123456, 1, 1),
(2, '2024-10-02 09:35:00', 2, 654321, 2, 2),
(3, '2024-10-03 11:05:00', 3, 987654, 3, 3),
(4, '2024-10-04 13:10:00', 1, 321654, 4, 1),
(5, '2024-10-05 14:20:00', 2, 456789, 5, 2),
(6, '2024-10-06 09:10:00', 1, 987321, 6, 3),
(7, '2024-10-07 12:10:00', 3, 654789, 7, 1),
(8, '2024-10-08 10:20:00', 2, 321987, 8, 2),
(9, '2024-10-09 14:30:00', 3, 789123, 9, 2),
(10, '2024-10-10 11:25:00', 1, 123789, 10, 1);
*/


-- Poblar la tabla Sesion_sospechosa 

COPY Sesion_sospechosas  FROM '..\datos\sesion_sospechosa.csv' DELIMITER ',' CSV HEADER;

/*
INSERT INTO Sesion_sospechosa (ID_Sesion_sospechosa, Id_autenticacion, Estado_sesion, Direccion_mac, Tipo_Dispositivo, Fecha_Hora_sospecha, Direccion_ip, Ubicacion, Acciones_tomadas)
VALUES 
(1, 1, 'Activa', '00:1B:44:11:3A:B7', 'PC', '2024-10-01 08:15:00', '192.168.0.6', 'Lima, Peru', 'Alertar al administrador'),
(2, 2, 'Inactiva', '00:1B:44:11:3A:C8', 'Móvil', '2024-10-02 09:45:00', '192.168.0.7', 'Cusco, Peru', 'Bloquear el acceso'),
(3, 3, 'Expirada', '00:1B:44:11:3A:D9', 'Tablet', '2024-10-03 11:15:00', '192.168.0.8', 'Arequipa, Peru', 'Revisar actividad'),
(4, 4, 'Bloqueada', '00:1B:44:11:3A:E0', 'Laptop', '2024-10-04 13:25:00', '192.168.0.9', 'Piura, Peru', 'Notificar a seguridad'),
(5, 5, 'Finalizada', '00:1B:44:11:3A:F1', 'Servidor', '2024-10-05 14:35:00', '192.168.0.10', 'Trujillo, Peru', 'Cerrar sesión automáticamente'),
(6, 6, 'Activa', '00:1B:44:11:3A:G2', 'PC', '2024-10-06 15:00:00', '192.168.0.11', 'Tacna, Peru', 'Solicitar autenticación'),
(7, 7, 'Expirada', '00:1B:44:11:3A:H3', 'Móvil', '2024-10-07 16:20:00', '192.168.0.12', 'Chiclayo, Peru', 'Monitorear actividad'),
(8, 8, 'Bloqueada', '00:1B:44:11:3A:I4', 'Tablet', '2024-10-08 17:45:00', '192.168.0.13', 'Iquitos, Peru', 'Investigar incidencia'),
(9, 9, 'Finalizada', '00:1B:44:11:3A:J5', 'Laptop', '2024-10-09 18:15:00', '192.168.0.14', 'Pucallpa, Peru', 'Enviar alerta al equipo'),
(10, 10, 'Activa', '00:1B:44:11:3A:K6', 'Servidor', '2024-10-10 19:00:00', '192.168.0.15', 'Tarapoto, Peru', 'Revisar actividad en detalle');
*/


-- Poblar la tabla Notificacion_Administrador 

COPY Notificacion_Administrador  FROM '..\datos\notificacion_administrador.csv' DELIMITER ',' CSV HEADER;


/*
INSERT INTO Notificacion_Administrador (ID_Notificacion, Id_Administrador, Tipo_Evento, Fecha_Hora_Notificacion, Estado_Notificacion, Mensaje_Notificacion, Prioridad, ID_Sesion_sospechosa, Id_autenticacion)
VALUES 
(1, 1, 'Sesión Sospechosa', '2024-10-01 08:20:00', 'Enviada', 'Se detectó una sesión sospechosa desde Lima', 'Alta', 1, 1),
(2, 2, 'Fallo de Autenticación', '2024-10-02 09:50:00', 'Enviada', 'Varios intentos fallidos de autenticación desde Cusco', 'Media', 2, 2),
(3, 3, 'Recuperación de Contraseña', '2024-10-03 11:20:00', 'Leída', 'Solicitud de recuperación de contraseña desde Arequipa', 'Baja', 3, 3),
(4, 4, 'Acceso Bloqueado', '2024-10-04 12:00:00', 'Resuelta', 'Acceso bloqueado debido a actividad sospechosa en Piura', 'Alta', 4, 4),
(5, 5, 'Cambio de Contraseña', '2024-10-05 14:10:00', 'En Proceso', 'Cambio de contraseña solicitado desde Trujillo', 'Media', 5, 5),
(6, 6, 'Reintento de Autenticación', '2024-10-06 10:30:00', 'Enviada', 'Reintento de autenticación fallido detectado en Tacna', 'Baja', 6, 6),
(7, 7, 'Inicio de Sesión No Reconocido', '2024-10-07 15:20:00', 'Pendiente', 'Inicio de sesión no reconocido desde Chiclayo', 'Alta', 7, 7),
(8, 8, 'Intento de Hackeo', '2024-10-08 16:40:00', 'Investigando', 'Posible intento de hackeo desde Iquitos', 'Alta', 8, 8),
(9, 9, 'Sesión Expirada', '2024-10-09 13:00:00', 'Enviada', 'Sesión expirada debido a inactividad en Pucallpa', 'Media', 9, 9),
(10, 10, 'Acceso Permitido', '2024-10-10 17:30:00', 'Resuelta', 'Acceso permitido tras verificación en Tarapoto', 'Baja', 10, 10);
*/



INSERT INTO Estado_codigo (Id_estado_codigo, Descripcion)
VALUES 
('Generado', 'Código generado para recuperación'),
('Utilizado', 'Código de recuperación usado'),
('Expirado', 'Código de recuperación expirado'),
('Fallido', 'Intento de recuperación fallido'),
('Validado', 'Código validado con éxito'),
('Reintento', 'Nuevo intento de recuperación'),
('Cancelado', 'Recuperación cancelada por el usuario'),
('Pendiente', 'Código pendiente de uso'),
('Revocado', 'Código revocado por seguridad'),
('Deshabilitado', 'Recuperación deshabilitada temporalmente');


-- Poblar la tabla Recuperacion_de_contraseña

COPY Recuperacion_de_contraseña  FROM '..\datos\notificacion_administrador.csv' DELIMITER ',' CSV HEADER;


/*
INSERT INTO Recuperacion_de_contraseña (ID_recupcontra, ID_sesion, Fecha_Hora_Envio, Nueva_contraseña, Email_envio, Id_estado_codigo)
VALUES 
(1, 2, '2024-10-01 08:20:00', 'newPass123', 'user1@example.com', 'Generado'),
(2, 2, '2024-10-02 09:45:00', 'securePass456', 'user2@example.com', 'Utilizado'),
(3, 2, '2024-10-03 10:30:00', 'resetPass789', 'user3@example.com', 'Expirado'),
(4, 2, '2024-10-04 11:15:00', 'safePass012', 'user4@example.com', 'Fallido'),
(5, 2, '2024-10-05 12:00:00', 'changePass345', 'user5@example.com', 'Validado'),
(6, 2, '2024-10-06 13:45:00', 'retryPass678', 'user6@example.com', 'Reintento'),
(7, 2, '2024-10-07 14:30:00', 'tempPass901', 'user7@example.com', 'Cancelado'),
(8, 2, '2024-10-08 15:15:00', 'quickPass234', 'user8@example.com', 'Pendiente'),
(9, 2, '2024-10-09 16:00:00', 'fastPass567', 'user9@example.com', 'Revocado'),
(10, 2, '2024-10-10 17:45:00', 'finalPass890', 'user10@example.com', 'Deshabilitado');
*/

