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

INSERT INTO equipo_evaluador (Id_equipo_evaluador, Cant_empleados) VALUES
(1, 5),
(2, 6),
(3, 4),
(4, 7),
(5, 5),
(6, 6),
(7, 4),
(8, 7);

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



-- Poblar la tabla Valoracion_del_riesgo
INSERT INTO Valoracion_del_riesgo (Id_valoracion_residual, Cantidad_controles, Id_valoracion_inicial, Id_tipo_riesgo) VALUES
(1, 3, 1, 'AL'), -- Riesgo alto
(2, 2, 2, 'ME'), -- Riesgo medio
(3, 3, 3, 'BA'), -- Riesgo bajo
(4, 4, 4, 'ME'), -- Riesgo medio
(5, 3, 5, 'BA'), -- Riesgo bajo
(6, 2, 6, 'AL'), -- Riesgo alto
(7, 1, 7, 'ME'), -- Riesgo medio
(8, 4, 8, 'BA'); -- Riesgo bajo


-- Poblar la tabla Control
INSERT INTO Control (Id_control, Descripcion_control, Id_tipo_estgia_control, Id_tipo_med_control, Id_tipo_Qcontrol, Id_valoracion_residual) VALUES
(1, 'Instalación de barandillas en plataformas elevadas', 'P', 'INH', 'A', 1),
(2, 'Mantenimiento preventivo de equipos eléctricos', 'P', 'ADM', 'B', 2),
(3, 'Uso obligatorio de equipo de protección personal (EPP)', 'M', 'EPP', 'C', 3),
(4, 'Sustitución de sustancias químicas peligrosas', 'P', 'SUS', 'B', 4),
(5, 'Implementación de procedimientos de seguridad', 'M', 'ADM', 'A', 5),
(6, 'Eliminación de riesgos en zonas de trabajo', 'P', 'ELI', 'A', 6),
(7, 'Monitoreo constante de niveles de gases peligrosos', 'P', 'INH', 'B', 7),
(8, 'Capacitación regular del personal en seguridad', 'M', 'ADM', 'C', 8);

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
INSERT INTO PlanaccionxEmpleado (Id_plan_mejora, Id_empleado) VALUES
(1, 1),  -- Juan Pérez participa en el plan de mejora 1
(2, 2),  -- Ana Gómez participa en el plan de mejora 2
(3, 3),  -- Carlos Sánchez participa en el plan de mejora 3
(4, 4),  -- Lucía Fernández participa en el plan de mejora 4
(5, 5),  -- Pedro Ramírez participa en el plan de mejora 5
(6, 6),  -- María Torres participa en el plan de mejora 6
(7, 7),  -- Diego Morales participa en el plan de mejora 7
(8, 8);  -- Laura Espinoza participa en el plan de mejora 8

INSERT INTO EquipoEvaluadorXEmpleado (id_equipo_evaluador, Id_empleado) VALUES 
(1, 4), 
(2, 3), 
(3, 2), 
(4, 2), 
(5, 5), 
(6, 1);

-- Poblar la tabla Informe_IPERC con datos de ejemplo
INSERT INTO Informe_IPERC (Id_informe_iperc, Fecha_registro, Cant_riesgos_analizados, Unidad_minera, Area, Id_proceso) VALUES
(1, '2022-01-02', 5, 'UCHUCCHACUA', 'MANTENIMIENTO', 1),
(2, '2023-12-12', 3, 'CERRO VERDE', 'MANTENIMIENTO', 2),
(3, '2024-02-01', 4, 'TRAPICHE', 'MANTENIMIENTO', 3),
(4, '2024-03-05', 2, 'EL FAIQUE', 'OPERACIONES', 4),
(5, '2024-04-10', 6, 'COIMOLACHE SULFUROS', 'MINERIA', 5),
(6, '2024-05-15', 3, 'COLQUIJIRCA', 'ADMINISTRACION', 6),
(7, '2024-06-20', 5, 'SAN GREGORIO', 'LOGISTICA', 7),
(8, '2024-07-25', 4, 'CONENHUA', 'MANTENIMIENTO', 8);

