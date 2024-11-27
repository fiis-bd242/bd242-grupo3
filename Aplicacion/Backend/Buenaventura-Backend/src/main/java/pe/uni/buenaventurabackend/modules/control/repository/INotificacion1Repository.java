package pe.uni.buenaventurabackend.modules.control.repository;

import pe.uni.buenaventurabackend.modules.control.models.Notificacion1;

public interface INotificacion1Repository {

    void aceptarNotificacion(Notificacion1 noti);

    void rechazarNotificacion(Notificacion1 noti);
}
