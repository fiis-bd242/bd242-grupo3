package pe.uni.buenaventurabackend.modules.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.GuardarPlanRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.NuevoPlanRequest;
import pe.uni.buenaventurabackend.modules.planificacion.service.IPlanService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planificacion")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class PlanController {
    private final IPlanService iPlanService;

    @GetMapping("/allplans")
    public ResponseEntity<List<Plan_de_mantenimiento>> list(){
        var result = iPlanService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/listaplanes/{offset}")
    public ResponseEntity<List<Map<String,Object>>> list(@PathVariable int offset){
        var result = iPlanService.findX(offset);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/nuevoPlan")
    public ResponseEntity<?> nuevoPlan(@RequestBody NuevoPlanRequest request){
        try {
            iPlanService.nuevoPlan(
                    request.getPlan(),
                    request.getMantenimiento(),
                    request.getListaEquipos(),
                    request.getListaInsumos()
            );
            return ResponseEntity.ok(new ApiResponse("Plan creado exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al crear el plan: " + e.getMessage()));
        }
    }

    @PostMapping("/envioNotificacion/{id_plan}")
    public ResponseEntity<?> envioNotificacion(@PathVariable int id_plan) {
        try {
            iPlanService.envioNotificacion(id_plan);
            return ResponseEntity.ok(new ApiResponse("Notificación enviada exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al enviar la notificación: " + e.getMessage()));
        }
    }

    @GetMapping("/detallePlan/{id_plan}")
    public ResponseEntity<DetallePlanRequest> detallePlan(@PathVariable int id_plan) {
        var result = iPlanService.detallePlan(id_plan);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/guardarPlan/{id_plan}")
    public ResponseEntity<?> guardarPlan(@PathVariable int id_plan, @RequestBody GuardarPlanRequest request) {
        try {
            iPlanService.guardarPlan(
                    id_plan,
                    request.getPlan(),
                    request.getMant(),
                    request.getListaEquipos(),
                    request.getListaInsumos()
            );
            return ResponseEntity.ok(new ApiResponse("Plan guardado exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al guardar el plan: " + e.getMessage()));
        }
    }

    @PostMapping("/borrarPlan/{id_plan}")
    public ResponseEntity<?> borrarPlan(@PathVariable int id_plan){
        try{
            iPlanService.borrarPlan(id_plan);
            return ResponseEntity.ok(new ApiResponse("Plan borrado exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al borrar el plan: " + e.getMessage()));
        }
    }
}
