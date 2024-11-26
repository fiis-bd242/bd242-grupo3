package pe.uni.buenaventurabackend.modules.control.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ITrabajosService {
    List<Map<String,Object>> findX(int offset);
}
