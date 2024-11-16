package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import lombok.RequiredArgsConstructor;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Resultado;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResultadoRepository implements IResultadoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Resultado> obtenerResultados(String nombreMarca) {
        String sql = """
            SELECT nombre || ' - ' || descripcion AS Resultado
            FROM Mantenimiento AS man
            INNER JOIN Maquina AS maq ON man.Id_maquina = maq.id_maquina
            INNER JOIN Marca_maquina AS mm ON maq.id_marca_maquina = mm.id_marca_maquina
            INNER JOIN plan_de_mantenimiento AS pm ON man.id_plan = pm.id_plan
            INNER JOIN empleado AS em ON em.id_empleado = pm.empleado_asigna
            WHERE Fecha_inicio_programado = CURRENT_DATE AND nombre_marca LIKE ?
        """;

        return jdbcTemplate.query(
                sql,
                new Object[]{"%" + nombreMarca + "%"},
                (rs, rowNum) -> new Resultado(rs.getString("Resultado"))
        );
    }
}

