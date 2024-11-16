package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDTO;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDia;

import java.util.Date;
import java.util.List;

public interface IReporteRepository {
    List<Reporte> findAll();
    List<ReporteDia> obtenerReportes();
    int insertarReporte(int idJefe);
    List<ReporteDTO> obtenerReportesPorFecha(Date fechaInicial, Date fechaFinal );
    int actualizarEstadoReporte(Integer idReporte, Integer nuevoEstado);
}
