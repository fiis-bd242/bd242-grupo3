package pe.uni.buenaventurabackend.modules.gestion_insumos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.service.IDetalleReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
public class DetalleReservaController {

    private final IDetalleReservaService detalleReservaService;

    // Endpoint para transferir detalles desde la reserva temporal
    @PostMapping("/transferir")
    public void transferirDetallesReservaTemporal() {
        detalleReservaService.transferirDetallesReservaTemporal();
    }

    // Endpoint para obtener los detalles por el id de la reserva
    @GetMapping("/detalles/{idReserva}")
    public List<Detalle_reserva_DTO> obtenerDetallesPorReservaId(@PathVariable Integer idReserva) {
        return detalleReservaService.obtenerDetallesPorReservaId(idReserva);
    }
}

