package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleActividadRequest;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ActividadRepository implements IActividadRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Actividad_empleado> findAll() {
        String SQL = "SELECT * FROM Actividad_empleado";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Actividad_empleado.class));
    }

    @Override
    public List<Map<String,Object>> findX(int id_orden, int limit, int offset){
        String sql = "SELECT LPAD(a.id_actvempleado::TEXT, 4, '0'), emp.nombre, CONCAT('ES-',LPAD(es.id_equipo_soporte::TEXT, 4, '0')), a.fecha_inicio " +
                "FROM Actividad_empleado a " +
                "INNER JOIN Empleado emp " +
                "ON emp.id_empleado = a.id_empleado " +
                "LEFT JOIN Equipo_de_Soporte es " +
                "ON es.id_equipo_soporte = a.id_equipo_soporte " +
                "WHERE a.id_orden = ? " +
                "LIMIT ? " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,id_orden, limit, offset);
    }

    @Override
    public int conteoActividad(){
        String sql = "SELECT COUNT(*) FROM Actividad_empleado;";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public DetalleActividadRequest detalleActividad(int id_actvempleado){
        String sql = "SELECT CONCAT('AC-', LPAD(act.id_actvempleado::TEXT, 4, '0')), act.id_estado, CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')), act.nombre_actv, emp.id_empleado, emp.nombre, act.fecha_inicio, act.fecha_fin, es.id_equipo_soporte, es.nombre_equipo_soporte, o.id_orden, act.descripcion_actv " +
                "FROM Actividad_empleado act " +
                "INNER JOIN Empleado emp ON emp.id_empleado = act.id_empleado " +
                "INNER JOIN Orden_de_trabajo o ON o.id_orden = act.id_orden " +
                "INNER JOIN Mantenimiento m ON m.id_orden = o.id_orden " +
                "INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant " +
                "LEFT JOIN Equipo_de_Soporte es ON es.id_equipo_soporte = act.id_equipo_soporte " +

                "WHERE act.id_actvempleado = ?";
        return jdbcTemplate.queryForObject(sql, DetalleActividadRequest.class, id_actvempleado);
    }

    @Override
    public void guardarActividad(Actividad_empleado act){
        String sql = "UPDATE Actividad_Empleado " +
                "SET nombre_actv = ?, id_empleado = ?, id_equipo_soporte = ?, fecha_inicio = ?, fecha_fin = ?, descripcion_actv = ? " +
                "WHERE id_actvempleado = ?;";
        jdbcTemplate.update(sql, act.getNombre_actv(), act.getId_empleado(), act.getId_equipo_soporte(), act.getFecha_inicio(), act.getFecha_fin(), act.getDescripcion_actv(), act.getId_actvempleado());
    }

    @Override
    public void borrarActividad(int id_actvempleado){
        String sql = "DELETE FROM Actividad_empleado " +
                "WHERE id_actvempleado = ?";
        jdbcTemplate.update(sql, id_actvempleado);
    }
}
