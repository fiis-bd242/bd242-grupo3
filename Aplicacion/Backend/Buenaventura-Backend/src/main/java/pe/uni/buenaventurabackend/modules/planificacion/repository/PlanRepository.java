package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Notificaciones;
import pe.uni.buenaventurabackend.modules.planificacion.models.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PlanRepository implements IPlanRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<String> findAll() {
        String SQL = "SELECT CONCAT('OT-', LPAD(id_plan::TEXT, 4, '0')) AS id_plan " +
                "FROM plan_de_mantenimiento";
        return jdbcTemplate.queryForList(SQL, String.class);
    }

    @Override
    public List<Map<String,Object>> findX(int limit, int offset){
        String sql = "SELECT LPAD(p.id_plan::TEXT, 4, '0') AS id_plan," +
                " CONCAT('MQ-',LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina," +
                " tm.nombre_tipo_mant," +
                " c.nivel," +
                " m.fecha_inicio_programado " +
                "FROM Plan_de_Mantenimiento p " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Criticidad c " +
                "ON c.id_criticidad = p.id_criticidad " +
                "ORDER BY p.id_plan " +
                "LIMIT ? " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,limit,offset);
    }

    @Override
    public void nuevoPlanMant(Plan_de_mantenimiento plan, Mantenimiento mantenimiento){
        String sql = "INSERT INTO Plan_de_mantenimiento (id_plan,descripcion,fecha_plan,empleado_asigna,id_criticidad) " +
                "VALUES (?,?,current_date,?,?); ";
        jdbcTemplate.update(sql, plan.getId_plan(), plan.getDescripcion(), plan.getEmpleado_asigna(), plan.getId_criticidad());

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
        String sql = "SELECT CONCAT('PL-', LPAD(p.id_plan::TEXT, 4, '0')) AS id_plan, " +
                "tm.nombre_tipo_mant, " +
                "CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina, " +
                "creador.nombre AS creador, " +
                "est.estado, " +
                "p.fecha_plan, " +
                "m.fecha_inicio_programado, " +
                "m.fecha_fin_programado, " +
                "responsable.nombre AS responsable, " +
                "crit.nivel AS criticidad, " +
                "CONCAT('OT-', LPAD(o.id_orden::TEXT, 4, '0')) AS id_orden, " +

                "(SELECT string_agg(DISTINCT es.nombre_equipo_soporte, ',') " +
                "FROM EquipoSXMantenimiento esm " +
                "INNER JOIN Equipo_de_Soporte es ON es.id_equipo_soporte = esm.id_equipo_soporte " +
                "WHERE esm.id_act_mantto = m.id_act_mantto) AS listaEquipos, " +

                "(SELECT string_agg(DISTINCT i.nombre || ' (Cantidad: ' || im.cantidad || ')', ', ') " +
                "FROM InsumoXMantenimiento im " +
                "INNER JOIN Insumo i ON i.id_insumo = im.id_insumo " +
                "WHERE im.id_act_mantto = m.id_act_mantto) AS listaInsumos " +
                "FROM Plan_de_mantenimiento p " +
                "INNER JOIN Mantenimiento m ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Empleado creador ON creador.id_empleado = p.empleado_asigna " +
                "INNER JOIN Orden_de_trabajo o ON o.id_orden = m.id_orden " +
                "INNER JOIN Actividad_empleado act ON act.id_orden = o.id_orden " +
                "INNER JOIN Empleado responsable ON responsable.id_empleado = act.id_empleado " +
                "INNER JOIN Estado_mantto est ON m.id_estado = est.id_estado " +
                "INNER JOIN Criticidad crit ON crit.id_criticidad = p.id_criticidad " +
                "WHERE act.nombre_actv = 'Responsable' AND p.id_plan = ? AND m.id_estado != 8;";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                DetallePlanRequest detalle = new DetallePlanRequest();
                detalle.setId_plan(rs.getString("id_plan"));
                detalle.setNombre_tipo_mant(rs.getString("nombre_tipo_mant"));
                detalle.setId_maquina(rs.getString("id_maquina"));
                detalle.setCreador(rs.getString("creador"));
                detalle.setEstado(rs.getString("estado"));
                detalle.setFecha_plan(rs.getString("fecha_plan"));
                detalle.setFecha_inicio_programado(rs.getString("fecha_inicio_programado"));
                detalle.setFecha_fin_programado(rs.getString("fecha_fin_programado"));
                detalle.setResponsable(rs.getString("responsable"));
                detalle.setCriticidad(rs.getString("criticidad"));
                detalle.setId_orden(rs.getString("id_orden"));

                // Convertir lista de equipos y lista de insumos en arrays separados
                String listaEquipos = rs.getString("listaEquipos");
                if (listaEquipos != null) {
                    detalle.setListaEquipos(List.of(listaEquipos.split(",")));
                }

                String listaInsumos = rs.getString("listaInsumos");
                if (listaInsumos != null) {
                    List<InsumoDTON> insumos = Arrays.stream(listaInsumos.split(","))
                            .map(insumo -> {
                                String[] parts = insumo.split("\\(Cantidad: ");
                                String nombre_insumo = parts[0].trim();
                                int cantidad = Integer.parseInt(parts[1].replace(")", "").trim());
                                return new InsumoDTON(nombre_insumo, cantidad);
                            })
                            .collect(Collectors.toList());
                    detalle.setListaInsumos(insumos);
                }

                return detalle;
            }
            return null;
        }, id_plan);
    }

    @Override
    public void envioNotificacion(Notificaciones noti){
        String sql = "INSERT INTO Notificaciones (id_notificacion, asunto, mensaje, fecha_notificacion, id_remitente, id_destinatario, id_registro, id_reporte, id_tipo) " +
                "SELECT ?,'Nuevo plan por aceptar',CONCAT('Nuevo plan de mantenimiento disponible con fecha ' , current_date), current_date, ?, e.id_empleado, null, null, 2 " +
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
        jdbcTemplate.update(sql, noti.getId_notificacion(), noti.getId_remitente(), noti.getId_plan());

        sql = "UPDATE Mantenimiento " +
                "SET id_estado = 2 " +
                "WHERE id_plan = ?;";
        jdbcTemplate.update(sql, noti.getId_plan());
    }

    @Override
    public void guardarPlan(int id_plan, Plan_de_mantenimiento p, Mantenimiento m, List<Integer> listaEquipos, List<InsumoDTO> listaInsumos){
        String sql = "UPDATE Plan_de_Mantenimiento " +
                "SET descripcion = ?, id_criticidad = ? " +
                "WHERE id_plan = ?;" +
                "UPDATE Mantenimiento " +
                "SET id_orden = ?, id_tipo_mant = ?, id_maquina = ?, fecha_inicio_programado = ?, fecha_fin_programado = ? " +
                "WHERE id_plan = ?; ";
        jdbcTemplate.update(sql, p.getDescripcion(),
                p.getId_criticidad(), id_plan,
                m.getId_orden(), m.getId_tipo_mant(),
                m.getId_maquina(), m.getFecha_inicio_programado(),
                m.getFecha_fin_programado(), id_plan);

        sql = "SELECT id_act_mantto FROM Mantenimiento " +
                "WHERE id_plan = ?";
        m.setId_act_mantto(jdbcTemplate.queryForObject(sql, Integer.class, id_plan));

        sql = "UPDATE Actividad_empleado a " +
                "SET fecha_inicio = ?, fecha_fin = ? " +
                "WHERE a.nombre_actv IN ( " +
                "SELECT a.nombre_actv FROM Actividad_empleado a " +
                "INNER JOIN Orden_de_trabajo o ON a.id_orden = o.id_orden " +
                "INNER JOIN Mantenimiento m ON m.id_orden = o.id_orden " +
                "INNER JOIN Plan_de_mantenimiento p ON p.id_plan = m.id_plan " +
                "WHERE p.id_plan = ? AND a.nombre_actv = 'Responsable');";
        jdbcTemplate.update(sql, m.getFecha_inicio_programado(),
                m.getFecha_fin_programado(), id_plan);

        // Eliminación de los equipos de soporte e insumos
        sql = "DELETE FROM EquipoSXMantenimiento " +
                "WHERE id_act_mantto = ?; " +

                "DELETE FROM InsumoXMantenimiento " +
                "WHERE id_act_mantto = ?;";
        jdbcTemplate.update(sql, m.getId_act_mantto(), m.getId_act_mantto());

        int id_equipo_mant = conteoPlanEquipo() +1;
        sql = "INSERT INTO EquipoSXMantenimiento (id_equipo_mant, id_act_mantto, id_equipo_soporte) " +
                "VALUES(?,?,?)";
        for (int i : listaEquipos){
            jdbcTemplate.update(sql, id_equipo_mant, m.getId_act_mantto(), i);
            id_equipo_mant++;
        }

        int id_insum_mant = conteoPlanInsumo() +1;
        sql = "INSERT INTO InsumoXMantenimiento (id_insum_mant, cantidad, id_act_mantto, id_insumo)\n" +
                "VALUES (?, ?, ?, ?);";
        for (InsumoDTO insumoDTO : listaInsumos){
            jdbcTemplate.update(sql, id_insum_mant, insumoDTO.getCantidad(), m.getId_act_mantto(), insumoDTO.getId_insumo());
            id_insum_mant++;
        }

    }

    @Override
    public void borrarPlan(int id_plan){
        String sql = "UPDATE Orden_de_trabajo " +
                "SET visible = 'N' " +
                "WHERE id_orden IN ( " +
                "SELECT m.id_orden FROM Mantenimiento m " +
                "WHERE m.id_plan = ?); " +

                "UPDATE Mantenimiento " +
                "SET id_estado = 8 " +
                "WHERE id_plan = ?;";
        jdbcTemplate.update(sql, id_plan, id_plan);
    }

    @Override
    public List<Map<String,Object>> findXbyMachine(int limit, int offset, int id_maquina){
        String sql = "SELECT LPAD(p.id_plan::TEXT, 4, '0') AS id_plan," +
                " CONCAT('MQ-',LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina," +
                " tm.nombre_tipo_mant," +
                " c.nivel," +
                " m.fecha_inicio_programado " +
                "FROM Plan_de_Mantenimiento p " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Criticidad c " +
                "ON c.id_criticidad = p.id_criticidad " +
                "WHERE m.id_maquina = ? " +
                "ORDER BY p.id_plan " +
                "LIMIT ? " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,id_maquina,limit,offset);
    }

    @Override
    public List<Map<String,Object>> findXbyDate(int limit, int offset, Date fecha_inicio_programado){
        String sql = "SELECT LPAD(p.id_plan::TEXT, 4, '0') AS id_plan," +
                " CONCAT('MQ-',LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina," +
                " tm.nombre_tipo_mant," +
                " c.nivel," +
                " m.fecha_inicio_programado " +
                "FROM Plan_de_Mantenimiento p " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Criticidad c " +
                "ON c.id_criticidad = p.id_criticidad " +
                "WHERE m.fecha_inicio_programado = ? " +
                "ORDER BY p.id_plan " +
                "LIMIT ? " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,fecha_inicio_programado,limit,offset);
    }

    @Override
    public void reservaEquipo(int id_equipo_soporte){
        String sql = "UPDATE Equipo_de_soporte " +
                "SET id_disponibilidad = 1 " +
                "WHERE id_equipo_soporte = ?";
        jdbcTemplate.update(sql, id_equipo_soporte);
    }

    @Override
    public void reservaInsumo(int id_insumo, int cantidad){
        String sql = "SELECT cantidad FROM Insumo " +
                "WHERE id_insumo = ?";
        int cant_actual = jdbcTemplate.queryForObject(sql, Integer.class, id_insumo);
        if(cant_actual < cantidad){
            return;
        }

        sql = "UPDATE Insumo " +
                "SET cantidad = ? " +
                "WHERE id_insumo = ?";
        jdbcTemplate.update(sql, cantidad-cant_actual, id_insumo);

        String sql_cant = "SELECT cantidad FROM InsumoXAlmacen " +
                "WHERE id_insumo = ? AND cantidad > 0 LIMIT 1";
        String sql_id = "SELECT id_insum_alm FROM InsumoXAlmacen " +
                "WHERE id_insumo = ? AND cantidad > 0 LIMIT 1";
        String sql_alm = "UPDATE InsumoXAlmacen " +
                "SET cantidad = ? " +
                "WHERE id_insum_alm = ?";

        int id_insum_alm;
        while (cantidad > 0){
            cant_actual = jdbcTemplate.queryForObject(sql_cant, Integer.class, id_insumo);
            id_insum_alm = jdbcTemplate.queryForObject(sql_id, Integer.class, id_insumo);
            if(cantidad >= cant_actual){
                jdbcTemplate.update(sql_alm, cantidad - cant_actual, id_insum_alm);
                cantidad = cantidad - cant_actual;

            }
            else{
                jdbcTemplate.update(sql_alm, cant_actual - cantidad, id_insum_alm);
                cantidad = 0;
            }
        }
        jdbcTemplate.queryForObject(sql, Integer.class, id_insumo);
    }

    @Override
    public int conteoNotificaciones(){
        String sql = "SELECT COUNT(*) FROM Notificaciones";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<String> listaInsumos(){
        String sql = "SELECT nombre FROM Insumo";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    public List<Integer> listaEquipos(){
        String sql = "SELECT id_equipo_soporte FROM Equipo_de_soporte";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }
}
