package pe.uni.buenaventurabackend.modules.planificacion.service;

import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;

import java.util.List;
import java.util.Map;

public interface IOrdenService {
    List<Orden_de_trabajo> findAll();
    List<Map<String,Object>> find10(int offset);
}
