package pe.uni.buenaventurabackend.modules.iperc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pe.uni.buenaventurabackend.modules.iperc.models.requests.IpercEditablesRequest;

import java.util.List;

public class IpercRepository {
    private JdbcTemplate jdbcTemplate;
    public List<IpercEditablesRequest> listaIperc(){
        String sql = "SELECT " +
                "p.descripcion_proceso AS \"Proceso\", " +
                "i.fecha_act_sup AS \"Última Fecha de Actualización\" " +
                "FROM " +
                "informe_iperc i " +
                "JOIN " +
                "proceso p ON i.id_proceso = p.id_proceso " +
                "ORDER BY " +
                "i.fecha_act_sup DESC;";
        return jdbcTemplate.queryForList(sql, IpercEditablesRequest.class);
    }
}
