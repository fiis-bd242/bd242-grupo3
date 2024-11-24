package pe.uni.buenaventurabackend.modules.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleActividadRequest;
import pe.uni.buenaventurabackend.modules.planificacion.service.IActividadService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planificacion")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class ActividadController {
    private final IActividadService iActividadService;

    @GetMapping("/allactividades")
    public ResponseEntity<List<Actividad_empleado>> list(){
        var result = iActividadService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/listaactividades/{id_orden}/{offset}")
    public ResponseEntity<List<Map<String,Object>>> list(@PathVariable int id_orden, @PathVariable int offset){
        var result = iActividadService.findX(id_orden, offset);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/detalleActividad/{id_actvempleado}")
    public ResponseEntity<DetalleActividadRequest> detalleActividad(@PathVariable int id_actvempleado){
        var result = iActividadService.detalleActividad(id_actvempleado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/guardarActividad/{id_actvempleado}")
    public ResponseEntity<?> guardarActividad(@PathVariable int id_actvempleado, @RequestBody Actividad_empleado act){
        try {
            iActividadService.guardarActividad(act);
            return ResponseEntity.ok(new ApiResponse("Actividad guardada exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al guardar la orden: " + e.getMessage()));
        }
    }

    @DeleteMapping("/borrarActividad/{id_actvempleado}")
    public ResponseEntity<?> borrarActividad(@PathVariable int id_actvempleado){
        try{
            iActividadService.borrarActividad(id_actvempleado);
            return ResponseEntity.ok(new ApiResponse("Actividad borrado exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al borrar la actividad: " + e.getMessage()));
        }
    }

    @GetMapping("/conteoActividad/{id_orden}")
    public ResponseEntity<Integer> conteoActividad(@PathVariable int id_orden){
        var result = iActividadService.conteoActividadOrden(id_orden);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
