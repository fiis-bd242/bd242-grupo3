package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleActividadRequest;

import java.util.List;
import java.util.Map;

public interface IActividadRepository {
    List<Actividad_empleado> findAll();
    List<Map<String,Object>> findX(int id_orden, int limit, int offset);
    int conteoActividad();
    DetalleActividadRequest detalleActividad(int id_actvempleado);
    void guardarActividad(Actividad_empleado act);
    void borrarActividad(int id_actvempleado);

    int conteoActividadOrden(int orden);
}
