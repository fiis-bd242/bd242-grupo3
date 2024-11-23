package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.Notificaciones;
import pe.uni.buenaventurabackend.modules.planificacion.models.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;

import java.util.List;
import java.util.Map;

public interface IPlanRepository {
    List<Plan_de_mantenimiento> findAll();
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

    //Módulo de equipo de soporte
    void reservaEquipo(int id_equipo);

    //Módulo de insumo
    void reservaInsumo(int id_insumo, int id_cantidad);

    //Módulo de gestión de reportes
    int conteoNotificaciones();
}
