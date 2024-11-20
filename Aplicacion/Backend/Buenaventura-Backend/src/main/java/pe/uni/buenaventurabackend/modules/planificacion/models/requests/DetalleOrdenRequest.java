package pe.uni.buenaventurabackend.modules.planificacion.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.uni.buenaventurabackend.modules.planificacion.models.EmpleadoDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrdenRequest {
    private String id_orden;
    private String descripcion;
    private String estado;
    private String maquina;
    private String fecha_orden;
    private String nombre_tipo_mant;
    private String fecha_inicio_programado;
    private String fecha_fin_programado;
    private String responsable;
    private String id_plan;
    private List<EmpleadoDTO> lista_empleados;
}
