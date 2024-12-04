package pe.uni.buenaventurabackend.modules.gestion_equipos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_equipos.service.EquipoSoporteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/equipo_soporte")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class EquipoSoporteController {

    @Autowired
    private EquipoSoporteService equipoSoporteService;

    @GetMapping("/obtenerEquipos")
    public ResponseEntity<List<Map<String, Object>>> listarEquipos() {
        var resultado = equipoSoporteService.obtenerEquipos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/buscarEquipos/{parametro}")
    public ResponseEntity<List<Map<String, Object>>> buscarEquipos(@PathVariable String parametro){
        var resultado = equipoSoporteService.BuscarEquipoSoporte(parametro);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/detalleEquipo/{id}")
    public ResponseEntity<Map<String, Object>> detalleEquipo(@PathVariable Integer id){
        var resultado = equipoSoporteService.obtenerDetalleEquipoSoporte(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
