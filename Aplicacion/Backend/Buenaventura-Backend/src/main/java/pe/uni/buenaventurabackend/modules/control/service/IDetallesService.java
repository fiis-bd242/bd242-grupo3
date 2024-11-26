package pe.uni.buenaventurabackend.modules.control.service;

import pe.uni.buenaventurabackend.modules.control.models.requests.DetallesRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;

public interface IDetallesService {

    DetallesRequest detalles(int id_plan);
}
