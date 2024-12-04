package pe.uni.buenaventurabackend.modules.seguridad.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionAdministrador {
    private int idNotificacion;
    private int idAdministrador;
    private String tipoEvento;
    private Date fechaHoraNotificacion;
    private String estadoNotificacion;
    private String mensajeNotificacion;
    private String prioridad;
    private int idSesion;
    private int idAutenticacion;
}
