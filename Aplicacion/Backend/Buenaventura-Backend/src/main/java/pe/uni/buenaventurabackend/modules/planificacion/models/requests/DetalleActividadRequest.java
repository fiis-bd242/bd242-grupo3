package pe.uni.buenaventurabackend.modules.planificacion.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleActividadRequest {
    private String id_actvempleado;
    private String id_estado;
    private String id_maquina;
    private String nombre_actv;
    private String id_empleado;
    private String nombre_empleado;
    private String act_fecha_inicio;
    private String act_fecha_fin;
    private String id_equipo_soporte;
    private String nombre_equipo_soporte;
    private String id_orden;
    private String descripcion_actv;

}
