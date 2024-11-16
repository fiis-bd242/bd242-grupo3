package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialMantenimientoDTO {
    private String nombreMaquina;
    private String tecnicoResponsable;
    private String tipoMantenimiento;
    private Timestamp fechaInicio;
    private Timestamp fechaFinal;
    private int calificacion;
    private String observaciones;
}
