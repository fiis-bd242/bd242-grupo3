package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden_de_trabajo {
    private int id_orden;
    private String descripcion;
    private Date fecha_orden;
    private String visible;
}
