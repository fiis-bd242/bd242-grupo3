package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {
    private int id_empleado;
    private String nombre;
}
