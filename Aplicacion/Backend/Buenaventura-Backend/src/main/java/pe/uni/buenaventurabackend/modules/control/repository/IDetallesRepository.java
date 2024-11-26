package pe.uni.buenaventurabackend.modules.control.repository;

import pe.uni.buenaventurabackend.modules.control.models.requests.DetallesRequest;

public interface IDetallesRepository {
    DetallesRequest detalles(int id_plan);
}
