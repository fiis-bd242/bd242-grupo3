package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actividad_empleado {
    private int id_actvempleado;
    private String nombre_actv;
    private String descripcion_actv;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Integer id_equipo_soporte;
    private int id_empleado;
    private int id_estado;
    private int id_orden;
}
