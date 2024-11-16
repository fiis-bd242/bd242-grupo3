package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidencias_tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_incidencias;
    private String incidencia;
    @Column(name = "id_registro")
    private int id_registro;

}
