package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Resultado;
import pe.uni.buenaventurabackend.modules.gestion_reportes.repository.IResultadoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultadoService implements IResultadoService {
    private final IResultadoRepository resultadoRepository;

    @Override
    public List<Resultado> obtenerResultados(String nombreMarca) {
        return resultadoRepository.obtenerResultados(nombreMarca);
    }
}
