package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_temporalDTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.repository.IDetalle_reserva_temporal_Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleReservaTemporalService implements IDetalleReservaTemporalService {

    private final IDetalle_reserva_temporal_Repository detalleReservaTemporalRepository;

    @Override
    public void truncateTable() {
        detalleReservaTemporalRepository.truncateTable();
    }

    @Override
    public void insertDetalleReservaTemporal(int cantReserv, int idInsumo, int idReserva) {
        detalleReservaTemporalRepository.insertDetalleReservaTemporal(cantReserv, idInsumo, idReserva);
    }

    @Override
    public List<Detalle_reserva_temporalDTO> findAllDetallesConInsumos() {
        return detalleReservaTemporalRepository.findAllDetallesConInsumos();
    }
}
