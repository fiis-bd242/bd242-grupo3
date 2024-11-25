package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_insumos.models.Insumo;
import pe.uni.buenaventurabackend.modules.gestion_insumos.repository.IInsumo_Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsumoService implements IInsumoService {

    private final IInsumo_Repository insumoRepository;

    @Override
    public List<Insumo> obtenerInsumosConLimiteYOffset(int limite, int offset) {
        return insumoRepository.obtenerInsumosConLimiteYOffset(limite, offset);
    }

    @Override
    public List<Insumo> obtenerInsumosPorNombre(String nombre) {
        return insumoRepository.obtenerInsumosPorNombre(nombre);
    }
}
