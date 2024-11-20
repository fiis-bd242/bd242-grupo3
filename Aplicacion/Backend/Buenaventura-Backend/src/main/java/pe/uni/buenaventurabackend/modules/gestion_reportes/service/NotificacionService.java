package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_reportes.repository.NotificacionRepository;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;

    public void crearNotificacion(Integer idRegistro, String asunto, String mensaje, int idRemitente, String fecha_Reporte, int id_tipo) {
        notificacionRepository.insertarNotificacion(idRegistro, asunto, mensaje, idRemitente,fecha_Reporte, id_tipo);
    }
}
