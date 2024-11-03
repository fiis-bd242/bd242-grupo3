package pe.uni.buenaventurabackend.modules.planificacion.repository;

import pe.uni.buenaventurabackend.modules.planificacion.models.Plan_de_mantenimiento;

import java.util.List;

public interface IPlanRepository {
    List<Plan_de_mantenimiento> findAll();
}
