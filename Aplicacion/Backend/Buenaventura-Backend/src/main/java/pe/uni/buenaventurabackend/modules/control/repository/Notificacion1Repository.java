package pe.uni.buenaventurabackend.modules.control.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.control.models.Notificacion1;


@Repository
@RequiredArgsConstructor
public class Notificacion1Repository implements INotificacion1Repository {
    private final JdbcTemplate jdbcTemplate;

    public void aceptarNotificacion(Notificacion1 noti){
        String sql = "INSERT INTO Notificaciones (id_notificacion, asunto, mensaje, fecha_notificacion, id_remitente, id_destinatario, id_registro, id_reporte, id_tipo) " +
                "SELECT ?,'Plan ha sido aceptado',CONCAT('Nuevo plan de mantenimiento disponible con fecha ' , current_date), current_date, ?, p.empleado_asigna, null, null, 2 " +
                "FROM plan_de_mantenimiento p " +
                " WHERE p.id_plan = ?;";
        jdbcTemplate.update(sql, noti.getId_notificacion(), noti.getId_remitente(), noti.getId_plan());

        sql = "UPDATE Mantenimiento " +
                "SET id_estado = 3 " +
                "WHERE id_plan = ?;";
        jdbcTemplate.update(sql, noti.getId_plan());
    }


    public void rechazarNotificacion(Notificacion1 noti){
        String sql = "INSERT INTO Notificaciones (id_notificacion, asunto, mensaje, fecha_notificacion, id_remitente, id_destinatario, id_registro, id_reporte, id_tipo) " +
                "SELECT ?,'Plan ha sido rechazado',CONCAT('Nuevo plan de mantenimiento disponible con fecha ' , current_date), current_date, ?, p.empleado_asigna, null, null, 2 " +
                "FROM plan_de_mantenimiento p " +
                " WHERE p.id_plan = ?;";
        jdbcTemplate.update(sql, noti.getId_notificacion(), noti.getId_remitente(), noti.getId_plan());

        sql = "UPDATE Mantenimiento " +
                "SET id_estado = 1 " +
                "WHERE id_plan = ?;";
        jdbcTemplate.update(sql, noti.getId_plan());
    }
}
