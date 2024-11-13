package pe.uni.buenaventurabackend.modules.gestion_reportes.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;
import pe.uni.buenaventurabackend.modules.gestion_reportes.repository.IReporte;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService implements IReporteService {
    private final IReporte iReporteRepository;
    @Override
    public List<Reporte> findAll() {

        List<Reporte> lists;
        lists = iReporteRepository.findAll();
        return lists;
    }
}
