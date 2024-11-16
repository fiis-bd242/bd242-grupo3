package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;

import java.util.List;
import java.util.Map;

public interface IOrdenRepository {
    List<Orden_de_trabajo> findAll();
    List<Map<String,Object>> find10(int offset);
    int conteoOrden();
    void nuevaOrden(Orden_de_trabajo orden);
    void nuevaOrdenMant(int id_orden, int id_plan);
    void nuevaOrdenActv(Actividad_empleado actividad_empleado, boolean responsable);
    List<String> findAllVisible();

}
