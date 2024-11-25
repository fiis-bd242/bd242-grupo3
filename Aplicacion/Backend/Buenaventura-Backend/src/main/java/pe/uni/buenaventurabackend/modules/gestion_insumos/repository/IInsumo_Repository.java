package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;

import pe.uni.buenaventurabackend.modules.gestion_insumos.models.Insumo;

import java.util.List;

public interface IInsumo_Repository {
    List<Insumo> obtenerInsumosConLimiteYOffset(int limite, int offset);
    List<Insumo> obtenerInsumosPorNombre(String nombre);
}
