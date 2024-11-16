package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Incidencias_tag;

public interface IncidenciasRepository extends JpaRepository<Incidencias_tag,Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Incidencias_tag it WHERE it.id_registro = :idRegistro AND it.incidencia = :incidencia")
    int eliminarIncidenciaPorRegistroYIncidencia(Integer idRegistro, String incidencia);

    @Transactional
    @Modifying
    @Query("INSERT INTO Incidencias_tag (incidencia, id_registro) VALUES (:incidencia, :idRegistro)")
    int agregarIncidencia(String incidencia, Integer idRegistro);
}
