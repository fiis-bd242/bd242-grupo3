package pe.uni.buenaventurabackend.modules.control.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TrabajosRepository implements ITrabajosRepository{
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> findX(int limit, int offset){
        String sql = "SELECT LPAD(p.id_plan::TEXT, 4, '0') AS id_plan," +
                " CONCAT('MQ-',LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina," +
                " tm.nombre_tipo_mant," +
                " c.nivel," +
                " m.fecha_inicio_programado, " +
                " em.estado " +
                "FROM Plan_de_Mantenimiento p " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = p.id_plan " +
                "INNER JOIN estado_mantto em " +
                "ON em.id_estado = m.id_estado " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Criticidad c " +
                "ON c.id_criticidad = p.id_criticidad " +
                "ORDER BY p.id_plan " +
                "LIMIT 6 " +
                "OFFSET 103-1;";
        return jdbcTemplate.queryForList(sql,limit,offset);
    }
}
