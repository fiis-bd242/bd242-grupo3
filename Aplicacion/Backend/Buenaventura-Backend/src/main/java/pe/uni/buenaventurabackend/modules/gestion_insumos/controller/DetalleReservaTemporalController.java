package pe.uni.buenaventurabackend.modules.gestion_insumos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_temporalDTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.service.IDetalleReservaTemporalService;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
public class DetalleReservaTemporalController {

    private final IDetalleReservaTemporalService detalleReservaTemporalService;

    // Endpoint para truncar la tabla detalle_reserva_temporal
    @PostMapping("/truncate")
    public void truncateTable() {
        detalleReservaTemporalService.truncateTable();
    }

    // Endpoint para insertar un detalle de reserva temporal
    @PostMapping("/insertarDetalleReservaTemp")
    public void insertDetalleReservaTemporal(
            @RequestParam int cantReserv,
            @RequestParam int idInsumo,
            @RequestParam int idReserva) {
        detalleReservaTemporalService.insertDetalleReservaTemporal(cantReserv, idInsumo, idReserva);
    }

    // Endpoint para obtener todos los detalles con insumos
    @GetMapping("/detalles-insumos")
    public List<Detalle_reserva_temporalDTO> findAllDetallesConInsumos() {
        return detalleReservaTemporalService.findAllDetallesConInsumos();
    }
}
