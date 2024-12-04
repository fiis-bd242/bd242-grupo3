package pe.uni.buenaventurabackend.modules.seguridad.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String email;
    String password;
}
