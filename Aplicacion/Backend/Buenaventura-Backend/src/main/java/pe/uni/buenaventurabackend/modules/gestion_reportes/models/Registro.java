package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_registro;
    private Date fecha_registro;
    private Date fecha_inicial;
    private int id_empleado;
    private int id_act_mantto;
    private int calificacion;
    private String observaciones;

}
