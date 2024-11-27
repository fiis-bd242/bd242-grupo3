package pe.uni.buenaventurabackend.modules.control.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.modules.planificacion.service.IPlanService;

@RestController
@RequestMapping("/api/control")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class NotificacionController {
    private final IPlanService iPlanService;
    @PostMapping("/aceptarNotificacion/{id_usuario}/{id_plan}")
    public ResponseEntity<?> aceptarNotificacion(@PathVariable int id_usuario, @PathVariable int id_plan) {
        try {
            iPlanService.envioNotificacion(id_usuario, id_plan);
            return ResponseEntity.ok(new ApiResponse("Notificación enviada exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al enviar la notificación: " + e.getMessage()));
        }
    }

    @PostMapping("/rechazarNotificacion/{id_usuario}/{id_plan}")
    public ResponseEntity<?> rechazarNotificacion(@PathVariable int id_usuario, @PathVariable int id_plan) {
        try {
            iPlanService.envioNotificacion(id_usuario, id_plan);
            return ResponseEntity.ok(new ApiResponse("Notificación enviada exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al enviar la notificación: " + e.getMessage()));
        }
    }
}
