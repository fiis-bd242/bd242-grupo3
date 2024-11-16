package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IOrdenRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrdenService implements IOrdenService{
    private final IOrdenRepository iOrdenRepository;
    @Override
    public List<Orden_de_trabajo> findAll() {
        List<Orden_de_trabajo> list;
        list = iOrdenRepository.findAll();
        return list;
    }

    @Override
    public List<Map<String,Object>> find10(int offset){
        List<Map<String,Object>> list;
        list = iOrdenRepository.find10(offset);
        System.out.println(list.toString());
        return list;
    }
}
