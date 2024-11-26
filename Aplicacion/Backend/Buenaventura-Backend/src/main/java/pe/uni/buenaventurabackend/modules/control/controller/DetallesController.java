package pe.uni.buenaventurabackend.modules.control.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.control.models.requests.DetallesRequest;
import pe.uni.buenaventurabackend.modules.control.service.IDetallesService;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;
import pe.uni.buenaventurabackend.modules.planificacion.service.IPlanService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/control")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class DetallesController {
    private final IDetallesService iDetallesService;

    @GetMapping("/detalles/{id_plan}")
    public ResponseEntity<DetallesRequest> detalles(@PathVariable int id_plan) {
        try {
            DetallesRequest result = iDetallesService.detalles(id_plan);

            // Inicializa listas vacías si son null
            if (result.getListaEquipos() == null) {
                result.setListaEquipos(new ArrayList<>());
            }
            if (result.getListaInsumos() == null) {
                result.setListaInsumos(new ArrayList<>());
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Devuelve null en caso de error.
        }
    }


}
