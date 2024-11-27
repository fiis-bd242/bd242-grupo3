package pe.uni.buenaventurabackend.modules.control.service;

import pe.uni.buenaventurabackend.modules.control.models.requests.DetallesRequest;

public interface IDetallesService {

    DetallesRequest detalles(int id_plan);
}
