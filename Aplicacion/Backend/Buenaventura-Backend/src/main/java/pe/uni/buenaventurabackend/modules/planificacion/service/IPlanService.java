package pe.uni.buenaventurabackend.modules.planificacion.service;

import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoDTO;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoXMantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Mantenimiento;
import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;

import java.util.List;
import java.util.Map;

public interface IPlanService {
    List<Plan_de_mantenimiento> findAll();
    List<Map<String,Object>> findX(int offset);
    void nuevoPlan(Plan_de_mantenimiento p, Mantenimiento mant, List<Integer> listaEquipos, List<InsumoDTO> listaInsumos);
    void reservaEquipo(List<Integer> listaEquipos);
    void reservaInsumo(List<InsumoDTO> listaInsumos);
}
