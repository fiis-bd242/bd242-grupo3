package pe.uni.buenaventurabackend.modules.control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.uni.buenaventurabackend.modules.control.models.Notificacion1;
import pe.uni.buenaventurabackend.modules.control.repository.INotificacion1Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Notificaciones;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IPlanRepository;


@Service
@RequiredArgsConstructor
public class Notificacion1Service implements INotificacion1Service {
    private final IPlanRepository iPlanRepository;
    private final INotificacion1Repository iNotificacion1Repository;

    @Transactional
    @Override
    public void aceptarNotificacion(int id_usuario, int id_plan){
        Notificacion1 noti = new Notificacion1();
        noti.setId_notificacion(iPlanRepository.conteoNotificaciones() +1);
        noti.setId_remitente(id_usuario);
        noti.setId_plan(id_plan);
        iNotificacion1Repository.aceptarNotificacion(noti);
    }

    @Transactional
    @Override
    public void rechazarNotificacion(int id_usuario, int id_plan){
        Notificacion1 noti = new Notificacion1();
        noti.setId_notificacion(iPlanRepository.conteoNotificaciones() +1);
        noti.setId_remitente(id_usuario);
        noti.setId_plan(id_plan);
        iNotificacion1Repository.rechazarNotificacion(noti);
    }
}
