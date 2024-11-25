package pe.uni.buenaventurabackend.modules.gestion_insumos.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insumoxalmacen {
    @Id
    private Integer Id_insum_alm;
    private Integer Cantidad;
    private Integer Id_insumo;
    private Integer Id_almacen;
}
