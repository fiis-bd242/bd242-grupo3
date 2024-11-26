package pe.uni.buenaventurabackend.modules.control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.control.models.requests.DetallesRequest;
import pe.uni.buenaventurabackend.modules.control.repository.IDetallesRepository;


@Service
@RequiredArgsConstructor
public class DetallesService implements IDetallesService{
    private final IDetallesRepository iDetallesRepository;


    public DetallesRequest detalles(int id_plan){
        return iDetallesRepository.detalles(id_plan);
    }
}
