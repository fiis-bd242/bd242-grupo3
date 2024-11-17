package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Notificacion;
import pe.uni.buenaventurabackend.modules.planificacion.models.EquipoSXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.GuardarPlanRequest;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PlanRepository implements IPlanRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Plan_de_mantenimiento> findAll() {
        String SQL = "SELECT * FROM Plan_de_mantenimiento";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Plan_de_mantenimiento.class));
    }

    @Override
    public List<Map<String,Object>> findX(int limit, int offset){
        String sql = "SELECT p.id_plan, m.id_maquina, tm.nombre_tipo_mant, c.nivel, m.fecha_inicio_programado " +
                "FROM Plan_de_Mantenimiento p " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Criticidad c " +
                "ON c.id_criticidad = p.id_criticidad " +
                "LIMIT ? " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,limit,offset);
    }

    @Override
    public void nuevoPlanMant(Plan_de_mantenimiento plan, Mantenimiento mantenimiento){
        String sql = "INSERT INTO Plan_de_mantenimiento (id_plan,descripcion,fecha_plan,empleado_asigna,id_criticidad) " +
                "VALUES (?,?,?,?,?); ";
        jdbcTemplate.update(sql, plan.getId_plan(), plan.getDescripcion(), plan.getFecha_plan(), plan.getEmpleado_asigna(), plan.getId_criticidad());

        sql = "INSERT INTO Mantenimiento (id_act_mantto,fecha_inicio_programado,fecha_fin_programado,id_orden,id_plan,id_tipo_mant,id_maquina,id_estado) " +
                "VALUES (?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql, mantenimiento.getId_act_mantto(), mantenimiento.getFecha_inicio_programado(), mantenimiento.getFecha_fin_programado(), mantenimiento.getId_orden(), mantenimiento.getId_plan(), mantenimiento.getId_tipo_mant(), mantenimiento.getId_maquina(), mantenimiento.getId_estado());
    }

    @Override
    public void nuevoPlanEquipo(EquipoSXMantenimiento equipoSXMantenimiento){
        String sql = "INSERT INTO EquipoSXMantenimiento (id_equipo_mant, id_act_mantto, id_equipo_soporte) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(sql,equipoSXMantenimiento.getId_equipo_mant(), equipoSXMantenimiento.getId_act_mantto(),equipoSXMantenimiento.getId_equipo_soporte());
    }

    @Override
    public void nuevoPlanInsumo(InsumoXMantenimiento insumoXMantenimiento){
        String sql = "INSERT INTO InsumoXMantenimiento (id_insum_mant, cantidad, id_act_mantto, id_insumo) " +
                "VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, insumoXMantenimiento.getId_insum_mant(), insumoXMantenimiento.getCantidad(), insumoXMantenimiento.getId_act_mantto(), insumoXMantenimiento.getId_insumo());
    }

    @Override
    public int conteoPlan(){
        String sql = "SELECT COUNT(*) FROM Plan_de_mantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoPlanEquipo(){
        String sql = "SELECT COUNT(*) FROM EquipoSXMantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoPlanInsumo(){
        String sql = "SELECT COUNT(*) FROM InsumoXMantenimiento ";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoMantenimiento(){
        String sql = "SELECT COUNT(*) FROM Mantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public DetallePlanRequest detallePlan(int id_plan){
        String sql = "SELECT CONCAT('PL-', LPAD(p.id_plan::TEXT, 4, '0')), " +
                "       tm.nombre_tipo_mant, " +
                "       CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')), " +
                "       creador.nombre AS creador, " +
                "       est.estado, " +
                "       p.fecha_plan, " +
                "       m.fecha_inicio_programado, " +
                "       m.fecha_fin_programado, " +
                "       responsable.nombre AS responsable, " +
                "       crit.nivel, " +
                "       CONCAT('OT-', LPAD(o.id_orden::TEXT, 4, '0')), " +
                "       -- Lista de equipos de soporte " +
                "       (SELECT string_agg(DISTINCT es.nombre_equipo_soporte, ',') " +
                "        FROM EquipoSXMantenimiento esm " +
                "        INNER JOIN Equipo_de_Soporte es ON es.id_equipo_soporte = esm.id_equipo_soporte " +
                "        WHERE esm.id_act_mantto = m.id_act_mantto) AS equipos_soporte, " +
                "       -- Lista de insumos con cantidad " +
                "       (SELECT string_agg(DISTINCT i.nombre || ' (Cantidad: ' || im.cantidad || ')', ', ') " +
                "        FROM InsumoXMantenimiento im " +
                "        INNER JOIN Insumo i ON i.id_insumo = im.id_insumo " +
                "        WHERE im.id_act_mantto = m.id_act_mantto) AS insumos_utilizados " +
                "FROM Plan_de_mantenimiento p " +
                "INNER JOIN Mantenimiento m ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Empleado creador ON creador.id_empleado = p.empleado_asigna " +
                "INNER JOIN Orden_de_trabajo o ON o.id_orden = m.id_orden " +
                "INNER JOIN Actividad_empleado act ON act.id_orden = o.id_orden " +
                "INNER JOIN Empleado responsable ON responsable.id_empleado = act.id_empleado " +
                "INNER JOIN Estado_mantto est ON m.id_estado = est.id_estado " +
                "INNER JOIN Criticidad crit ON crit.id_criticidad = p.id_criticidad " +
                "WHERE act.nombre_actv = 'Responsable' AND p.id_plan = <1> AND m.id_estado != 8;";
        return jdbcTemplate.queryForObject(sql, DetallePlanRequest.class);
    }

    @Override
    public void envioNotificacion(Notificacion noti, int id_plan){
        String sql = "INSERT INTO Notificaciones (id_notificacion, asunto, mensaje, fecha_notificacion, id_remitente, id_destinatario, id_registro, id_reporte, id_tipo) " +
                "SELECT ?,'Nuevo plan por aceptar',CONCAT('Nuevo plan de mantenimiento disponible con fecha ' , ?), ?, ?, e.id_empleado, null, null, 2 " +
                "FROM Empleado e " +
                "INNER JOIN Actividad_empleado act " +
                "ON act.id_empleado = e.id_empleado " +
                "INNER JOIN Orden_de_trabajo o " +
                "ON o.id_orden = act.id_orden " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_orden = o.id_orden " +
                "INNER JOIN Plan_de_Mantenimiento p " +
                "ON p.id_plan = m.id_plan " +
                " WHERE p.id_plan = ? AND act.nombre_actv = 'Responsable';";
        jdbcTemplate.update(sql, noti.getId_notificacion(), noti.getFecha_notificacion(), noti.getFecha_notificacion(), noti.getId_remitente(), id_plan);

        sql = "UPDATE Mantenimiento " +
                "SET id_estado = 2 " +
                "WHERE id_plan = ?;";
        jdbcTemplate.update(sql, id_plan);
    }

    @Override
    public void guardarPlan(GuardarPlanRequest req){
        String sql = "UPDATE Plan_de_Mantenimiento\n" +
                "\tSET descripcion = <2>, id_criticidad = <3>\n" +
                "\tWHERE id_plan = <1>;" +
                "UPDATE Mantenimiento\n" +
                "\tSET id_orden = <4>, id_tipo_mant = <5>, id_maquina = <6>, fecha_inicio_programado = DATE('<7>'), fecha_fin_programado = DATE('<8>')\n" +
                "\tWHERE id_plan = <1>;\n" +
                "\n" +
                "\t--- Restablecer fechas del responsable ---\n" +
                "\tUPDATE Actividad_empleado\n" +
                "\tSET fecha_inicio = DATE('<7>'), fecha_fin = DATE('<8>')\n" +
                "\tWHERE a.nombre_actv IN (\n" +
                "\t\tSELECT a.nombre_actv FROM Actividad_empleado a\n" +
                "\t\tINNER JOIN Orden_de_trabajo o ON a.id_orden = o.id_orden\n" +
                "\t\tINNER JOIN Mantenimiento m ON m.id_orden = o.id_orden\n" +
                "\t\tINNER JOIN Plan_de_mantenimiento p ON p.id_plan = m.id_plan\n" +
                "\t\tWHERE p.id_plan = <1> AND a.nombre_actv = 'Responsable'\n" +
                "\t);";
        jdbcTemplate.update(sql);

        // Eliminación de los equipos de soporte e insumos
        sql = "DELETE FROM EquipoSXMantenimiento " +
                "WHERE id_act_mantto IN ( " +
                "SELECT m.id_act_mantto " +
                "FROM Mantenimiento m " +
                "WHERE m.id_plan = <1>; " +
                "DELETE FROM InsumoXMantenimiento " +
                "WHERE id_act_mantto IN ( " +
                "SELECT m.id_act_mantto " +
                "FROM Mantenimiento m " +
                "WHERE m.id_plan = <1>;";
        jdbcTemplate.update(sql);

        for (int i : req.getListaEquipos()){

        }
    }
}
