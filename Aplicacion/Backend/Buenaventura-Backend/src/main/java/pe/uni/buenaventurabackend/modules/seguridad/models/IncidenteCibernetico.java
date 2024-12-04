package pe.uni.buenaventurabackend.modules.seguridad.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenteCibernetico {
    private int idIncidente;
    private String descripcionIncidente;
    private Date fecha;
    private String criticidad;
    private String usuarioInvolucrado;
    private String medidasTomadas;

}
