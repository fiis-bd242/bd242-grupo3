package pe.uni.buenaventurabackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ResponseStatus(HttpStatus.OK)
public class ApiResponse {
    String message;
}