-- Poblar la tabla Reg_riesgo_analizado 
INSERT INTO Reg_riesgo_analizado (Id_reg_riesgo_analizado, Id_valoracion_inicial, Id_plan_mejora, Id_analisis, Id_valoracion_residual, Id_informe_iperc) VALUES
(1, 1, 1, 1, 1, 1),
(2, 2, 2, 2, 2, 1),
(3, 3, 3, 3, 3, 2),
(4, 4, 4, 4, 4, 3),
(5, 5, 5, 5, 5, 4),
(6, 6, 6, 6, 6, 5),
(7, 7, 7, 7, 7, 6),
(8, 8, 8, 8, 8, 7);


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

INSERT INTO Herramienta (Id_herramienta, Cantidad, Nombre) VALUES
(1, 100, 'Martillo'),
(2, 200, 'Taladro'),
(3, 1000, 'Clavo'),
(4, 500, 'Sierra'),
(5, 300, 'Destornillador'),
(6, 400, 'Alicate'),
(7, 800, 'Cable'),
(8, 600, 'Guantes'),
(9, 1200, 'Mascarilla'),
(10, 150, 'Botas de seguridad'),
(11, 500, 'Casco'),
(12, 700, 'Chaleco reflectante'),
(13, 900, 'Gafas de protección'),
(14, 50, 'Extintor'),
(15, 100, 'Señal de emergencia'),
(16, 300, 'Cono de seguridad'),
(17, 250, 'Linterna'),
(18, 120, 'Medidor eléctrico'),
(19, 400, 'Batería recargable'),
(20, 50, 'Kit de primeros auxilios');

-- RecursoXMantenimiento
INSERT INTO HerramientaXMantenimiento (Id_HerrXMantto, Id_Act_mantto, Id_Herramienta) VALUES
(1, 1, 2),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 12, 12),
(13, 13, 13),
(14, 14, 14),
(15, 15, 15),
(16, 16, 16),
(17, 17, 17),
(18, 18, 18),
(19, 19, 19),
(20, 20, 20);

-- Poblamiento de la tabla Proveedor
COPY proveedor FROM 'C:\Users\fabia\Desktop\DBD-2024-2\bd242-grupo3\7\datos\Proveedor.csv' DELIMITER ',' CSV HEADER;

/*
INSERT INTO Proveedor (id_proveedor, ruc, Empresa, Contacto, Email, Categoria, Telefono, Direccion) 
VALUES
('20512345678', 'Herramientas S.A.', 'Juan Pérez', 'juan@herramientas.com', 'Herramientas', '123456789', 'Calle Los Alamos 123'),
('20512345679', 'Industria Taladro SAC', 'María Gómez', 'maria@taladro.com', 'Maquinaria', '987654321', 'Av. La Industria 456'),
('20512345680', 'Clavos del Norte SRL', 'Pedro Ruiz', 'pedro@clavos.com', 'Ferretería', '456789123', 'Calle Norte 789'),
('20512345681', 'Sierra Proveedores', 'Luisa Morales', 'luisa@sierra.com', 'Ferretería', '321654987', 'Av. Central 987'),
('20512345682', 'Destornilladores SAC', 'Carlos Pérez', 'carlos@destornilladores.com', 'Herramientas', '123987456', 'Calle Sur 345'),
('20512345683', 'Alicates y más SRL', 'Ana Torres', 'ana@alicates.com', 'Ferretería', '456123789', 'Av. Las Flores 123'),
('20512345684', 'Cables Industriales S.A.', 'Pablo Reyes', 'pablo@cables.com', 'Electricidad', '789654123', 'Calle Industrial 654'),
('20512345685', 'Guantes Protec SAC', 'Luz García', 'luz@guantes.com', 'Seguridad', '321987654', 'Av. Proteccion 321'),
('20512345686', 'Mascarillas del Sur SRL', 'Ricardo Flores', 'ricardo@mascarillas.com', 'Salud', '654321987', 'Calle Sur 654'),
('20512345687', 'Botas Industriales SAC', 'Sofía Vargas', 'sofia@botas.com', 'Seguridad', '789123456', 'Av. Los Andes 789'),
('20512345688', 'Casco Seguro S.A.', 'Miguel Romero', 'miguel@casco.com', 'Seguridad', '123654789', 'Calle Seguridad 123'),
('20512345689', 'Chalecos y más SRL', 'Verónica Salas', 'veronica@chalecos.com', 'Seguridad', '987321654', 'Av. Proteccion 456'),
('20512345690', 'Gafas Protec S.A.', 'Daniel Rojas', 'daniel@gafas.com', 'Seguridad', '654987321', 'Calle Salud 789'),
('20512345691', 'Extintores del Norte SAC', 'Carmen López', 'carmen@extintores.com', 'Seguridad', '321654123', 'Av. Norte 123'),
('20512345692', 'Señales Industriales SRL', 'Raúl Espinoza', 'raul@senales.com', 'Seguridad', '123789456', 'Calle Segura 987'),
('20512345693', 'Conos del Sur SAC', 'Elena Martínez', 'elena@conos.com', 'Seguridad', '987456123', 'Av. Sur 654'),
('20512345694', 'Linternas Industriales SRL', 'Diego Castillo', 'diego@linternas.com', 'Electricidad', '456789321', 'Calle Luz 321'),
('20512345695', 'Medidores del Este S.A.', 'Fernanda López', 'fernanda@medidores.com', 'Electricidad', '789123654', 'Av. Este 654'),
('20512345696', 'Baterías Recargables SAC', 'Javier Delgado', 'javier@baterias.com', 'Electricidad', '654789123', 'Calle Recarga 987'),
('20512345697', 'Primera Ayuda SAC', 'Clara Huamán', 'clara@primerosauxilios.com', 'Salud', '321987123', 'Av. Emergencia 321');
*/

