package pe.uni.buenaventurabackend.modules.control.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.control.repository.ITrabajosRepository;


import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrabajosService implements ITrabajosService{
    private final ITrabajosRepository iTrabajosRepository;


    public List<Map<String,Object>> findX(int offset){
        // Limite
        int limit = 10;
        List<Map<String,Object>> list;
        list = iTrabajosRepository.findX(limit, offset);
        return list;
    }

}
