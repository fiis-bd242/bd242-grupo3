package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan_de_mantenimiento {
    private int id_plan;
    private String descripcion;
    private Date fecha_plan;
    private int empleado_asigna;
    private int id_criticidad;
}
