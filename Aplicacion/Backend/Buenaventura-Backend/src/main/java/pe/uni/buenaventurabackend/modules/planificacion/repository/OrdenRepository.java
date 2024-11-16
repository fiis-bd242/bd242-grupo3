package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrdenRepository implements IOrdenRepository {
    private final JdbcTemplate jdbcTemplate;
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

}
