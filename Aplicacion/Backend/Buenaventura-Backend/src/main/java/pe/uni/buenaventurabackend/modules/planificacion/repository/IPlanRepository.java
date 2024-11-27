package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.Notificaciones;
import pe.uni.buenaventurabackend.modules.planificacion.models.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPlanRepository {
    List<String> findAll();
    List<Map<String,Object>> findX(int limit, int offset);
    void nuevoPlanMant(Plan_de_mantenimiento plan, Mantenimiento mantenimiento);
    void nuevoPlanEquipo(EquipoSXMantenimiento equipoSXMantenimiento);
    void nuevoPlanInsumo(InsumoXMantenimiento insumoXMantenimiento);
    int conteoPlan();
    int conteoPlanEquipo();
    int conteoPlanInsumo();
    int conteoMantenimiento();
    DetallePlanRequest detallePlan(int id_plan);
    void envioNotificacion(Notificaciones noti);
    void guardarPlan(int id_plan, Plan_de_mantenimiento p, Mantenimiento m, List<Integer> listaEquipos, List<InsumoDTO> listaInsumos);
    void borrarPlan(int id_plan);

    List<Map<String,Object>> findXbyMachine(int limit, int offset, int id_maquina);
    List<Map<String,Object>> findXbyDate(int limit, int offset, Date fecha_inicio_programado);

    //Módulo de equipo de soporte
    void reservaEquipo(List<Integer> listaEquipos);

    //Módulo de insumo
    void reservaInsumo(int id_insumo, int cantidad, int id_usuario);
    List<InsumoDTO> listaInsumos();

    //Módulo de gestión de reportes
    int conteoNotificaciones();
    List<String> listaEquipos();

    //Modulo Control
    List<String> listaMaquinas();

}
