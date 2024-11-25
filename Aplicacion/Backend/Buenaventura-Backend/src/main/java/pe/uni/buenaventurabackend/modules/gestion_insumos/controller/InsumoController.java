package pe.uni.buenaventurabackend.modules.gestion_insumos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_insumos.models.Insumo;
import pe.uni.buenaventurabackend.modules.gestion_insumos.service.IInsumoService;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
public class InsumoController {

    private final IInsumoService insumoService;

    // Endpoint para obtener insumos con límite y desplazamiento (paginación)
    @GetMapping("/listar-insumo")
    public List<Insumo> obtenerInsumosConLimiteYOffset(@RequestParam int limite, @RequestParam int offset) {
        return insumoService.obtenerInsumosConLimiteYOffset(limite, offset);
    }

    // Endpoint para obtener insumos por nombre
    @GetMapping("/insumoxnombre")
    public List<Insumo> obtenerInsumosPorNombre(@RequestParam String nombre) {
        return insumoService.obtenerInsumosPorNombre(nombre);
    }
}
