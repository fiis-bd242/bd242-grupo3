package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Registro;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Incidencias_tag;
import pe.uni.buenaventurabackend.modules.gestion_reportes.repository.RegistroRepository;
import pe.uni.buenaventurabackend.modules.gestion_reportes.repository.IncidenciasRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroService {
    private final RegistroRepository registroRepository;
    private final IncidenciasRepository incidenciaTagRepository;

    @Transactional
    public Registro guardarRegistroConIncidencias(Registro registro, List<Incidencias_tag> incidencias) {
        Registro nuevoRegistro = registroRepository.save(registro);

        for (Incidencias_tag incidencia : incidencias) {
            incidencia.setId_registro(nuevoRegistro.getId_registro());
            incidenciaTagRepository.save(incidencia);
        }

        return nuevoRegistro;
    }
    public void eliminarRegistro(int idRegistro) {
        if (registroRepository.existsById(idRegistro)) {
            registroRepository.deleteById(idRegistro);
        } else {
            throw new IllegalArgumentException("El registro con ID " + idRegistro + " no existe.");
        }
    }

    public boolean actualizarObservaciones(Integer idRegistro, String observaciones) {
        int rowsUpdated = registroRepository.actualizarObservaciones(idRegistro, observaciones);
        return rowsUpdated > 0;
    }
}
