package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoSXMantenimiento {
    private int id_equipo_mant;
    private int id_act_mantto;
    private int id_equipo_soporte;
}
