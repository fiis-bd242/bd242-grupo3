package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;

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
    public List<Map<String,Object>> find10(int offset){
        String sql = "SELECT p.id_plan, m.id_maquina, tm.nombre_tipo_mant, c.nivel, m.fecha_inicio_programado " +
                "FROM Plan_de_Mantenimiento p " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Criticidad c " +
                "ON c.id_criticidad = p.id_criticidad " +
                "LIMIT 10 " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,offset);
    }
}
