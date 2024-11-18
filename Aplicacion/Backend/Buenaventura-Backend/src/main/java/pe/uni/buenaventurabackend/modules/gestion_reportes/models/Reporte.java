package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {
    private int id_reporte;
    private Date fecha_reporte;
    private int id_estado_reporte;
    private int id_jefe;
    private Integer id_supervisor ;
}
