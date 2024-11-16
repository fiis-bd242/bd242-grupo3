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
    public List<Map<String,Object>> findX(int id_orden, int offset){
        // Se define el límite como 9 de momento.
        int limit = 9;
        List<Map<String,Object>> list;
        list = iActividadRepository.findX(id_orden, limit, offset);
        return list;
    }
}
