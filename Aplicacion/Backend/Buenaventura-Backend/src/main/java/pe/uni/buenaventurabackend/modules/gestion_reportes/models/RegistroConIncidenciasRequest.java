package pe.uni.buenaventurabackend.modules.gestion_reportes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroConIncidenciasRequest {
    private Registro registro;
    private List<Incidencias_tag> incidencias;
}