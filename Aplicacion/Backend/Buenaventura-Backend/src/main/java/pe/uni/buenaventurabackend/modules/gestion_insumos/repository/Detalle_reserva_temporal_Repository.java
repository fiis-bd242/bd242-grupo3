package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;
import org.springframework.jdbc.core.RowMapper;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_temporalDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Detalle_reserva_temporal_Repository implements IDetalle_reserva_temporal_Repository {

    private final JdbcTemplate jdbcTemplate;

    public Detalle_reserva_temporal_Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void truncateTable() {
        String sql = "TRUNCATE TABLE Detalle_reserva_temporal RESTART IDENTITY";
        jdbcTemplate.execute(sql);
    }
    @Override
    public void insertDetalleReservaTemporal(int cantReserv, int idInsumo, int idReserva) {
        String sql = "INSERT INTO Detalle_reserva_temporal (Cant_reserv, Id_insumo, Id_reserva) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, cantReserv, idInsumo, idReserva);
    }
    @Override
    public List<Detalle_reserva_temporalDTO> findAllDetallesConInsumos() {
        String sql = """
                SELECT i.Nombre as nombreInsumo, d.Cant_reserv as cantidadReservada
                FROM Detalle_reserva_temporal as d
                JOIN Insumo as i ON d.Id_insumo = i.Id_insumo
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String nombreInsumo = rs.getString("nombreInsumo");
            int cantidadReservada = rs.getInt("cantidadReservada");
            return new Detalle_reserva_temporalDTO(nombreInsumo, cantidadReservada);
        });
    }
}
