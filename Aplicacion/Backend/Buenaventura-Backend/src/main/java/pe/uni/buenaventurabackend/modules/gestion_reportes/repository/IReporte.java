package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;

import java.util.List;

public interface IReporte {
    List<Reporte> findAll();
}
