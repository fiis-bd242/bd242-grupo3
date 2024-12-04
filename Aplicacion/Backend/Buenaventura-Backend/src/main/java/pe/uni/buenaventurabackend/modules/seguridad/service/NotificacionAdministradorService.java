package pe.uni.buenaventurabackend.modules.seguridad.service;

import pe.uni.buenaventurabackend.modules.seguridad.models.NotificacionAdministrador;
import pe.uni.buenaventurabackend.modules.seguridad.repository.INotificacionAdministradorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificacionAdministradorService {

    private final INotificacionAdministradorRepository notificacionRepository;

    public NotificacionAdministradorService(INotificacionAdministradorRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Transactional
    public void createNotificacion(NotificacionAdministrador notificacion) {
        notificacionRepository.createNotificacion(notificacion);
    }

    public List<NotificacionAdministrador> getAllNotificaciones(int offset) {
        return notificacionRepository.getAllNotificaciones(offset);
    }

    public NotificacionAdministrador findById(int idNotificacion) {
        return notificacionRepository.findById(idNotificacion);
    }

    @Transactional
    public void updateNotificacion(NotificacionAdministrador notificacion) {
        notificacionRepository.updateNotificacion(notificacion);
    }

    @Transactional
    public void deleteNotificacion(int idNotificacion) {
        notificacionRepository.deleteNotificacion(idNotificacion);
    }
}
