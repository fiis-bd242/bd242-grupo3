package pe.uni.buenaventurabackend.modules.planificacion.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardarOrdenRequest {
    private String descripcion;
    private int id_plan;
    private List<Integer> lista_empleados;
    private int num_responsable;
}
