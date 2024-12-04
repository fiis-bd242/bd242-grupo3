-- Índices

--- Planificacion
DROP INDEX IF EXISTS IDX_Maquina_mantto;
CREATE INDEX IDX_Maquina_mantto ON Mantenimiento(id_maquina);

DROP INDEX IF EXISTS IDX_Fecha_mantto;
CREATE INDEX IDX_Fecha_mantto ON Mantenimiento(fecha_inicio_programado);

--- Control
DROP INDEX IF EXISTS IDX_Fecha_program;
CREATE INDEX IDX_Fecha_program
    ON Mantenimiento(fecha_inicio_programado);

--- Equipos de soporte
DROP INDEX IF EXISTS idx_disponibilidad_equipo;
CREATE INDEX idx_disponibilidad_equipo ON Equipo_de_soporte(id_disponibilidad);

DROP INDEX IF EXISTS idx_estado_pedido;
CREATE INDEX idx_estado_pedido ON pedido_compra(id_estado_pedido);

--- IPERC
--- Insumos
DROP INDEX IF EXISTS idx_reserva_fecha_hora;
CREATE INDEX idx_reserva_fecha_hora ON Reserva(Fecha, Hora);

DROP INDEX IF EXISTS idx_estado_reserva;
CREATE INDEX idx_estado_reserva ON Estado_reserva(Id_estado_reserva);

--- Seguridad
CREATE INDEX id_email_contacto_empleado ON Empleado(Email_contacto);
DROP INDEX id_email_contacto_empleado;

CREATE INDEX idx_fecha_estado ON Notificacion_Administrador(Fecha_Notificacion, Estado_Notificacion);
DROP INDEX IF EXISTS idx_fecha_estado;



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

--- Control del mantenimiento

CREATE VIEW vista_trabajos_mantto AS
	SELECT 
        LPAD(p.id_plan::TEXT, 4, '0') AS id_plan,
        CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina,
        tm.nombre_tipo_mant,
        c.nivel,
        m.fecha_inicio_programado,
        em.estado
    FROM Plan_de_Mantenimiento p
    INNER JOIN Mantenimiento m
        ON m.id_plan = p.id_plan
    INNER JOIN estado_mantto em
        ON em.id_estado = m.id_estado
    INNER JOIN Tipo_mantenimiento tm
        ON tm.id_tipo_mant = m.id_tipo_mant
    INNER JOIN Criticidad c
        ON c.id_criticidad = p.id_criticidad
    ORDER BY p.id_plan;

--- Gestión de insumos

DROP VIEW IF EXISTS vista_reservas;
CREATE VIEW vista_reservas AS
SELECT id_reserva, fecha, hora, id_estado_reserva
FROM reserva;

--- Equipos de soporte
DROP VIEW IF EXISTS vista_detalle_equipo;
CREATE VIEW vista_detalle_equipo AS
SELECT
	es.id_equipo_soporte AS ID,
	es.nombre_equipo_soporte AS Nombre,
	tes.nombre_tipo AS Tipo,
	ees.nombre_estado AS Estado,
	des.nombre_disponibilidad AS Disponibilidad,
	es.horas_uso AS "horas de uso",
	es.descripcion AS descripcion
FROM equipo_de_soporte es
INNER JOIN tipo_equipo_soporte tes
ON es.id_tipo = tes.id_tipo
INNER JOIN estado_equipo_soporte ees
ON es.id_estado = ees.id_estado
INNER JOIN disponibilidad_equipo_soporte des
ON es.id_disponibilidad = des.id_disponibilidad
ORDER BY es.id_equipo_soporte

DROP VIEW IF EXISTS vista_detalle_pedido_equipo;
CREATE VIEW vista_detalle_pedido_equipo AS
SELECT
	pc.id_pedido_compra AS id,
	e.nombre AS nombre,
	tu.tipo_urgencia AS urgencia,
	ep.nombre_estado_pedido AS estado,
	pc.descripción AS descripcion
