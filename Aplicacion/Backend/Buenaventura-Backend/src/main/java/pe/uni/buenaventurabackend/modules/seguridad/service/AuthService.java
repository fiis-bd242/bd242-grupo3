package pe.uni.buenaventurabackend.modules.seguridad.service;

import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import pe.uni.buenaventurabackend.exception.AuthenticationException;
import pe.uni.buenaventurabackend.models.*;
import pe.uni.buenaventurabackend.modules.seguridad.models.Empleado;
import pe.uni.buenaventurabackend.modules.seguridad.models.IncidenteCibernetico;
import pe.uni.buenaventurabackend.modules.seguridad.models.LoginRequest;
import pe.uni.buenaventurabackend.modules.seguridad.models.RegisterRequest;
import pe.uni.buenaventurabackend.modules.seguridad.repository.IAuthRepository;
import pe.uni.buenaventurabackend.utils.CodeVerificationService;
import pe.uni.buenaventurabackend.utils.EmailService;
import pe.uni.buenaventurabackend.utils.CookieUtil;
import pe.uni.buenaventurabackend.utils.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.uni.buenaventurabackend.models.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IAuthRepository authRepository;

    @Override
    public void verify(String code) {

    }

    @Override
    public void register(Empleado user) {
        authRepository.register(user);
    }

    @Override
    public ArrayList<Empleado> getAllEmpleados(int offset) {
        return authRepository.readAllEmpleados(offset);
    }

    @Override
    public Empleado login(String email, String contrasena) {
        return authRepository.login(email,contrasena);
    }

    public void createIncidente(IncidenteCibernetico incidente) {
        authRepository.saveIncidente(incidente);
    }

    @Override
    public void login(HttpServletResponse httpServletRequest, LoginRequest request) {

    }

    @Override
    public void logout(HttpServletResponse httpServletResponse) {

    }

    @Override
    public ApiResponse resetPassword(String newPassword, String code) {
        return null;
    }

    @Override
    public ApiResponse emailResetPassword(String email) {
        return null;
    }

    @Override
    public boolean verifyCodeExists(String code) {
        return false;
    }
}
