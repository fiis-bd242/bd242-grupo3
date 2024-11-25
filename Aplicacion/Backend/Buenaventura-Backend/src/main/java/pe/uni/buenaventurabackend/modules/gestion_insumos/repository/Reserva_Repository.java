package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_estado_DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class Reserva_Repository implements IReserva_Repository  {
    private final JdbcTemplate jdbcTemplate;

    public Reserva_Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertarReserva(int idReserva, int idEmpleado) {
        String sql = """
                INSERT INTO Reserva (Id_reserva, Fecha, Hora, Id_estado_reserva, Id_empleado)
                VALUES (?, ?, ?, 0, ?);
                """;

        // Obtén la fecha y la hora del sistema
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Ejecuta la consulta de inserción
        jdbcTemplate.update(sql, idReserva, fechaActual, horaActual, idEmpleado);
    }
    @Override
    public List<Reserva_estado_DTO> obtenerReservasConEstadosMantenimiento(int idEmpleado, int limite, int offset) {
        String sql = "SELECT r.id_reserva, e.nombre_estado " +
                "FROM Reserva r " +
                "JOIN Estado_reserva e ON r.Id_estado_reserva = e.Id_estado_reserva " +
                "WHERE r.id_Empleado = ? " +
                "LIMIT ? OFFSET ?";

        // Ejecutamos la consulta SQL y devolvemos los resultados mapeados
        return jdbcTemplate.query(sql, new Object[]{idEmpleado, limite, offset}, (rs, rowNum) -> new Reserva_estado_DTO(
                rs.getInt("id_reserva"),
                rs.getString("nombre_estado")
        ));
    }
    @Override
    public Reserva_DTO buscarReservaPorId(Integer idReserva) {
        String sql = """
                SELECT Id_reserva, Fecha, Hora
                FROM Reserva
                WHERE Id_reserva = ?
                """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToReservaDTO(rs), idReserva);
    }

    private Reserva_DTO mapRowToReservaDTO(ResultSet rs) throws SQLException {
        Integer idReserva = rs.getInt("Id_reserva");
        LocalDate fecha = rs.getDate("Fecha").toLocalDate();
        LocalTime hora = rs.getTime("Hora").toLocalTime();

        return new Reserva_DTO(idReserva, fecha, hora);
    }
    @Override
    public int validarNumeroReserva(Integer idReserva) {
        String sql = """
                SELECT COALESCE((SELECT Id_reserva FROM Reserva WHERE Id_reserva = ?), -1) AS resultado
                """;

        return jdbcTemplate.queryForObject(sql, Integer.class, idReserva);
    }
    @Override
    public int actualizarEstadoReserva(Integer idReserva, Integer idEstado) {
        String sql = """
                UPDATE Reserva
                SET Id_estado_reserva = ?
                WHERE Id_reserva = ?
                """;

        return jdbcTemplate.update(sql, idEstado, idReserva);
    }
    @Override
    public List<Reserva_estado_DTO> obtenerReservasConEstadoAlmacen(int limite, int offset) {
        String sql = """
                SELECT r.Id_reserva, e.Nombre_estado
                FROM Reserva r
                JOIN Estado_reserva e ON e.Id_estado_reserva = r.Id_estado_reserva
                WHERE r.Id_estado_reserva <> 0 AND r.Id_estado_reserva <> 3
                ORDER BY Fecha, Hora
                LIMIT ? OFFSET ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToReservaEstadoDTO(rs), limite, offset);
    }
    @Override
    public int obtenerNumeroDeReservas() {
        String sql = "SELECT COUNT(*) FROM Reserva";

        // Usamos Optional para evitar el NPE si la consulta devuelve null
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, Integer.class))
                .orElse(0); // Si es null, devolvemos 0
    }
    private Reserva_estado_DTO mapRowToReservaEstadoDTO(ResultSet rs) throws SQLException {
        int idReserva = rs.getInt("Id_reserva");
        String nombreEstado = rs.getString("Nombre_estado");

        return new Reserva_estado_DTO(idReserva, nombreEstado);
    }

}
