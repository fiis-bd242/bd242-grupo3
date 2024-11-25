package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class Detalle_reserva_Repository implements IDetalle_reserva_Repository{
    private final JdbcTemplate jdbcTemplate;

    public Detalle_reserva_Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void transferirDetallesReservaTemporal() {
        String sql = """
                INSERT INTO Detalle_reserva (Cant_reserv, Id_insumo, Id_reserva)
                SELECT Cant_reserv, Id_insumo, Id_reserva
                FROM Detalle_reserva_temporal
                """;

        // Ejecuta la consulta
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Detalle_reserva_DTO> obtenerDetallesPorReservaId(Integer idReserva) {
        String sql = """
                SELECT i.Nombre, d.Cant_reserv as "Cantidad reservada"
                FROM Detalle_reserva as d
                JOIN Insumo as i ON d.Id_insumo = i.Id_insumo
                JOIN Reserva as r ON r.Id_reserva = d.Id_reserva
                WHERE r.Id_reserva = ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToDetalleReservaDTO(rs), idReserva);
    }

    private Detalle_reserva_DTO mapRowToDetalleReservaDTO(ResultSet rs) throws SQLException {
        String nombreInsumo = rs.getString("Nombre");
        int cantidadReservada = rs.getInt("Cantidad reservada");

        return new Detalle_reserva_DTO(nombreInsumo, cantidadReservada);
    }
}


