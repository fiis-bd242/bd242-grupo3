package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrdenRepository implements IOrdenRepository {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Orden_de_trabajo> findAll() {
        String SQL = "SELECT * FROM Orden_de_trabajo";
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
}
