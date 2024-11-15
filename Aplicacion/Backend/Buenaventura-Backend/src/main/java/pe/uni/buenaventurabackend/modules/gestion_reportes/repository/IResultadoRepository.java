package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Resultado;

import java.util.List;

public interface IResultadoRepository {
    List<Resultado> obtenerResultados(String nombreMarca);
}
