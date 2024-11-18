package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {
    private int id_notificacion;
    private String Asunto;
    private String mensaje;
    private Date fecha_notificacion;
    private int id_remitente;
    private int id_destinatario;
    private Integer Id_registro;
    private Integer Id_reporte;
    private int id_tipo;
}
