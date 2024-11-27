package pe.uni.buenaventurabackend.modules.control.service;

public interface INotificacionService {

    void aceptarNotificacion(int id_usuario, int id_plan);

    void rechazarNotificacion(int id_usuario, int id_plan);
}
