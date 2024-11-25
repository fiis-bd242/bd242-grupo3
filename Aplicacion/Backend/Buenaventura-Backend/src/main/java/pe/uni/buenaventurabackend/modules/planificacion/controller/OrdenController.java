package pe.uni.buenaventurabackend.modules.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.modules.planificacion.models.EmpleadoDTO;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleOrdenRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.GuardarOrdenRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.NuevaOrdenRequest;
import pe.uni.buenaventurabackend.modules.planificacion.service.IOrdenService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planificacion")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class OrdenController {
    private final IOrdenService iOrdenService;

    @GetMapping("/allordenes")
    public ResponseEntity<List<String>> list(){
        var result = iOrdenService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/listaordenes/{offset}")
    public ResponseEntity<List<Map<String,Object>>> find10(@PathVariable int offset){
        var result = iOrdenService.findX(offset);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/nuevaOrden")
    public ResponseEntity<?> nuevaOrden(@RequestBody NuevaOrdenRequest request){
        try {
            iOrdenService.nuevaOrden(
                    request.getOrden(),
                    request.getId_plan(),
                    request.getLista_empleados()
            );
            return ResponseEntity.ok(new ApiResponse("Orden creada exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al crear la orden: " + e.getMessage()));
        }
    }

    @GetMapping("/detalleOrden/{id_orden}")
    public ResponseEntity<DetalleOrdenRequest> detalleOrden(@PathVariable int id_orden){
        var result = iOrdenService.detalleOrden(id_orden);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/guardarOrden/{id_orden}")
    public ResponseEntity<?> guardarOrden(@PathVariable int id_orden, @RequestBody GuardarOrdenRequest request) {
        try{
            iOrdenService.guardarOrden(
                    id_orden,
                    request.getDescripcion(),
                    request.getId_plan(),
                    request.getLista_empleados(),
                    request.getNum_responsable()
            );
            return ResponseEntity.ok(new ApiResponse("Orden guardada exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error al guardar la orden: " + e.getMessage()));
        }
    }

    @PostMapping("/borrarOrden/{id_orden}")
    public ResponseEntity<?> borrarOrden(@PathVariable int id_orden){
        try{
            iOrdenService.borrarOrden(id_orden);
            return ResponseEntity.ok(new ApiResponse("Orden borrada exitosamente"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("Error al guardar la orden: " + e.getMessage()));
        }
    }

    @GetMapping("/conteoOrden")
    public ResponseEntity<Integer> conteoOrden(){
        var result = iOrdenService.conteoOrden();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/listaEmpleados")
    public ResponseEntity<List<EmpleadoDTO>> listaEmpleados(){
        var result = iOrdenService.listaEmpleados();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
