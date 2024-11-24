package pe.uni.buenaventurabackend.modules.gestion_reportes.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {
    private String resultado;
    private int id_mantenimiento;
}