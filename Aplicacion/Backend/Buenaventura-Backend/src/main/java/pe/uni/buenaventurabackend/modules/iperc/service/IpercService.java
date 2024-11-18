package pe.uni.buenaventurabackend.modules.iperc.service;

import pe.uni.buenaventurabackend.modules.iperc.repository.IpercRepository;
import pe.uni.buenaventurabackend.modules.iperc.models.requests.IpercEditablesRequest;

import java.util.List;

public class IpercService {
    private IpercRepository repository;
    public List<IpercEditablesRequest> listaIperc(){
        return repository.listaIperc();
    }
}
