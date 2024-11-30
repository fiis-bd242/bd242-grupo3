-- Índices
--- Planificacion
DROP INDEX IF EXISTS IDX_Maquina_mantto;
CREATE INDEX IDX_Maquina_mantto ON Mantenimiento(id_maquina);

DROP INDEX IF EXISTS IDX_Fecha_mantto;
CREATE INDEX IDX_Fecha_mantto ON Mantenimiento(fecha_inicio_programado);

--- Control
--- Equipos de soporte
--- IPERC
--- Insumos
DROP INDEX IF EXISTS idx_reserva_fecha_hora;
CREATE INDEX idx_reserva_fecha_hora ON Reserva(Fecha, Hora);

DROP INDEX IF EXISTS idx_estado_reserva;
CREATE INDEX idx_estado_reserva ON Estado_reserva(Id_estado_reserva);

--- Seguridad
--- Reportes
DROP INDEX IF EXISTS idx_registros_fecha;
CREATE INDEX idx_registros_fecha ON Registro(Fecha_inicial, fecha_registro);

DROP INDEX IF EXISTS idx_reportes_fecha;
CREATE INDEX idx_reportes_fecha ON reportes (fecha_reporte);



-- Vistas
--- Planificacion
DROP VIEW IF EXISTS vista_equipos_soporte;
CREATE VIEW vista_equipos_soporte AS
SELECT 
    esm.id_act_mantto,
    json_agg(
        json_build_object(
            'id_equipo_soporte', es.id_equipo_soporte,
            'nombre_equipo_soporte', es.nombre_equipo_soporte
        )
    ) AS listaEquipos
FROM EquipoSXMantenimiento esm
INNER JOIN Equipo_de_Soporte es ON es.id_equipo_soporte = esm.id_equipo_soporte
GROUP BY esm.id_act_mantto;

DROP VIEW IF EXISTS vista_insumos;
CREATE VIEW vista_insumos AS
SELECT 
    im.id_act_mantto,
    json_agg(
        json_build_object(
            'id_insumo', i.id_insumo,
            'nombre', i.nombre,
            'cantidad', im.cantidad
        )
    ) AS listaInsumos
FROM InsumoXMantenimiento im
INNER JOIN Insumo i ON i.id_insumo = im.id_insumo
GROUP BY im.id_act_mantto;

DROP VIEW IF EXISTS vista_plan_mantenimiento;
CREATE VIEW vista_plan_mantenimiento AS
SELECT 
    CONCAT('PL-', LPAD(p.id_plan::TEXT, 4, '0')) AS id_plan,
    tm.nombre_tipo_mant,
    CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina,
    creador.nombre AS creador,
    est.estado,
    p.fecha_plan,
    m.fecha_inicio_programado,
    m.fecha_fin_programado,
    responsable.nombre AS responsable,
    crit.nivel AS criticidad,
    CONCAT('OT-', LPAD(o.id_orden::TEXT, 4, '0')) AS id_orden,
    p.descripcion,
    equipos.listaEquipos,
    insumos.listaInsumos
FROM Plan_de_mantenimiento p
INNER JOIN Mantenimiento m ON m.id_plan = p.id_plan
INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant
INNER JOIN Empleado creador ON creador.id_empleado = p.empleado_asigna
INNER JOIN Orden_de_trabajo o ON o.id_orden = m.id_orden
INNER JOIN Actividad_empleado act ON act.id_orden = o.id_orden
INNER JOIN Empleado responsable ON responsable.id_empleado = act.id_empleado
INNER JOIN Estado_mantto est ON m.id_estado = est.id_estado
INNER JOIN Criticidad crit ON crit.id_criticidad = p.id_criticidad
LEFT JOIN vista_equipos_soporte equipos ON equipos.id_act_mantto = m.id_act_mantto
LEFT JOIN vista_insumos insumos ON insumos.id_act_mantto = m.id_act_mantto
WHERE act.nombre_actv = 'Responsable' AND m.id_estado != 8
ORDER BY p.id_plan;

--- Control
--- Equipos de soporte
--- IPERC
--- Insumos
--- Seguridad
--- Reportes


-- Secuencias
--- Planificacion
--- Control
--- Equipos de soporte
--- IPERC
--- Insumos
--- Seguridad
--- Reportes


-- Otros
--- Planificacion
--- Control
--- Equipos de soporte
--- IPERC
--- Insumos
--- Seguridad
--- Reportes