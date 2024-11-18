package pe.uni.buenaventurabackend.modules.iperc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IPERC {
    private int id_reg_iperc;
    private int id_registro_riesgo;
    private int id_proceso;
    private Date fecha_registro;
    private int cant_riesgos_analizados;
}
