package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_reportes.repository.IncidenciasRepository;


@Service
@RequiredArgsConstructor
public class IncidenciasTagService {


    private final IncidenciasRepository incidenciasTagRepository;

    public boolean eliminarIncidencia(Integer idRegistro, String incidencia) {
        int rowsDeleted = incidenciasTagRepository.eliminarIncidenciaPorRegistroYIncidencia(idRegistro, incidencia);
        return rowsDeleted > 0;
    }

    public boolean agregarIncidencia(String incidencia, Integer idRegistro) {
        int rowsInserted = incidenciasTagRepository.agregarIncidencia(incidencia, idRegistro);
        return rowsInserted > 0;
    }
}