FROM Pedido_compra pc
INNER JOIN tipo_urgencia tu
ON pc.id_urgencia = tu.id_urgencia
INNER JOIN Estado_pedido ep
ON pc.id_estado_pedido = ep.id_estado_pedido
INNER JOIN tipo_producto tp
ON tp.id_tipo_producto = pc.id_tipo_producto
INNER JOIN empleado e
ON e.id_empleado = pc.id_empleado
WHERE tp.nombre_tipo_producto ILIKE 'Equipos de Soporte'
ORDER BY pc.id_pedido_compra

--- IPERC

--- Seguridad
DROP VIEW IF EXISTS vista_empleados_cargo_rol;
CREATE VIEW vista_empleados_cargo_rol AS
SELECT 
    e.Id_empleado,
    e.Nombre AS "Nombre_Empleado",
    e.DNI,
    e.Email_contacto AS "Correo_Empleado",
    e.Numero_contacto AS "Numero_Contacto",
    e.Sexo,
    c.Nombre_cargo AS "Cargo",
    a.Nombre_acceso AS "Rol"
FROM 
    Empleado e
JOIN 
    Cargo_empleado c ON e.Id_cargo = c.Id_cargo
JOIN 
    Acceso_empleado a ON e.Id_acceso = a.Id_acceso;


--- Reportes
CREATE VIEW vista_reportes_historial AS
SELECT 
    r.Id_reporte AS "ID_Reporte",
    r.Fecha_reporte AS "Fecha_Reporte",
    er.nombre_estado AS "Estado_Reporte",
    e_jefe.Nombre AS "Jefe",
    e_supervisor.Nombre AS "Supervisor",
    reg.Id_registro AS "ID_Registro",
    reg.Fecha_registro AS "Fecha_Registro",
    reg.Fecha_inicial AS "Fecha_Inicial",
    reg.Calificacion AS "Calificacion",
    reg.Observaciones AS "Observaciones",
    nt.Asunto AS "Notificacion_Asunto",
    nt.mensaje AS "Notificacion_Mensaje",
    nt.fecha_notificacion AS "Fecha_Notificacion"
FROM 
    Reportes r
JOIN 
    Estado_Reporte er ON r.id_estado_reporte = er.id_estado_reporte
LEFT JOIN 
    Empleado e_jefe ON r.id_jefe = e_jefe.Id_empleado
LEFT JOIN 
    Empleado e_supervisor ON r.Id_supervisor = e_supervisor.Id_empleado
LEFT JOIN 
    Registro reg ON reg.Id_empleado = e_supervisor.Id_empleado
LEFT JOIN 
    Notificaciones nt ON nt.Id_reporte = r.Id_reporte;

-- Secuencias

CREATE SEQUENCE IF NOT EXISTS notificaciones_id_notificacion_seq;


-- Otros Objetos de BD

WITH cte_mantenimiento AS (
    SELECT 
        m.id_plan,
        m.id_maquina,
        m.id_tipo_mant,
        m.id_estado,
        m.fecha_inicio_programado
    FROM Mantenimiento m
    WHERE m.fecha_inicio_programado = DATE('2023-08-15')
)
SELECT 
    LPAD(p.id_plan::TEXT, 4, '0') AS id_plan,
    CONCAT('MQ-', LPAD(cte.id_maquina::TEXT, 4, '0')) AS id_maquina,
    tm.nombre_tipo_mant,
    c.nivel,
    cte.fecha_inicio_programado,
    em.estado
FROM cte_mantenimiento cte
INNER JOIN Plan_de_Mantenimiento p
    ON cte.id_plan = p.id_plan
INNER JOIN estado_mantto em
    ON cte.id_estado = em.id_estado
INNER JOIN Tipo_mantenimiento tm
    ON cte.id_tipo_mant = tm.id_tipo_mant
INNER JOIN Criticidad c
    ON c.id_criticidad = p.id_criticidad
ORDER BY p.id_plan;
