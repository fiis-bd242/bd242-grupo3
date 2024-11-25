package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_temporalDTO;

import java.util.List;

public interface IDetalleReservaTemporalService {
    void truncateTable();
    void insertDetalleReservaTemporal(int cantReserv, int idInsumo, int idReserva);
    List<Detalle_reserva_temporalDTO> findAllDetallesConInsumos();
}
