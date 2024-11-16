package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.planificacion.models.EquipoSXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IPlanRepository;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PlanService implements IPlanService{
    private final IPlanRepository iPlanRepository;
    @Override
    public List<Plan_de_mantenimiento> findAll() {
        List<Plan_de_mantenimiento> list;
        list = iPlanRepository.findAll();
        return list;
    }

    @Override
    public List<Map<String,Object>> find10(int offset){
        List<Map<String,Object>> list;
        list = iPlanRepository.find10(offset);
        return list;
    }

    @Override
    public void nuevoPlan(Plan_de_mantenimiento plan, Mantenimiento mant, List<EquipoSXMantenimiento> listaEquipos, List<InsumoXMantenimiento> listaInsumos){
        //Establecimiento de parámetros estándar y calculados
        // IDs de plan, mantenimiento son calculados:
        plan.setId_plan(iPlanRepository.conteoPlan()+1);
        mant.setId_plan(iPlanRepository.conteoPlan()+1);
        mant.setId_act_mantto(iPlanRepository.conteoMantenimiento()+1);
        //El id de mantenimiento = 1 (Borrador)
        mant.setId_estado(1);

        iPlanRepository.nuevoPlanMant(plan,mant);
        for (EquipoSXMantenimiento listaEquipo : listaEquipos) {
            iPlanRepository.nuevoPlanEquipo(listaEquipo);
        }
        for (InsumoXMantenimiento insumo : listaInsumos) {
            iPlanRepository.nuevoPlanInsumo(insumo);
        }
    }
}
