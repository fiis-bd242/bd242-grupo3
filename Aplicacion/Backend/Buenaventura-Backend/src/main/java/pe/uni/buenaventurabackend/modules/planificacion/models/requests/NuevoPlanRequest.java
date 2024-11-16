package pe.uni.buenaventurabackend.modules.planificacion.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoDTO;
import pe.uni.buenaventurabackend.modules.planificacion.models.Mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevoPlanRequest {
    private Plan_de_mantenimiento plan;
    private Mantenimiento mantenimiento;
    private List<Integer> listaEquipos;
    private List<InsumoDTO> listaInsumos;
}

