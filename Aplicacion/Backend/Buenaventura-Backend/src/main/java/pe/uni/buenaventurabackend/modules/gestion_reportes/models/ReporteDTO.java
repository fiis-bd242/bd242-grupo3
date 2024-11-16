package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
    private String codigoRegistro;
    private String fechaDelDia;
    private int numeroRegistrosDia;
    private Timestamp fechaReporte;
    private String estadoReporte;
    private String jefe;

}

