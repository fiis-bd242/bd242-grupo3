package pe.uni.buenaventurabackend.modules.gestion_reportes.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDTO;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDia;
import pe.uni.buenaventurabackend.modules.gestion_reportes.repository.IReporteRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService implements pe.uni.buenaventurabackend.modules.gestion_reportes.service.IReporteService {
    private final IReporteRepository iReporteRepository;
    @Override
    public List<Reporte> findAll() {

        List<Reporte> lists;
        lists = iReporteRepository.findAll();
        return lists;
    }

    @Override
    public List<ReporteDia> obtenerReportes() {
        List<ReporteDia> lists;
        lists = iReporteRepository.obtenerReportes();
        return lists;
    }

    @Override
    public int crearReporte(int idJefe) {
        return iReporteRepository.insertarReporte(idJefe);
    }

    @Override
    public List<ReporteDTO> obtenerReportesPorFecha(Date fechaInicial, Date fechaFinal) {
        List<ReporteDTO> lists;
        lists = iReporteRepository.obtenerReportesPorFecha(fechaInicial,fechaFinal);
        return lists;
    }

}
