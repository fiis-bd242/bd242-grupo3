package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificacionRepository {
    private final JdbcTemplate jdbcTemplate;

    public void insertarNotificacion(int Id_registro, String Asunto, String Mensaje, int Id_remitente) {
        String sql = """
            INSERT INTO Notificaciones (
                id_notificacion, Fecha_notificacion, Asunto, Mensaje, Id_remitente, Id_destinatario, Id_registro, Id_reporte, id_tipo
            ) VALUES (
                (SELECT COALESCE(MAX(id_notificacion), 0) + 1 FROM Notificaciones),
                CURRENT_TIMESTAMP,
                ?, ?, ?,
                (SELECT Id_empleado FROM Registro WHERE Id_registro = ?),
                ?, NULL, 1
            );
        """;

        jdbcTemplate.update(sql, Asunto, Mensaje, Id_remitente, Id_registro, Id_registro);
    }
}
