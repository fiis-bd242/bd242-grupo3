package pe.uni.buenaventurabackend.modules.planificacion.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.uni.buenaventurabackend.modules.planificacion.models.EquipoDTO;
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
    private List<EquipoDTO> listaEquipos;
    private List<InsumoDTO> listaInsumos;
    private int id_usuario;
    private String id_maquina;
}

