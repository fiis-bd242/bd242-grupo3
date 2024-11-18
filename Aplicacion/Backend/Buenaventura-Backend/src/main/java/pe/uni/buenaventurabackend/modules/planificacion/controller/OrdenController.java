package pe.uni.buenaventurabackend.modules.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleOrdenRequest;
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
    public ResponseEntity<List<Orden_de_trabajo>> list(){
        var result = iOrdenService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/listaordenes/{offset}")
    public ResponseEntity<List<Map<String,Object>>> list(@PathVariable int offset){
        var result = iOrdenService.find10(offset);
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
}
