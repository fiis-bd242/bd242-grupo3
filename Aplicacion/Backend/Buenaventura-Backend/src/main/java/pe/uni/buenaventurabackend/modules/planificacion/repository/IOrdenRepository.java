package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;

import java.util.List;
import java.util.Map;

public interface IOrdenRepository {
    List<Orden_de_trabajo> findAll();
    List<Map<String,Object>> find10(int offset);
}
