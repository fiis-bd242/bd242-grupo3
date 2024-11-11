package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IPlanRepository;

import java.util.List;


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
}
