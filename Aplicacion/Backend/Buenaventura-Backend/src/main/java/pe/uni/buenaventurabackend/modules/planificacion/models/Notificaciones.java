package pe.uni.buenaventurabackend.modules.planificacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificaciones {
    private int id_notificacion;
    private String asunto;
    private String mensaje;
    private Date fecha_notificacion;
    private int id_remitente;
    private int id_destinatario;
    private int id_registro;
    private int id_reporte;
    private int id_tipo;
}
