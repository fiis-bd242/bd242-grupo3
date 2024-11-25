package pe.uni.buenaventurabackend.modules.gestion_insumos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_estado_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.service.IReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
public class ReservaController {

    private final IReservaService reservaService;

    // Endpoint para insertar una reserva
    @PostMapping("/insertar-reserva")
    public void insertarReserva(@RequestParam int idReserva, @RequestParam int idEmpleado) {
        reservaService.insertarReserva(idReserva, idEmpleado);
    }

    // Endpoint para obtener reservas con estado de mantenimiento
    @GetMapping("/estado-mantenimiento")
    public List<Reserva_estado_DTO> obtenerReservasConEstadosMantenimiento(
            @RequestParam int idEmpleado, @RequestParam int limite, @RequestParam int offset) {
        return reservaService.obtenerReservasConEstadosMantenimiento(idEmpleado, limite, offset);
    }

    // Endpoint para obtener una reserva por su ID
    @GetMapping("/{idReserva}")
    public Reserva_DTO buscarReservaPorId(@PathVariable Integer idReserva) {
        return reservaService.buscarReservaPorId(idReserva);
    }

    // Endpoint para validar el número de una reserva
    @GetMapping("/validar/{idReserva}")
    public int validarNumeroReserva(@PathVariable Integer idReserva) {
        return reservaService.validarNumeroReserva(idReserva);
    }

    // Endpoint para actualizar el estado de una reserva
    @PutMapping("/actualizar-estado")
    public int actualizarEstadoReserva(@RequestParam Integer idReserva, @RequestParam Integer idEstado) {
        return reservaService.actualizarEstadoReserva(idReserva, idEstado);
    }

    // Endpoint para obtener reservas con estado de almacén
    @GetMapping("/estado-almacen")
    public List<Reserva_estado_DTO> obtenerReservasConEstadoAlmacen(
            @RequestParam int limite, @RequestParam int offset) {
        return reservaService.obtenerReservasConEstadoAlmacen(limite, offset);
    }

    // Endpoint para obtener el número total de reservas
    @GetMapping("/numero-reservas")
    public int obtenerNumeroDeReservas() {
        return reservaService.obtenerNumeroDeReservas();
    }
}
