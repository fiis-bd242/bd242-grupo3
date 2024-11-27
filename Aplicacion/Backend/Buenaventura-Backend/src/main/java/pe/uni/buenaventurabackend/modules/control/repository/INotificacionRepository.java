package pe.uni.buenaventurabackend.modules.control.repository;

import pe.uni.buenaventurabackend.modules.control.models.Notificacion;
import pe.uni.buenaventurabackend.modules.planificacion.models.Notificaciones;

public interface INotificacionRepository {

    void aceptarNotificacion(Notificacion noti);

    void rechazarNotificacion(Notificacion noti);
}
