package pe.uni.buenaventurabackend.modules.seguridad.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.uni.buenaventurabackend.models.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String nombre_user;
    private String email;
    private Role role;
    private String verification_code;

    private Boolean enabled;
    private Integer id_carrera;
    private String password;
    private String genero;
    private String img_user;
}
