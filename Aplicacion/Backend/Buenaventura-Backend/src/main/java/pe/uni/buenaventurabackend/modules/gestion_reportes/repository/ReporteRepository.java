package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ReporteRepository implements IReporte{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Reporte> findAll() {
        String SQL = "SELECT * FROM reportes";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Reporte.class));
    }
}
