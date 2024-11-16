package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import lombok.Data;

@Data
public class ReporteDia {
    private String horaRegistro;
    private String codigoActividad;
    private String nombreEquipo;
    private String tecnicoResponsable;
    private String tipoMantenimiento;
    private String fechaDia;
}