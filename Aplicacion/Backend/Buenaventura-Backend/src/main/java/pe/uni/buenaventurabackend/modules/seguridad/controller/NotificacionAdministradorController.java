package pe.uni.buenaventurabackend.modules.seguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.modules.seguridad.models.NotificacionAdministrador;
import pe.uni.buenaventurabackend.modules.seguridad.service.NotificacionAdministradorService;

import java.util.List;

@RestController
@RequestMapping("api/seguridad")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class NotificacionAdministradorController {

    private final NotificacionAdministradorService notificacionService;

    // Crear notificación
    @PostMapping("/crearNotificacion")
    public ResponseEntity<?> createNotificacion(@RequestBody NotificacionAdministrador notificacion) {
        try {
            notificacionService.createNotificacion(notificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Notificación creada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al crear la notificación: " + e.getMessage()));
        }
    }

    // Obtener todas las notificaciones
    @GetMapping("/obtenerNotificaciones")
    public ResponseEntity<List<NotificacionAdministrador>> getAllNotificaciones() {
        try {
            List<NotificacionAdministrador> notificaciones = notificacionService.findAll();
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Obtener notificación por ID
    @GetMapping("/obtenerNotificacion/{id}")
    public ResponseEntity<?> getNotificacion(@PathVariable int id) {
        try {
            NotificacionAdministrador notificacion = notificacionService.findById(id);
            if (notificacion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("Notificación no encontrada"));
            }
            return ResponseEntity.ok(notificacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al obtener la notificación: " + e.getMessage()));
        }
    }

    // Actualizar notificación
    @PutMapping("/actualizarNotificacion/{id}")
    public ResponseEntity<?> updateNotificacion(@PathVariable int id, @RequestBody NotificacionAdministrador notificacion) {
        try {
            notificacion.setIdNotificacion(id);
            notificacionService.updateNotificacion(notificacion);
            return ResponseEntity.ok(new ApiResponse("Notificación actualizada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al actualizar la notificación: " + e.getMessage()));
        }
    }

    // Eliminar notificación
    @DeleteMapping("/eliminarNotificacion/{id}")
    public ResponseEntity<?> deleteNotificacion(@PathVariable int id) {
        try {
            notificacionService.deleteNotificacion(id);
            return ResponseEntity.ok(new ApiResponse("Notificación eliminada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al eliminar la notificación: " + e.getMessage()));
        }
    }
}
