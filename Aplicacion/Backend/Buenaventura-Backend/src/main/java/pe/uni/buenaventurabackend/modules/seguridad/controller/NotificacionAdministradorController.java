package pe.uni.buenaventurabackend.modules.seguridad.controller;

import pe.uni.buenaventurabackend.modules.seguridad.models.NotificacionAdministrador;
import pe.uni.buenaventurabackend.modules.seguridad.service.NotificacionAdministradorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionAdministradorController {

    private final NotificacionAdministradorService notificacionService;

    public NotificacionAdministradorController(NotificacionAdministradorService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public void createNotificacion(@RequestBody NotificacionAdministrador notificacion) {
        notificacionService.createNotificacion(notificacion);
    }

    @GetMapping
    public List<NotificacionAdministrador> getAllNotificaciones() {
        return notificacionService.findAll();
    }

    @GetMapping("/{id}")
    public NotificacionAdministrador getNotificacion(@PathVariable int id) {
        return notificacionService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateNotificacion(@PathVariable int id, @RequestBody NotificacionAdministrador notificacion) {
        notificacion.setIdNotificacion(id);
        notificacionService.updateNotificacion(notificacion);
    }

    @DeleteMapping("/{id}")
    public void deleteNotificacion(@PathVariable int id) {
        notificacionService.deleteNotificacion(id);
    }
}
