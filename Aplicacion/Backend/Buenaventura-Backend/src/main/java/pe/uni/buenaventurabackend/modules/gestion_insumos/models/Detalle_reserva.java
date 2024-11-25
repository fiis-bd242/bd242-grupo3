package pe.uni.buenaventurabackend.modules.gestion_insumos.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detalle_reserva {
    @Id
    private Integer Id_detalle;
    private Integer Cant_reserv;
    private Integer Id_insumo;
    private Integer Id_reserva;
}
