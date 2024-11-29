package pe.uni.buenaventurabackend.modules.planificacion.service;

import pe.uni.buenaventurabackend.modules.planificacion.models.*;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.GuardarPlanRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPlanService {
    List<String> findAll();
    List<Map<String,Object>> findX(int offset);
    void nuevoPlan(Plan_de_mantenimiento p, Mantenimiento mant, List<Integer> listaEquipos, List<InsumoDTO> listaInsumos, int id_usuario);
    void reservaEquipo(List<Integer> listaEquipos);
    void reservaInsumo(int id_usuario, List<InsumoDTO> listaInsumos);
    void envioNotificacion(int id_usuario, int id_plan);
    DetallePlanRequest detallePlan(int id_plan);
    void guardarPlan(int id_plan, Plan_de_mantenimiento p, Mantenimiento m, List<Integer> listaEquipos, List<InsumoDTO> listaInsumos);
    void borrarPlan(int id_plan);

    List<Map<String,Object>> findXbyMachine(int offset, int id_maquina);
    List<Map<String,Object>> findXbyDate(int offset, LocalDate fecha_inicio_programado);

    int conteoPlan();

    List<InsumoDTO> listaInsumos();

    List<EquipoDTO> listaEquipos();

    List<String> listaMaquinas();

}
