package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.EquipoSXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Mantenimiento;
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

    @Override
    public void nuevoPlanMant(Plan_de_mantenimiento plan, Mantenimiento mantenimiento){
        String sql = "INSERT INTO Plan_de_mantenimiento (id_plan,descripcion,fecha_plan,empleado_asigna,id_criticidad) " +
                "VALUES (?,?,DATE('?'),?,?); ";
        jdbcTemplate.update(sql, plan.getId_plan(), plan.getDescripcion(), plan.getFecha_plan(), plan.getEmpleado_asigna(), plan.getId_criticidad());

        sql = "INSERT INTO Mantenimiento (id_act_mantto,fecha_inicio_programado,fecha_fin_programado,id_orden,id_plan,id_tipo_mant,id_maquina,id_estado) " +
                "VALUES (?,DATE('?'),DATE('?'),null,?,?,?,1);";
        jdbcTemplate.update(sql, mantenimiento.getId_act_mantto(), mantenimiento.getFecha_inicio_programado(), mantenimiento.getFecha_fin_programado(), mantenimiento.getId_plan(), mantenimiento.getId_tipo_mant());
    }

    @Override
    public void nuevoPlanEquipo(EquipoSXMantenimiento equipoSXMantenimiento){
        String sql = "INSERT INTO EquipoSXMantenimiento (id_equipo_mant, id_act_mantto, id_equipo_soporte) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(sql,equipoSXMantenimiento.getId_equipo_mant(), equipoSXMantenimiento.getId_act_mantto(),equipoSXMantenimiento.getId_equipo_soporte());
    }

    @Override
    public void nuevoPlanInsumo(InsumoXMantenimiento insumoXMantenimiento){
        String sql = "INSERT INTO InsumoXMantenimiento (id_insum_mant, cantidad, id_act_mantto, id_insumo) " +
                "VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, insumoXMantenimiento.getId_insum_mant(), insumoXMantenimiento.getCantidad(), insumoXMantenimiento.getId_act_mantto(), insumoXMantenimiento.getId_insumo());
    }

    @Override
    public int conteoPlan(){
        String sql = "SELECT COUNT(*) FROM Plan_de_mantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoPlanEquipo(){
        String sql = "SELECT COUNT(*) FROM EquipoSXMantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoPlanInsumo(){
        String sql = "SELECT COUNT(*) FROM InsumoXMantenimiento ";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int conteoMantenimiento(){
        String sql = "SELECT COUNT(*) FROM Mantenimiento;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
