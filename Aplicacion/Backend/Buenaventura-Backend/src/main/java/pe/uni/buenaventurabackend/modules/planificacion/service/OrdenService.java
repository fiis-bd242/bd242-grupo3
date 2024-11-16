package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IActividadRepository;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IOrdenRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrdenService implements IOrdenService{
    private final IOrdenRepository iOrdenRepository;
    private final IActividadRepository iActividadRepository;

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

    @Transactional
    @Override
    public void nuevaOrden(Orden_de_trabajo orden, int id_plan, List<Integer> lista_empleados){
        //Crear orden
        orden.setId_orden(iOrdenRepository.conteoOrden()+1);
        orden.setVisible("Y");
        iOrdenRepository.nuevaOrden(orden);

        //Enlazar a un plan de mantenimiento
        iOrdenRepository.nuevaOrdenMant(orden.getId_orden(), id_plan);

        //Añadir responsable
        Actividad_empleado act = new Actividad_empleado();
        int id_actvempleado = iActividadRepository.conteoActividad() +1;
        act.setId_actvempleado(id_actvempleado);
        id_actvempleado++;
        act.setId_empleado(lista_empleados.get(0));
        act.setId_orden(orden.getId_orden());
        iOrdenRepository.nuevaOrdenActv(act,true);


        //Añadir el resto de empleados
        for (int i = 1; i < lista_empleados.size(); i++){
            act.setId_actvempleado(id_actvempleado);
            act.setId_empleado(lista_empleados.get(i));
            id_actvempleado++;
        }
    }

}
