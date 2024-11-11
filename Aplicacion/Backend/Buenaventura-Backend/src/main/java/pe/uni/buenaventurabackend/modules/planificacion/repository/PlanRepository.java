package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlanRepository implements IPlanRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Plan_de_mantenimiento> findAll() {
        String SQL = "SELECT * FROM Plan_de_mantenimiento";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Plan_de_mantenimiento.class));

    }
}
