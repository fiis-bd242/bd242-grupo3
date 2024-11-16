package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDTO;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDia;

import java.util.Date;
import java.util.List;

public interface IReporteService {
    List<Reporte> findAll();
    List<ReporteDia> obtenerReportes();
    int crearReporte(int idJefe);
    List<ReporteDTO> obtenerReportesPorFecha(Date fechaInicial, Date fechaFinal);
    int actualizarEstadoReporte(Integer idReporte, Integer nuevoEstado);
}
