package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mantenimiento {
    private int id_act_mantto;
    private Date fecha_inicio_programado;
    private Date fecha_fin_programado;
    private int id_orden;
    private int id_plan;
    private int id_tipo_mant;
    private int id_maquina;
    private int id_estado;

}
