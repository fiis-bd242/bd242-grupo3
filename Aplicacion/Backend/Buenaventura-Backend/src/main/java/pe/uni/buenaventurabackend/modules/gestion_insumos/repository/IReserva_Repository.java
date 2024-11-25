package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;

import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_estado_DTO;

import java.util.List;

public interface IReserva_Repository {
    void insertarReserva(int idReserva, int idEmpleado);
    Reserva_DTO buscarReservaPorId(Integer idReserva);
    int validarNumeroReserva(Integer idReserva);
    int actualizarEstadoReserva(Integer idReserva, Integer idEstado);
    List<Reserva_estado_DTO> obtenerReservasConEstadoAlmacen(int limite, int offset);
    int obtenerNumeroDeReservas();
    List<Reserva_estado_DTO> obtenerReservasConEstadosMantenimiento(int idEmpleado, int limite, int offset);
}
