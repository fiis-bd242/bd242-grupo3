package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;

import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_DTO;

import java.util.List;

public interface IDetalle_reserva_Repository {
    void transferirDetallesReservaTemporal();
    List<Detalle_reserva_DTO> obtenerDetallesPorReservaId(Integer idReserva);
}
