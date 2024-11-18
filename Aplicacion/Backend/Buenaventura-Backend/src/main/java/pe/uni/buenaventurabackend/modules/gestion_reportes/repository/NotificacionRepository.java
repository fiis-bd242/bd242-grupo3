package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
@RequiredArgsConstructor
public class NotificacionRepository {
    private final JdbcTemplate jdbcTemplate;

    public void insertarNotificacion(Integer Id_registro, String Asunto, String Mensaje, int Id_remitente, String fecha_Reporte, int id_tipo) {
        Date fecha = null;
        if (fecha_Reporte != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fecha_Reporte);
            } catch (ParseException e) {
                e.printStackTrace();
                fecha = null;
            }
        }
        System.out.println(fecha);
        String sql;
        if (Id_registro != null) {
            sql = """
            INSERT INTO Notificaciones (
                id_notificacion, Fecha_notificacion, Asunto, Mensaje, Id_remitente, Id_destinatario, Id_registro, Id_reporte, id_tipo
            ) VALUES (
                (SELECT COALESCE(MAX(id_notificacion), 0) + 1 FROM Notificaciones),
                CURRENT_TIMESTAMP,
                ?, ?, ?,
                (SELECT Id_empleado FROM Registro WHERE Id_registro = ?),
                ?, (SELECT id_reporte FROM reportes WHERE fecha_reporte = ?), ?
            );
        """;
            jdbcTemplate.update(
                    sql,
                    Asunto, Mensaje, Id_remitente, Id_registro, Id_registro, fecha, id_tipo
            );
        } else {
            sql = """
            INSERT INTO Notificaciones (
                id_notificacion, Fecha_notificacion, Asunto, Mensaje, Id_remitente, Id_destinatario, Id_registro, Id_reporte, id_tipo
            ) VALUES (
                (SELECT COALESCE(MAX(id_notificacion), 0) + 1 FROM Notificaciones),
                CURRENT_TIMESTAMP,
                ?, ?, ?,
                (SELECT id_jefe FROM reportes WHERE fecha_reporte = ?),
                ?, (SELECT id_reporte FROM reportes WHERE fecha_reporte = ?), ?
            );
        """;
            jdbcTemplate.update(
                    sql,
                    Asunto, Mensaje, Id_remitente, fecha, null, fecha, id_tipo
            );
        }
    }

}
