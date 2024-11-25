package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_estado_DTO;

import java.util.List;

public interface IReservaService {
    void insertarReserva(int idReserva, int idEmpleado);
    List<Reserva_estado_DTO> obtenerReservasConEstadosMantenimiento(int idEmpleado, int limite, int offset);
    Reserva_DTO buscarReservaPorId(Integer idReserva);
    int validarNumeroReserva(Integer idReserva);
    int actualizarEstadoReserva(Integer idReserva, Integer idEstado);
    List<Reserva_estado_DTO> obtenerReservasConEstadoAlmacen(int limite, int offset);
    int obtenerNumeroDeReservas();

}
