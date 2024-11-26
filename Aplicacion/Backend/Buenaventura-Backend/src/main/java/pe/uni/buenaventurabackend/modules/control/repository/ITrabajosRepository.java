package pe.uni.buenaventurabackend.modules.control.repository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface ITrabajosRepository{

    List<Map<String,Object>> findX(int limit, int offset);

}
