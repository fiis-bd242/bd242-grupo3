package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;

import java.util.List;
import java.util.Map;

public interface IActividadRepository {
    List<Actividad_empleado> findAll();
    List<Map<String,Object>> find9(int id_orden, int offset);
}
