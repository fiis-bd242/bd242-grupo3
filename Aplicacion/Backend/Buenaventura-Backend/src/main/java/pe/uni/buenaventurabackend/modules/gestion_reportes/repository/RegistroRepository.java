package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Registro r SET r.observaciones = :observaciones WHERE r.id_registro = :idRegistro")
    int actualizarObservaciones(Integer idRegistro, String observaciones);
}