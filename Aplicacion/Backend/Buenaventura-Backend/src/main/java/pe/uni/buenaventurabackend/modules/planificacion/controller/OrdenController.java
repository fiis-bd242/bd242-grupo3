package pe.uni.buenaventurabackend.modules.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
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
}
