package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IPlanRepository;
import pe.uni.buenaventurabackend.modules.planificacion.repository.PlanRepository;

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
        System.out.println(list.toString());
        return list;
    }
}
