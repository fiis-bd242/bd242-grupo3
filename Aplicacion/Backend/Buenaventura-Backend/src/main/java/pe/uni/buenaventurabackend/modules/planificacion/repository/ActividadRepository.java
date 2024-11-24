package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.EmpleadoDTO;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleActividadRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleOrdenRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String sql = "SELECT LPAD(a.id_actvempleado::TEXT, 4, '0') AS id_actvempleado," +
                " emp.nombre AS empleado," +
                " CONCAT('ES-',LPAD(es.id_equipo_soporte::TEXT, 4, '0')) AS id_equipo_soporte," +
                " a.fecha_inicio AS fecha_inicio " +
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
        String sql = "SELECT CONCAT('AC-', LPAD(act.id_actvempleado::TEXT, 4, '0')) AS id_actvempleado, " +
                "act.id_estado," +
                " CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina," +
                " act.nombre_actv," +
                " emp.id_empleado," +
                " emp.nombre AS nombre_empleado," +
                " act.fecha_inicio AS act_fecha_inicio," +
                " act.fecha_fin AS act_fecha_fin," +
                " es.id_equipo_soporte," +
                " es.nombre_equipo_soporte," +
                " o.id_orden," +
                " act.descripcion_actv " +
                "FROM Actividad_empleado act " +
                "INNER JOIN Empleado emp ON emp.id_empleado = act.id_empleado " +
                "INNER JOIN Orden_de_trabajo o ON o.id_orden = act.id_orden " +
                "INNER JOIN Mantenimiento m ON m.id_orden = o.id_orden " +
                "INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant " +
                "LEFT JOIN Equipo_de_Soporte es ON es.id_equipo_soporte = act.id_equipo_soporte " +

                "WHERE act.id_actvempleado = ?";
        return jdbcTemplate.query(sql, new Object[]{id_actvempleado}, rs -> {
            if (rs.next()) {
                DetalleActividadRequest detalle = new DetalleActividadRequest();
                detalle.setId_actvempleado(rs.getString("id_actvempleado"));
                detalle.setId_estado(rs.getString("id_estado"));
                detalle.setId_maquina(rs.getString("id_maquina"));
                detalle.setNombre_actv(rs.getString("nombre_actv"));
                detalle.setId_empleado(rs.getString("id_empleado"));
                detalle.setNombre_empleado(rs.getString("nombre_empleado"));
                detalle.setAct_fecha_inicio(rs.getString("act_fecha_inicio"));
                detalle.setAct_fecha_fin(rs.getString("act_fecha_fin"));
                detalle.setId_equipo_soporte(rs.getString("id_equipo_soporte"));
                detalle.setNombre_equipo_soporte(rs.getString("nombre_equipo_soporte"));
                detalle.setId_orden(rs.getString("id_orden"));
                detalle.setDescripcion_actv(rs.getString("descripcion_actv"));

                return detalle;
            }
            return null;
        });
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

    @Override
    public int conteoActividadOrden(int id_orden){
        String sql = "SELECT COUNT (*) FROM Actividad_empleado " +
                "WHERE id_orden = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id_orden);
    }
}
