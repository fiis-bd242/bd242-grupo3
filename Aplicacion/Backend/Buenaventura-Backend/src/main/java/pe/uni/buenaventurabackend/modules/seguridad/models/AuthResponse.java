package pe.uni.buenaventurabackend.modules.seguridad.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class AuthResponse {
    String token;
}
