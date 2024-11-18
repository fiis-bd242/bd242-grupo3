package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleOrdenRequest;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrdenRepository implements IOrdenRepository {
    private final JdbcTemplate jdbcTemplate;
    private final IActividadRepository iActividadRepository;

    @Override
    public List<Orden_de_trabajo> findAll() {
        String SQL = "SELECT CONCAT('OT-', LPAD(id_orden::TEXT, 4, '0')) " +
                "FROM Orden_de_trabajo;";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Orden_de_trabajo.class));
    }

    @Override
    public List<Map<String,Object>> find10(int offset){
        String sql = "SELECT LPAD(p.id_plan::TEXT, 4, '0'), CONCAT('MQ-',LPAD(m.id_maquina::TEXT, 4, '0')), tm.nombre_tipo_mant, m.fecha_inicio_programado " +
                "FROM Plan_de_Mantenimiento p " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Orden_de_trabajo o " +
                "ON o.id_orden = m.id_orden " +
                "WHERE o.visible = 'Y' " +
                "LIMIT 10 " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,offset);
    }

    @Override
    public int conteoOrden(){
        String sql = "SELECT COUNT(*) FROM Orden_de_Trabajo;";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public void nuevaOrden(Orden_de_trabajo orden){
        String sql = "INSERT INTO Orden_de_trabajo (id_orden,descripcion,fecha_orden, visible) " +
                "VALUES (?,?,?,?);";
        jdbcTemplate.update(sql,orden.getId_orden(),orden.getDescripcion(), orden.getFecha_orden(), orden.getVisible());
    }

    @Override
    public void nuevaOrdenMant(int id_orden, int id_plan){
        String sql = "UPDATE Mantenimiento " +
                "SET id_orden = ? " +
                "WHERE id_plan = ?;";
        jdbcTemplate.update(sql,id_orden,id_plan);
    }

    @Override
    public void nuevaOrdenActv(Actividad_empleado act, boolean responsable){
        String sql = "";
        if(responsable){
            sql = "INSERT INTO Actividad_empleado (id_actvempleado, nombre_actv, descripcion_actv, fecha_inicio, fecha_fin, id_equipo_soporte, id_empleado, id_estado, id_orden) " +
                    "SELECT " +
                    "?, " +
                    "'Responsable', " +
                    "'Es responsable de la ejecución completa del mantenimiento y su supervisión', " +
                    "m.fecha_inicio_programado, " +
                    "m.fecha_fin_programado, " +
                    "null, " +
                    "?, " +
                    "1, " +
                    "? " +
                    "FROM Mantenimiento m " +
                    "WHERE id_plan = ?;";
            jdbcTemplate.update(sql, act.getId_actvempleado(), act.getId_empleado(), act.getId_orden());
        }
        else {
            sql = "INSERT INTO Actividad_empleado (id_actvempleado, nombre_actv, descripcion_actv, fecha_inicio, fecha_fin, id_equipo_soporte, id_empleado, id_estado, id_orden) " +
                    "VALUES (?, null, null, null, null, null, ?, 1, ?);";
            jdbcTemplate.update(sql, act.getId_actvempleado(), act.getId_empleado(), act.getId_orden());
        }
    }

    @Override
    public List<String> findAllVisible(){
        String sql = "SELECT CONCAT('OT-', LPAD(id_orden::TEXT, 4, '0')) " +
                "FROM Orden_de_trabajo " +
                "WHERE visible = 'Y';";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    public DetalleOrdenRequest detalleOrden(int id_orden){
        String sql = "SELECT " +
                "CONCAT('OT-', LPAD(o.id_orden::TEXT, 4, '0')) AS orden_trabajo, " +
                "o.descripcion, " +
                "est.estado, " +
                "CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')) AS maquina, " +
                "o.fecha_orden, " +
                "tm.nombre_tipo_mant, " +
                "m.fecha_inicio_programado, " +
                "m.fecha_fin_programado, " +
                "responsable.nombre AS responsable, " +
                "CONCAT('PL-', LPAD(p.id_plan::TEXT, 4, '0')) AS plan_mantenimiento, " +

                "string_agg(DISTINCT CONCAT(emp.id_empleado, '-', emp.nombre), ', ') AS lista_empleados  " +

                "FROM Orden_de_Trabajo o " +

                "INNER JOIN Actividad_empleado act ON act.id_orden = o.id_orden " +
                "INNER JOIN Empleado emp ON emp.id_empleado = act.id_empleado " +
                "INNER JOIN Mantenimiento m ON m.id_plan = o.id_orden " +
                "INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Plan_de_mantenimiento p ON p.id_plan = m.id_plan " +
                "INNER JOIN Empleado responsable ON p.empleado_asigna = responsable.id_empleado " +
                "INNER JOIN Estado_mantto est ON est.id_estado = m.id_estado " +
                "WHERE o.id_orden = ? " +
                "GROUP BY " +
                "o.id_orden, est.estado, m.id_maquina, o.fecha_orden, tm.nombre_tipo_mant, " +
                "m.fecha_inicio_programado, m.fecha_fin_programado, responsable.nombre, p.id_plan " +
                "ORDER BY o.id_orden;";
        return jdbcTemplate.queryForObject(sql, DetalleOrdenRequest.class, id_orden);
    }

    @Override
    public void guardarOrden(int id_orden, String descripcion, int id_plan, List<Integer> listaEmpleados){
        String sql = "UPDATE Orden_de_Trabajo " +
                "SET descripcion = ?, " +
                "WHERE id_orden = ?; " +

                "UPDATE Mantenimiento " +
                "SET id_plan = ? " +
                "WHERE id_orden = ?;";
        jdbcTemplate.update(sql, descripcion, id_orden, id_plan, id_orden);

        sql = "DELETE FROM Actividad_Empleado " +
                "WHERE id_orden = ?;";
        jdbcTemplate.update(sql, id_orden);

        int id_actvempleado = iActividadRepository.conteoActividad() +1;
        sql = "INSERT INTO Actividad_Empleado (id_empleado, id_actvempleado)" +
                "VALUES (?, ?);";
        for(int i : listaEmpleados){
            jdbcTemplate.update(sql, i, id_actvempleado);
            id_actvempleado++;
        }

    }
}