-- Poblamiento de la tabla Orden_de_compra
COPY orden_de_compra FROM 'C:\Users\fabia\Desktop\DBD-2024-2\bd242-grupo3\7\datos\Orden_de_Compra.csv' DELIMITER ',' CSV HEADER;

/*
INSERT INTO Orden_de_compra (Id_orden_compra, Fecha_emision, Fecha_posible_entrega, Descripcion, Cantidad, Prioridad, Precio_unitario, Descripcion_product, RUC_proveedor, Id_empleado) VALUES
(1, '2024-01-10', '2024-01-20', 'Orden para martillos', 30, 'Alta', 15.50, 'Martillos de acero', '20512345678', 1),
(2, '2024-02-05', '2024-02-15', 'Orden para taladros', 50, 'Media', 45.00, 'Taladros industriales', '20512345679', 2),
(3, '2024-03-01', '2024-03-11', 'Orden para clavos', 100, 'Alta', 0.10, 'Clavos de 3 pulgadas', '20512345680', 3),
(4, '2024-03-25', '2024-04-05', 'Orden para sierras', 25, 'Baja', 12.00, 'Sierras manuales', '20512345681', 4),
(5, '2024-04-10', '2024-04-20', 'Orden para destornilladores', 70, 'Alta', 5.00, 'Destornilladores planos', '20512345682', 5),
(6, '2024-05-01', '2024-05-11', 'Orden para alicates', 60, 'Media', 8.50, 'Alicates multifunción', '20512345683', 6),
(7, '2024-06-05', '2024-06-15', 'Orden para cables', 80, 'Alta', 1.20, 'Cables de cobre', '20512345684', 7),
(8, '2024-07-10', '2024-07-20', 'Orden para guantes', 200, 'Media', 3.00, 'Guantes de seguridad', '20512345685', 8),
(9, '2024-08-15', '2024-08-25', 'Orden para mascarillas', 150, 'Alta', 0.80, 'Mascarillas protectoras', '20512345686', 9),
(10, '2024-09-01', '2024-09-11', 'Orden para botas de seguridad', 40, 'Baja', 25.00, 'Botas de seguridad industrial', '20512345687', 10),
(11, '2024-09-18', '2024-09-28', 'Orden para cascos', 100, 'Alta', 15.00, 'Cascos de protección', '20512345688', 11),
(12, '2024-10-05', '2024-10-15', 'Orden para chalecos reflectantes', 120, 'Media', 7.50, 'Chalecos reflectantes', '20512345689', 12),
(13, '2024-10-20', '2024-10-30', 'Orden para gafas de protección', 180, 'Alta', 3.20, 'Gafas de seguridad', '20512345690', 13),
(14, '2024-11-02', '2024-11-12', 'Orden para extinguidores', 35, 'Media', 40.00, 'Extinguidores ABC', '20512345691', 14),
(15, '2024-11-20', '2024-11-30', 'Orden para señales de emergencia', 15, 'Baja', 12.00, 'Señales de emergencia', '20512345692', 15),
(16, '2024-12-01', '2024-12-11', 'Orden para conos de seguridad', 50, 'Alta', 10.00, 'Conos reflectantes', '20512345693', 16),
(17, '2024-12-15', '2024-12-25', 'Orden para linternas', 60, 'Media', 20.00, 'Linternas de alta potencia', '20512345694', 17),
(18, '2025-01-10', '2025-01-20', 'Orden para medidores eléctricos', 45, 'Alta', 50.00, 'Medidores de tensión', '20512345695', 18),
(19, '2025-01-25', '2025-02-05', 'Orden para baterías recargables', 100, 'Media', 5.00, 'Baterías de larga duración', '20512345696', 19),
(20, '2025-02-05', '2025-02-15', 'Orden para kits de primeros auxilios', 25, 'Alta', 30.00, 'Kits médicos', '20512345697', 20);
*/

