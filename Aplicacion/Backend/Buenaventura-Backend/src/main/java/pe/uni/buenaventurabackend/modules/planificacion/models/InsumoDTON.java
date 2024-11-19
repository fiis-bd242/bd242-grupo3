package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumoDTON {
    private String nombre_insumo;
    private int cantidad;
}
