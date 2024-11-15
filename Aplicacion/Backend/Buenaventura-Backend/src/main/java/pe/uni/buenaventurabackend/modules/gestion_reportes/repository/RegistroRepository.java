package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
}