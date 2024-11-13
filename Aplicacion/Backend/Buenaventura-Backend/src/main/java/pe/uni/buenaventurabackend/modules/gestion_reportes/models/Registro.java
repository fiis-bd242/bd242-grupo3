package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
    private int id_registro;
    private Date fecha_registro;
    private Date fecha_inicial;
    private int id_empleado;
    private int id_act_mantto;
    private int calificacion;
    private String observaciones;
}
