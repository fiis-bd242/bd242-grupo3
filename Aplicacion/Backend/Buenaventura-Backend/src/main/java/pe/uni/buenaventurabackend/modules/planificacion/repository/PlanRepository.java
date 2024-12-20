package pe.uni.buenaventurabackend.modules.planificacion.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Notificaciones;
import pe.uni.buenaventurabackend.modules.planificacion.models.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;

import java.time.LocalDate;
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
        String SQL = "SELECT CONCAT('PL-', LPAD(id_plan::TEXT, 4, '0')) AS id_plan " +
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
        String sql = "SELECT MAX(id_plan) FROM Plan_de_mantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoPlanEquipo(){
        String sql = "SELECT MAX(id_equipo_mant) FROM EquipoSXMantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoPlanInsumo(){
        String sql = "SELECT MAX(id_insum_mant) FROM InsumoXMantenimiento ";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoMantenimiento(){
        String sql = "SELECT MAX(id_act_mantto) FROM Mantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public DetallePlanRequest detallePlan(int id_plan) {
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
                "p.descripcion, " + // Suponiendo que este campo ya existe en la tabla Plan_de_mantenimiento

                "(SELECT json_agg(json_build_object('id_equipo_soporte', es.id_equipo_soporte, 'nombre_equipo_soporte', es.nombre_equipo_soporte)) " +
                "FROM EquipoSXMantenimiento esm " +
                "INNER JOIN Equipo_de_Soporte es ON es.id_equipo_soporte = esm.id_equipo_soporte " +
                "WHERE esm.id_act_mantto = m.id_act_mantto) AS listaEquipos, " +

                "(SELECT json_agg(json_build_object('id_insumo', i.id_insumo, 'nombre', i.nombre, 'cantidad', im.cantidad)) " +
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
                detalle.setDescripcion(rs.getString("descripcion"));

                // Convertir lista de equipos
                String listaEquiposJson = rs.getString("listaEquipos");
                if (listaEquiposJson != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        List<EquipoDTO> listaEquipos = objectMapper.readValue(listaEquiposJson, new TypeReference<List<EquipoDTO>>() {});
                        detalle.setListaEquipos(listaEquipos);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error al procesar la lista de equipos: " + e.getMessage(), e);
                    }
                }

                // Convertir lista de insumos
                String listaInsumosJson = rs.getString("listaInsumos");
                if (listaInsumosJson != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        List<InsumoDTO> listaInsumos = objectMapper.readValue(listaInsumosJson, new TypeReference<List<InsumoDTO>>() {});
                        detalle.setListaInsumos(listaInsumos);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error al procesar la lista de insumos: " + e.getMessage(), e);
                    }
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

        System.out.println("PASO A");
        // Eliminación de los equipos de soporte e insumos
        sql = "DELETE FROM EquipoSXMantenimiento " +
                "WHERE id_act_mantto = ?; " +

                "DELETE FROM InsumoXMantenimiento " +
                "WHERE id_act_mantto = ?;";
        jdbcTemplate.update(sql, m.getId_act_mantto(), m.getId_act_mantto());

        int id_equipo_mant = conteoPlanEquipo() +1;
        System.out.println("conteo: " +  id_equipo_mant);
        sql = "INSERT INTO EquipoSXMantenimiento (id_equipo_mant, id_act_mantto, id_equipo_soporte) " +
                "VALUES(?,?,?)";
        for (int i : listaEquipos){
            System.out.println("conteo: " +  id_equipo_mant);
            jdbcTemplate.update(sql, id_equipo_mant, m.getId_act_mantto(), i);
            id_equipo_mant++;
        }

        int id_insum_mant = conteoPlanInsumo() +1;
        sql = "INSERT INTO InsumoXMantenimiento (id_insum_mant, cantidad, id_act_mantto, id_insumo) " +
                "VALUES (?, ?, ?, ?);";
        for (InsumoDTO insumoDTO : listaInsumos){
            System.out.println(insumoDTO);
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
    public List<Map<String,Object>> findXbyDate(int limit, int offset, LocalDate fecha_inicio_programado){
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
    public void reservaEquipo(List<Integer> listaEquipos){
        String sql = "UPDATE Equipo_de_soporte " +
                "SET id_disponibilidad = 1 " +
                "WHERE id_equipo_soporte = ? ";
        for (int id_equipo_soporte: listaEquipos){
            jdbcTemplate.update(sql, id_equipo_soporte);
        }
    }

    @Override
    public void reservaInsumo(int id_insumo, int cantidad, int id_usuario){
        String sql = "SELECT cantidad FROM Insumo " +
                "WHERE id_insumo = ?";
        int cant_actual = jdbcTemplate.queryForObject(sql, Integer.class, id_insumo);
        if(cant_actual < cantidad){
            return;
        }
        int cant_nueva = cant_actual - cantidad;
        String sql1 = "UPDATE Insumo " +
                "SET cantidad = ? " +
                "WHERE id_insumo = ?";
        jdbcTemplate.update(sql1,cant_nueva, id_insumo);

        String sql_cant = "SELECT cantidad FROM InsumoXAlmacen " +
                "WHERE id_insumo = ? AND cantidad > 0 LIMIT 1";
        String sql_id = "SELECT id_insum_alm FROM InsumoXAlmacen " +
                "WHERE id_insumo = ? AND cantidad > 0 LIMIT 1";
        String sql_alm = "UPDATE InsumoXAlmacen " +
                "SET cantidad = ? " +
                "WHERE id_insum_alm = ?";

        int id_insum_alm = 0;
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

        sql = "SELECT COUNT (*) FROM Reserva";
        int id_reserva = jdbcTemplate.queryForObject(sql, Integer.class) +1;

        String sql2 = "INSERT INTO Reserva (id_reserva, fecha, hora, id_estado_reserva, id_empleado)" +
                "VALUES (?,current_date,current_time,1,?)";
        jdbcTemplate.update(sql2, id_reserva, id_usuario);

        sql = "SELECT COUNT(*) FROM Detalle_reserva";
        int id_detalle = jdbcTemplate.queryForObject(sql, Integer.class) +1;



        sql = "INSERT INTO Detalle_reserva (id_detalle, cant_reserv, id_insumo, id_reserva) " +
                "VALUES (?, ?, ?, ?) ";
        jdbcTemplate.update(sql, id_detalle, cantidad, id_insumo, id_reserva);
    }

    @Override
    public int conteoNotificaciones(){
        String sql = "SELECT MAX(id_notificacion) FROM Notificaciones";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<InsumoDTO> listaInsumos(){
        String sql = "SELECT id_insumo, nombre, cantidad FROM Insumo " +
                "ORDER BY id_insumo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            InsumoDTO detalle = new InsumoDTO();
            detalle.setId_insumo(rs.getInt("id_insumo"));
            detalle.setNombre(rs.getString("nombre"));
            return detalle;
        });
    }

    @Override
    public List<EquipoDTO> listaEquipos(){
        String sql = "SELECT id_equipo_soporte, nombre_equipo_soporte " +
                "FROM Equipo_de_soporte es " +
                "ORDER BY id_equipo_soporte";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EquipoDTO detalle = new EquipoDTO();
            detalle.setId_equipo_soporte(rs.getInt("id_equipo_soporte"));
            detalle.setNombre_equipo_soporte(rs.getString("nombre_equipo_soporte"));
            return detalle;
        });
    }

    @Override
    public List<String> listaMaquinas(){
        String sql = "SELECT CONCAT('MQ-',LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina " +
                "FROM Maquina m " +
                "ORDER BY m.id_maquina";
        return jdbcTemplate.queryForList(sql, String.class);
    }

}
