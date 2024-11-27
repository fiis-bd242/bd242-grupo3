package pe.uni.buenaventurabackend.modules.control.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {

    private int id_notificacion;
    private int id_remitente;
    private int id_plan;
}
