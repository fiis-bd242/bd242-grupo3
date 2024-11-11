package pe.uni.buenaventurabackend.modules.seguridad.service;

import jakarta.servlet.http.HttpServletResponse;
import pe.uni.buenaventurabackend.models.ApiResponse;
import pe.uni.buenaventurabackend.modules.seguridad.models.LoginRequest;
import pe.uni.buenaventurabackend.modules.seguridad.models.RegisterRequest;

public interface IAuthService {
    void verify(String code);

    ApiResponse register(RegisterRequest request);

    void login(HttpServletResponse httpServletRequest,LoginRequest request);

    void logout(HttpServletResponse httpServletResponse);

    ApiResponse resetPassword(String newPassword,String code);

    ApiResponse emailResetPassword(String email);

    boolean verifyCodeExists(String code);
}
