package pe.uni.buenaventurabackend.modules.gestion_insumos.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    private Integer Id_reserva;
    private Date Fecha;
    private Time Hora;
    private Integer Id_estado_reserva;
    private Integer Id_empleado;
}
