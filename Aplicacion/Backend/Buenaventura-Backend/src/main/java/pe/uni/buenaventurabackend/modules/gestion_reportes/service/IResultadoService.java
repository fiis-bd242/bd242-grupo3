package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Resultado;

import java.util.List;

public interface IResultadoService {
    List<Resultado> obtenerResultados(String nombreMarca);
}
