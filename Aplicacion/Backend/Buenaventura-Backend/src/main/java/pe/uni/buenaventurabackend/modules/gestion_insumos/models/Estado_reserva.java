package pe.uni.buenaventurabackend.modules.gestion_insumos.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estado_reserva {
    @Id
    private Integer Id_estado_reserva;
    private String Nombre_estado;
}
