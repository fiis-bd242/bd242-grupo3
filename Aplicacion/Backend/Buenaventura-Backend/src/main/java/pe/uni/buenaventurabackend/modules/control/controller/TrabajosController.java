package pe.uni.buenaventurabackend.modules.control.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.control.service.ITrabajosService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/control")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class TrabajosController {
    private final ITrabajosService iTrabajosService;

    @GetMapping("/listatrabajos/{offset}")
    public ResponseEntity<List<Map<String,Object>>> list(@PathVariable int offset){
        var result = iTrabajosService.findX(offset);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
