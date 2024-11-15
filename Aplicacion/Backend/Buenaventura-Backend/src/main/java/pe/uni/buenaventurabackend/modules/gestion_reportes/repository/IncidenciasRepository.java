package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Incidencias_tag;

public interface IncidenciasRepository extends JpaRepository<Incidencias_tag,Integer> {
}
