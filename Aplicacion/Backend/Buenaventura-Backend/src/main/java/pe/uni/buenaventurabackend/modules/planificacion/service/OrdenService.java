package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.EmpleadoDTO;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleOrdenRequest;
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
    public List<String> findAll() {
        List<String> list;
        list = iOrdenRepository.findAll();
        return list;
    }

    @Override
    public List<Map<String,Object>> findX(int offset){
        int limit = 10;
        List<Map<String,Object>> list;
        list = iOrdenRepository.findX(limit,offset);
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
        iOrdenRepository.nuevaOrdenActv(act,true, id_plan);


        //Añadir el resto de empleados
        for (int i = 1; i < lista_empleados.size(); i++){
            act.setId_actvempleado(id_actvempleado);
            act.setId_empleado(lista_empleados.get(i));
            iOrdenRepository.nuevaOrdenActv(act,false, id_plan);
            id_actvempleado++;
        }
    }

    @Override
    public DetalleOrdenRequest detalleOrden(int id_orden){
        return iOrdenRepository.detalleOrden(id_orden);
    }

    @Transactional
    @Override
    public void guardarOrden(int id_orden, String descripcion, int id_plan, List<Integer> lista_empleados, int  num_responsable){
        int id_actvempleado = iActividadRepository.conteoActividad() +1;
        iOrdenRepository.guardarOrden(id_orden, descripcion, id_plan);

        //Añadir responsable
        Actividad_empleado act = new Actividad_empleado();
        act.setId_actvempleado(id_actvempleado);
        id_actvempleado++;
        act.setId_empleado(lista_empleados.get(num_responsable));
        act.setId_orden(id_orden);
        System.out.println("Antes de responsable");
        System.out.println(id_actvempleado);
        System.out.println(act.getId_actvempleado());
        iOrdenRepository.nuevaOrdenActv(act,true, id_plan);
        System.out.println("Despues de responsable");
        System.out.println(id_actvempleado);
        System.out.println(act.getId_actvempleado());


        //Añadir el resto de empleados
        System.out.println("Antes de bucle");
        for (int i = 0; i < lista_empleados.size(); i++){
            System.out.println("Dentro del bucle con i,id-act");
            System.out.println(i);
            System.out.println(id_actvempleado);
            if (num_responsable != i){
                act.setId_actvempleado(id_actvempleado);
                act.setId_empleado(lista_empleados.get(i));
                iOrdenRepository.nuevaOrdenActv(act,false, id_plan);
                id_actvempleado++;
            }
        }
    }

    @Transactional
    @Override
    public void borrarOrden(int id_orden){
        iOrdenRepository.borrarOrden(id_orden);
    }

    @Override
    public int conteoOrden(){
        return iOrdenRepository.conteoOrden();
    }

    @Override
    public List<EmpleadoDTO> listaEmpleados(){
        return iOrdenRepository.listaEmpleados();
    }
}
