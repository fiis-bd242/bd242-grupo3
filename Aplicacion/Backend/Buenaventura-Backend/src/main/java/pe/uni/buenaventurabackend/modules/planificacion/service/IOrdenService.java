package pe.uni.buenaventurabackend.modules.planificacion.service;

import pe.uni.buenaventurabackend.modules.planificacion.models.EmpleadoDTO;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleOrdenRequest;

import java.util.List;
import java.util.Map;

public interface IOrdenService {
    List<String> findAll();
    List<Map<String,Object>> findX(int offset);
    void nuevaOrden(Orden_de_trabajo orden, int id_plan, List<Integer> lista_empleados);
    DetalleOrdenRequest detalleOrden(int id_orden);
    void guardarOrden(int id_orden, String descripcion, int id_plan, List<Integer> lista_empleados, int  num_responsable);
    void borrarOrden(int id_orden);

    int conteoOrden();

    List<EmpleadoDTO> listaEmpleados();
}