-- Registro_compra_recursos
INSERT INTO Registro_compra_herramienta (id_reg_herramienta, fecha_registro, Descripcion, Cantidad, id_almacen, id_orden_compra, id_herramienta) VALUES
(1, '2024-01-15', 'Compra de martillos', 30, 1, 1, 1),
(2, '2024-02-20', 'Compra de taladros', 50, 2, 2, 2),
(3, '2024-03-22', 'Compra de clavos', 100, 4, 3, 3),
(4, '2024-04-11', 'Compra de sierras', 25, 4, 4, 4),
(5, '2024-05-18', 'Compra de destornilladores', 70, 2, 5, 5),
(6, '2024-06-02', 'Compra de alicates', 60, 1, 6, 6),
(7, '2024-07-15', 'Compra de cables', 80, 2, 7, 7),
(8, '2024-08-10', 'Compra de guantes', 200, 1, 8, 8),
(9, '2024-09-05', 'Compra de mascarillas', 150, 2, 9, 9),
(10, '2024-09-20', 'Compra de botas de seguridad', 40, 1, 10, 10),
(11, '2024-10-01', 'Compra de cascos', 100, 2, 11, 11),
(12, '2024-10-15', 'Compra de chalecos reflectantes', 120, 4, 12, 12),
(13, '2024-11-03', 'Compra de gafas de protección', 180, 4, 13, 13),
(14, '2024-11-18', 'Compra de extinguidores', 35, 2, 14, 14),
(15, '2024-12-01', 'Compra de señales de emergencia', 15, 1, 15, 15),
(16, '2024-12-15', 'Compra de conos de seguridad', 50, 4, 16, 16),
(17, '2025-01-07', 'Compra de linternas', 60, 1, 17, 17),
(18, '2025-01-22', 'Compra de medidores eléctricos', 45, 2, 18, 18),
(19, '2025-02-05', 'Compra de baterías recargables', 100, 4, 19, 19),
(20, '2025-02-18', 'Compra de kits de primeros auxilios', 25, 2, 20, 20);

-- Estado
INSERT INTO Estado_pedido (id_estado_pedido, nombre_estado) VALUES
(1, 'Aprobado'),
(2, 'En proceso de envío'),
(3, 'Enviado');

-- Pedido
INSERT INTO Pedido (Id_pedido, Cant_pedid, Fecha, Est_inactividad, Id_herramienta, Id_empleado, Id_estado_pedido) VALUES
(1, 30, '2024-01-10', '1', 1, 1, 1),
(2, 50, '2024-02-05', '1', 2, 2, 2),
(3, 100, '2024-03-01', '0', 3, 3, 3),
(4, 25, '2024-03-25', '1', 4, 4, 1),
(5, 70, '2024-04-10', '1', 5, 5, 2),
(6, 60, '2024-05-01', '0', 6, 6, 3),
(7, 80, '2024-06-05', '1', 7, 7, 1),
(8, 200, '2024-07-10', '1', 8, 8, 2),
(9, 150, '2024-08-15', '0', 9, 9, 3),
(10, 40, '2024-09-01', '1', 10, 10, 1),
(11, 100, '2024-09-18', '1', 11, 11, 2),
(12, 120, '2024-10-05', '0', 12, 12, 3),
(13, 180, '2024-10-20', '1', 13, 13, 1),
(14, 35, '2024-11-02', '1', 14, 14, 2),
(15, 15, '2024-11-20', '0', 15, 15, 3),
(16, 50, '2024-12-01', '1', 16, 16, 1),
(17, 60, '2024-12-15', '0', 17, 17, 2),
(18, 45, '2025-01-10', '1', 18, 18, 3),
(19, 100, '2025-01-25', '0', 19, 19, 1),
(20, 25, '2025-02-05', '1', 20, 20, 2);



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

