package pe.uni.buenaventurabackend.modules.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.service.IPlanService;
import pe.uni.buenaventurabackend.modules.planificacion.service.PlanService;

import java.util.List;

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
}
