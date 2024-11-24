package pe.uni.buenaventurabackend.modules.planificacion.service;

import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleActividadRequest;

import java.util.List;
import java.util.Map;

public interface IActividadService {
    List<Actividad_empleado> findAll();
    List<Map<String,Object>> findX(int id_orden, int offset);
    DetalleActividadRequest detalleActividad(int id_actvempleado);
    void guardarActividad(Actividad_empleado actividad_empleado);
    void borrarActividad(int id_actvempleado);

    int conteoActividadOrden(int id_orden);
}
