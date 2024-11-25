package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_DTO;

import java.util.List;

public interface IDetalleReservaService {
    void transferirDetallesReservaTemporal();
    List<Detalle_reserva_DTO> obtenerDetallesPorReservaId(Integer idReserva);
}
