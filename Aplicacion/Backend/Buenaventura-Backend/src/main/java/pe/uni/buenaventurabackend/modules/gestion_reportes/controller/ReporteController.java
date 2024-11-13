package pe.uni.buenaventurabackend.modules.gestion_reportes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;
import pe.uni.buenaventurabackend.modules.gestion_reportes.service.IReporteService;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final IReporteService iReporteService;

    @GetMapping("/allreports")
    public ResponseEntity<List<Reporte>> list(){
        var result = iReporteService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
