package pe.uni.buenaventurabackend.modules.seguridad.repository;

import pe.uni.buenaventurabackend.modules.seguridad.models.NotificacionAdministrador;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Repository
public class NotificacionAdministradorRepository implements INotificacionAdministradorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createNotificacion(NotificacionAdministrador notificacion) {
        String sql = "INSERT INTO Notificacion_Administrador (Id_Notificacion,Id_Administrador, Tipo_Evento, Fecha_Notificacion, Estado_Notificacion, Mensaje_Notificacion, Prioridad, Id_Sesion, Id_autenticacion) " +
                "VALUES ((SELECT MAX(Id_Notificacion) FROM Notificacion_Administrador) + 1,?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                notificacion.getIdAdministrador(),
                notificacion.getTipoEvento(),
                notificacion.getFechaHoraNotificacion(),
                notificacion.getEstadoNotificacion(),
                notificacion.getMensajeNotificacion(),
                notificacion.getPrioridad(),
                notificacion.getIdSesion(),
                notificacion.getIdAutenticacion()
        );
    }



    @Override
    public List<NotificacionAdministrador> getAllNotificaciones(int offset) {

        // Consulta SQL con paginación
        String sql = "SELECT * FROM Notificacion_Administrador " +
                "LIMIT 7 OFFSET ?";

        // Calcular el valor del OFFSET
        int offsetValue = (offset - 1) * 7;

        // Ejecutar la consulta con el parámetro de OFFSET
        return jdbcTemplate.query(sql, new Object[]{offsetValue}, (rs, rowNum) -> {
            NotificacionAdministrador notificacion = new NotificacionAdministrador();
            notificacion.setIdNotificacion(rs.getInt("Id_Notificacion"));
            notificacion.setIdAdministrador(rs.getInt("Id_Administrador"));
            notificacion.setTipoEvento(rs.getString("Tipo_Evento"));
            notificacion.setFechaHoraNotificacion(rs.getDate("Fecha_Notificacion"));
            notificacion.setEstadoNotificacion(rs.getString("Estado_Notificacion"));
            notificacion.setMensajeNotificacion(rs.getString("Mensaje_Notificacion"));
            notificacion.setPrioridad(rs.getString("Prioridad"));
            notificacion.setIdSesion(rs.getInt("Id_Sesion"));
            notificacion.setIdAutenticacion(rs.getInt("Id_autenticacion"));
            return notificacion;
        });
    }

    @Override
    public NotificacionAdministrador findById(int idNotificacion) {
        String sql = "SELECT * FROM Notificacion_Administrador WHERE Id_Notificacion = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            NotificacionAdministrador notificacion = new NotificacionAdministrador();
            notificacion.setIdNotificacion(rs.getInt("Id_Notificacion"));
            notificacion.setIdAdministrador(rs.getInt("Id_Administrador"));
            notificacion.setTipoEvento(rs.getString("Tipo_Evento"));
            // Ajuste aquí: usaremos rs.getDate para obtener solo la fecha
            Date fechaHora = rs.getDate("Fecha_Hora_Notificacion");
            notificacion.setFechaHoraNotificacion(fechaHora);  // Al ser solo fecha, podemos usar java.sql.Date
            notificacion.setEstadoNotificacion(rs.getString("Estado_Notificacion"));
            notificacion.setMensajeNotificacion(rs.getString("Mensaje_Notificacion"));
            notificacion.setPrioridad(rs.getString("Prioridad"));
            notificacion.setIdSesion(rs.getInt("Id_Sesion"));
            notificacion.setIdAutenticacion(rs.getInt("Id_autenticacion"));
            return notificacion;
        });
    }

    @Override
    public void updateNotificacion(NotificacionAdministrador notificacion) {
        String sql = "UPDATE Notificacion_Administrador SET Tipo_Evento = ?, Fecha_Hora_Notificacion = ?, Estado_Notificacion = ?, " +
                "Mensaje_Notificacion = ?, Prioridad = ?, Id_Sesion = ?, Id_autenticacion = ? WHERE Id_Notificacion = ?";
        jdbcTemplate.update(sql, notificacion.getTipoEvento(), notificacion.getFechaHoraNotificacion(),
                notificacion.getEstadoNotificacion(), notificacion.getMensajeNotificacion(), notificacion.getPrioridad(),
                notificacion.getIdSesion(), notificacion.getIdAutenticacion(), notificacion.getIdNotificacion());
    }

    @Override
    public void deleteNotificacion(int idNotificacion) {
        String sql = "DELETE FROM Notificacion_Administrador WHERE Id_Notificacion = ?";
        jdbcTemplate.update(sql, idNotificacion);
    }
}
