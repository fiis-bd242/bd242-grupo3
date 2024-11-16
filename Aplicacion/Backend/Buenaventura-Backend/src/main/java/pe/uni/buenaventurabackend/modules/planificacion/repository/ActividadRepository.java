package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;

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
    public List<Map<String,Object>> find9(int id_orden, int offset){
        String sql = "SELECT LPAD(a.id_actvempleado::TEXT, 4, '0'), emp.nombre, CONCAT('ES-',LPAD(es.id_equipo_soporte::TEXT, 4, '0')), a.fecha_inicio " +
                "FROM Actividad_empleado a " +
                "INNER JOIN Empleado emp " +
                "ON emp.id_empleado = a.id_empleado " +
                "LEFT JOIN Equipo_de_Soporte es " +
                "ON es.id_equipo_soporte = a.id_equipo_soporte " +
                "WHERE a.id_orden = ? " +
                "LIMIT 9 " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,id_orden, offset);
    }
}
