package pe.uni.buenaventurabackend.modules.gestion_insumos.service;

import pe.uni.buenaventurabackend.modules.gestion_insumos.models.Insumo;

import java.util.List;

public interface IInsumoService {
    List<Insumo> obtenerInsumosConLimiteYOffset(int limite, int offset);
    List<Insumo> obtenerInsumosPorNombre(String nombre);
}
