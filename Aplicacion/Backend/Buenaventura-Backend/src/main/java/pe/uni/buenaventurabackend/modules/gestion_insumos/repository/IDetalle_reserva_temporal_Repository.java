package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Detalle_reserva_temporalDTO;
import java.util.List;

public interface IDetalle_reserva_temporal_Repository {
    void truncateTable();
    void insertDetalleReservaTemporal(int cantReserv, int idInsumo, int idReserva);
    List<Detalle_reserva_temporalDTO> findAllDetallesConInsumos();
}









































