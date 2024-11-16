package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumoXMantenimiento {
    private int id_insum_mant;
    private int cantidad;
    private int id_act_mantto;
    private int id_insumo;
}
