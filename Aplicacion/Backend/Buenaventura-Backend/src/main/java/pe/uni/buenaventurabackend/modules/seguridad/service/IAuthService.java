package pe.uni.buenaventurabackend.modules.seguridad.service;

import jakarta.servlet.http.HttpServletResponse;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.models.User;
import pe.uni.buenaventurabackend.modules.seguridad.models.Empleado;
import pe.uni.buenaventurabackend.modules.seguridad.models.IncidenteCibernetico;
import pe.uni.buenaventurabackend.modules.seguridad.models.LoginRequest;
import pe.uni.buenaventurabackend.modules.seguridad.models.RegisterRequest;

import java.util.ArrayList;
import java.util.List;

public interface IAuthService {
    void verify(String code);

    void register(Empleado user);

    ArrayList<Empleado> getAllEmpleados(int offset);

    Empleado login(String email, String contrasena);
    void login(HttpServletResponse httpServletRequest,LoginRequest request);
;
    void logout(HttpServletResponse httpServletResponse);

    void createIncidente(IncidenteCibernetico incidente);

    ApiResponse resetPassword(String newPassword,String code);

    ApiResponse emailResetPassword(String email);

    boolean verifyCodeExists(String code);
}
