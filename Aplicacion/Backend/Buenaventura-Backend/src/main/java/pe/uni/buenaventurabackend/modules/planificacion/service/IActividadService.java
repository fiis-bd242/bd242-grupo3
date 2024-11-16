package pe.uni.buenaventurabackend.modules.planificacion.service;

import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;

import java.util.List;
import java.util.Map;

public interface IActividadService {
    List<Actividad_empleado> findAll();
    List<Map<String,Object>> findX(int id_orden, int offset);
}
