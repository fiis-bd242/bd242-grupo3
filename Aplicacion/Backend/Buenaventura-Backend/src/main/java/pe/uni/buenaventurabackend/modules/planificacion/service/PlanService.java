package pe.uni.buenaventurabackend.modules.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.uni.buenaventurabackend.modules.planificacion.models.Notificaciones;
import pe.uni.buenaventurabackend.modules.planificacion.models.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IOrdenRepository;
import pe.uni.buenaventurabackend.modules.planificacion.repository.IPlanRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PlanService implements IPlanService{
    private final IPlanRepository iPlanRepository;
    private final IOrdenRepository iOrdenRepository;
    @Override
    public List<String> findAll() {
        List<String> list;
        list = iPlanRepository.findAll();
        return list;
    }

    @Override
    public List<Map<String,Object>> findX(int offset){
        // Limite
        int limit = 10;
        List<Map<String,Object>> list;
        list = iPlanRepository.findX(limit, offset);
        return list;
    }

    @Transactional
    @Override
    public void nuevoPlan(Plan_de_mantenimiento plan, Mantenimiento mant, List<Integer> listaEquipos, List<InsumoDTO> listaInsumos){
        //Establecimiento de parámetros estándar y calculados
        // IDs de plan, mantenimiento son calculados:
        plan.setId_plan(iPlanRepository.conteoPlan()+1);
        mant.setId_plan(iPlanRepository.conteoPlan()+1);
        mant.setId_orden(iOrdenRepository.conteoOrden());
        int id_act_mantto = iPlanRepository.conteoMantenimiento()+1;
        mant.setId_act_mantto(id_act_mantto);
        //El id de mantenimiento = 1 (Borrador)
        mant.setId_estado(1);

        iPlanRepository.nuevoPlanMant(plan,mant);
        EquipoSXMantenimiento elemEquipo = new EquipoSXMantenimiento();
        int id_equipo_mant = iPlanRepository.conteoPlanEquipo() +1;
        for (int id_equipo : listaEquipos) {
            elemEquipo.setId_act_mantto(id_act_mantto);
            elemEquipo.setId_equipo_mant(id_equipo_mant);
            elemEquipo.setId_equipo_soporte(id_equipo);
            iPlanRepository.nuevoPlanEquipo(elemEquipo);
            id_equipo_mant++;
        }

        int id_insum_mant = iPlanRepository.conteoPlanInsumo() +1;
        InsumoXMantenimiento insumoXMantenimiento = new InsumoXMantenimiento();
        for (InsumoDTO insumo : listaInsumos) {
            insumoXMantenimiento.setId_act_mantto(id_act_mantto);
            insumoXMantenimiento.setId_insum_mant(id_insum_mant);
            insumoXMantenimiento.setId_insumo(insumo.getId_insumo());
            insumoXMantenimiento.setCantidad(insumo.getCantidad());
            iPlanRepository.nuevoPlanInsumo(insumoXMantenimiento);
            id_insum_mant++;
        }

        //Reserva de los equipos de soporte e insumos (interaccion con otros modulos)
        reservaEquipo(listaEquipos);
        reservaInsumo(listaInsumos);

    }

    @Override
    public List<Map<String,Object>> findXbyMachine(int offset, int id_maquina){
        // Limite
        int limit = 10;
        List<Map<String,Object>> list;
        list = iPlanRepository.findXbyMachine(limit, offset, id_maquina);
        return list;
    }

    @Override
    public List<Map<String,Object>> findXbyDate(int offset, Date fecha_inicio_programado){
        // Limite
        int limit = 10;
        List<Map<String,Object>> list;
        list = iPlanRepository.findXbyDate(limit, offset, fecha_inicio_programado);
        return list;
    }

    @Transactional
    @Override
    public void reservaEquipo(List<Integer> listaEquipos){
        for (int i : listaEquipos){
            iPlanRepository.reservaEquipo(i);
        }
    }

    @Transactional
    @Override
    public void reservaInsumo(List<InsumoDTO> listaInsumos){
        for (InsumoDTO insumo : listaInsumos){
            iPlanRepository.reservaInsumo(insumo.getId_insumo(), insumo.getCantidad());
        }
    }

    @Transactional
    @Override
    public void envioNotificacion(int id_usuario, int id_plan){
        Notificaciones noti = new Notificaciones();
        noti.setId_notificacion(iPlanRepository.conteoNotificaciones() +1);
        noti.setId_remitente(id_usuario);
        noti.setId_plan(id_plan);
        iPlanRepository.envioNotificacion(noti);
    }

    @Override
    public DetallePlanRequest detallePlan(int id_plan){
        return iPlanRepository.detallePlan(id_plan);
    }

    @Transactional
    @Override
    public void guardarPlan(int id_plan, Plan_de_mantenimiento p, Mantenimiento m, List<Integer> listaEquipos, List<InsumoDTO> listaInsumos){
        iPlanRepository.guardarPlan(id_plan, p, m, listaEquipos, listaInsumos);
    }

    @Transactional
    @Override
    public void borrarPlan(int id_plan){
        iPlanRepository.borrarPlan(id_plan);
    }

    @Override
    public int conteoPlan(){
        return iPlanRepository.conteoPlan();
    }

    @Override
    public List<String> listaInsumos(){
        return iPlanRepository.listaInsumos();
    }

    @Override
    public List<Integer> listaEquipos(){
        return  iPlanRepository.listaEquipos();
    }
}
