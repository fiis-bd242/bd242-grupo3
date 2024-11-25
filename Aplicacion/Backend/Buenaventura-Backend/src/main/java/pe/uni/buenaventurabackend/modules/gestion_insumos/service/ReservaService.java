package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.dto.Reserva_estado_DTO;
import pe.uni.buenaventurabackend.modules.gestion_insumos.repository.IReserva_Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final IReserva_Repository reservaRepository;

    @Override
    public void insertarReserva(int idReserva, int idEmpleado) {
        reservaRepository.insertarReserva(idReserva, idEmpleado);
    }

    @Override
    public List<Reserva_estado_DTO> obtenerReservasConEstadosMantenimiento(int idEmpleado, int limite, int offset) {
        return reservaRepository.obtenerReservasConEstadosMantenimiento(idEmpleado, limite, offset);
    }

    @Override
    public Reserva_DTO buscarReservaPorId(Integer idReserva) {
        return reservaRepository.buscarReservaPorId(idReserva);
    }

    @Override
    public int validarNumeroReserva(Integer idReserva) {
        return reservaRepository.validarNumeroReserva(idReserva);
    }

    @Override
    public int actualizarEstadoReserva(Integer idReserva, Integer idEstado) {
        return reservaRepository.actualizarEstadoReserva(idReserva, idEstado);
    }

    @Override
    public List<Reserva_estado_DTO> obtenerReservasConEstadoAlmacen(int limite, int offset) {
        return reservaRepository.obtenerReservasConEstadoAlmacen(limite, offset);
    }

    @Override
    public int obtenerNumeroDeReservas() {
        return reservaRepository.obtenerNumeroDeReservas();
    }
}
