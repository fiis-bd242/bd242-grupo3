package pe.uni.buenaventurabackend.modules.seguridad.repository;

import pe.uni.buenaventurabackend.modules.seguridad.models.NotificacionAdministrador;
import java.util.List;

public interface INotificacionAdministradorRepository {

    void createNotificacion(NotificacionAdministrador notificacion);

    List<NotificacionAdministrador> findAll();

    NotificacionAdministrador findById(int idNotificacion);

    void updateNotificacion(NotificacionAdministrador notificacion);

    void deleteNotificacion(int idNotificacion);
}
