package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IActividadRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActividadService implements IActividadService{
    private final IActividadRepository iActividadRepository;
    @Override
    public List<Actividad_empleado> findAll() {
        List<Actividad_empleado> list;
        list = iActividadRepository.findAll();
        return list;
    }

    @Override
    public List<Map<String,Object>> find9(int id_orden, int offset){
        List<Map<String,Object>> list;
        list = iActividadRepository.find9(id_orden, offset);
        System.out.println(list.toString());
        return list;
    }
}
