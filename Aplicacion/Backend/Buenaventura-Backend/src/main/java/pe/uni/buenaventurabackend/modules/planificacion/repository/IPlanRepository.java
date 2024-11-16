package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.EquipoSXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;
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
}
