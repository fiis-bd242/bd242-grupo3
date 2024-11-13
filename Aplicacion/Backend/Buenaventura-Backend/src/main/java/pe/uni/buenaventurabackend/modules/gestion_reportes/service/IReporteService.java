package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;

import java.util.List;

public interface IReporteService {
    List<Reporte> findAll();
}
