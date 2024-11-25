package pe.uni.buenaventurabackend.modules.gestion_insumos.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insumo {
    @Id
    private Integer Id_insumo;
    private Integer Cantidad;
    private String Nombre;
}
