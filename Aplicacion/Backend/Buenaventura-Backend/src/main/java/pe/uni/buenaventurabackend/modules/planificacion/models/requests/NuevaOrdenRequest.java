package pe.uni.buenaventurabackend.modules.planificacion.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevaOrdenRequest {
    private Orden_de_trabajo orden;
    private int id_plan;
    private List<Integer> lista_empleados;
}
