package pe.uni.buenaventurabackend.modules.seguridad.controller;

import jakarta.servlet.http.HttpServletResponse;
import pe.uni.buenaventurabackend.exception.AuthenticationException;
import pe.uni.buenaventurabackend.models.*;
import pe.uni.buenaventurabackend.modules.seguridad.models.Empleado;
import pe.uni.buenaventurabackend.modules.seguridad.models.IncidenteCibernetico;
import pe.uni.buenaventurabackend.modules.seguridad.models.LoginRequest;
import pe.uni.buenaventurabackend.modules.seguridad.models.RegisterRequest;
import pe.uni.buenaventurabackend.modules.seguridad.service.IAuthService;
import pe.uni.buenaventurabackend.utils.CodeVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/seguridad")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class AuthController {

    private final IAuthService iAuthService;
    private final TemplateEngine templateEngine;
    @Autowired
    CodeVerificationService codeVerificationService;

    @GetMapping("/todos")
    public ResponseEntity<ArrayList<Empleado>> getAllEmpleados(@RequestParam int offset) {
        try {
            ArrayList<Empleado> empleados = iAuthService.getAllEmpleados(offset);
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Empleado> login(@RequestBody LoginRequest loginRequest) {
        Empleado empleado = iAuthService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/crearIncidente")
    public ResponseEntity<String> createIncidente(@RequestBody IncidenteCibernetico incidente) {
        iAuthService.createIncidente(incidente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Incidente cibernético creado con éxito");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Empleado request){
        try {
            iAuthService.register(request);
            String response = "Todo ok";
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return handleAuthenticationException(e);
        }
    }

    @GetMapping("/verify-email")
    public String verifyUser(@RequestParam("code") String code) {
        Context context = new Context();
        try {
            iAuthService.verify(code);
            context.setVariable("message", "Correo verificado correctamente");
            return templateEngine.process("verification-success", context);
        } catch (AuthenticationException e) {
            context.setVariable("message", e.getMessage());
            return templateEngine.process("verification-fail", context);
        } catch (Exception e) {
            context.setVariable("message", "Fallo en la verificación del correo.");
            return templateEngine.process("verification-fail", context);
        }
    }
    @GetMapping("/verify-reset-password")
    public String verifyResetPassword(@RequestParam("code") String code) {
        Context context = new Context();
        try {
            boolean codeExists = iAuthService.verifyCodeExists(code);
            if (codeExists) {
                return templateEngine.process("reset-password-form",context);
            } else {
                context.setVariable("message", "Fallo en la verificación para cambiar la contraseña.");
                return templateEngine.process("verification-fail", context);
            }
        } catch (AuthenticationException e) {
            context.setVariable("message", e.getMessage());
            return templateEngine.process("verification-fail", context);
        }catch (Exception e) {
            context.setVariable("message", "Fallo en la verificación para cambiar la contraseña.");
            return templateEngine.process("verification-fail", context);
        }
    }
    @PostMapping( "/email-reset-password")
    public ResponseEntity<?> emailResetPassword(@RequestBody Map<String, String> requestBody)
    {
        try {
            String email = requestBody.get("email");
            ApiResponse response = iAuthService.emailResetPassword(email);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return handleAuthenticationException(e);
        }
    }
    @PostMapping( "/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> requestBody) {
        String newPassword = requestBody.get("newPassword");
        String code = requestBody.get("code");
        try {
            ApiResponse response = iAuthService.resetPassword(newPassword,code);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return handleAuthenticationException(e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        try {
            iAuthService.logout(response);
            return ResponseEntity.ok(new ApiResponse("sesión cerrada"));
        } catch (AuthenticationException e) {
            return handleAuthenticationException(e);
        }
    }

    @GetMapping("/isValid")
    public boolean isTokenValid(@CookieValue(value = "AuthToken", required = false) String cookieValue){
        return codeVerificationService.isTokenValid(cookieValue);
    }
}
