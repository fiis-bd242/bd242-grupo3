package pe.uni.buenaventurabackend.modules.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.service.IActividadService;
import pe.uni.buenaventurabackend.modules.planificacion.service.IPlanService;

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
        var result = iActividadService.find9(id_orden, offset);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
