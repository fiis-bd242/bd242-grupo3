package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.repository.IDetalle_reserva_Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleReservaService implements IDetalleReservaService {

    private final IDetalle_reserva_Repository detalleReservaRepository;

    @Override
    public void transferirDetallesReservaTemporal() {
        detalleReservaRepository.transferirDetallesReservaTemporal();
    }

    @Override
    public List<Detalle_reserva_DTO> obtenerDetallesPorReservaId(Integer idReserva) {
        return detalleReservaRepository.obtenerDetallesPorReservaId(idReserva);
    }
}
