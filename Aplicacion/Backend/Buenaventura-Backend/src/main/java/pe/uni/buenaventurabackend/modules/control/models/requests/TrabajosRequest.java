package pe.uni.buenaventurabackend.modules.control.models.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrabajosRequest {
    private String id_plan;
    private String id_maquina;
    private String nombre_tipo_mant;
    private String nivel;
    private String fecha_inicio_programado;
    private String estado;

}
