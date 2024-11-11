package pe.uni.buenaventurabackend.modules.seguridad.service;

import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import pe.uni.buenaventurabackend.exception.AuthenticationException;
import pe.uni.buenaventurabackend.models.*;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${url.site}")
    private String urlSite;
    @Value("${jwt.accessTokenCookieName}")
    private String cookieName;

    private final JwtService jwtService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IAuthRepository iAuthRepository;
    private final CodeVerificationService codeVerificationService;

    public void login(HttpServletResponse httpServletResponse, LoginRequest request) {
        User user= iAuthRepository.findUserByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Contraseña incorrecta");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token=jwtService.getToken(user);
        CookieUtil.create(httpServletResponse, cookieName, token, false, -1, "localhost");
    }

    @Override
    public void logout(HttpServletResponse httpServletResponse) {
        SecurityContextHolder.clearContext();
        CookieUtil.clear(httpServletResponse,cookieName,"localhost",false);
    }

    @Override
    public ApiResponse resetPassword(String newPassword, String code) {
        Claims codeToken = codeVerificationService.getDataFromToken(code);
        iAuthRepository.resetPassword(passwordEncoder.encode(newPassword),Integer.parseInt(codeToken.getSubject()),codeToken.getIssuedAt());
        return ApiResponse.builder()
                .message("El cambio de contraseña fue exitoso")
                .build();
    }

    @Override
    public ApiResponse emailResetPassword(String email) {
        try {
            User user=iAuthRepository.findUserByEmail(email);
            String token=codeVerificationService.createToken(String.valueOf(user.getId_user()));

            // Envío de correo electrónico de confirmación
            String subject = "Reseteo de contraseña";
            String template = "reset-password-email";
            String url = urlSite+"/api/auth/verify-reset-password?code=" + token;
            Context context = new Context();
            context.setVariable("url", url);
            emailService.sendEmail(email, subject, template, context);

            return ApiResponse.builder()
                    .message("Se envió correo de verificación")
                    .build();
        } catch (MessagingException e) {
            throw new AuthenticationException("Error al enviar el correo de verificación");
        }
    }

    @Override
    public boolean verifyCodeExists(String code) {
        Claims codeToken = codeVerificationService.getDataFromToken(code);
        iAuthRepository.verifyExistCode(Integer.parseInt(codeToken.getSubject()),codeToken.getIssuedAt());
        return codeToken.getSubject() != null && !codeToken.getSubject().isEmpty();
    }

    public void verify(String code) {
        Claims codeToken=codeVerificationService.getDataFromToken(code);
        iAuthRepository.verifyCode(Integer.parseInt(codeToken.getSubject()),codeToken.getIssuedAt());
    }

    public ApiResponse register(RegisterRequest request) {
        Role role=Role.USER;
        if(Objects.equals(request.getEmail(), adminEmail)){
            role=Role.ADMIN;
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .nombre_user(request.getNombre_user())
                .enabled(false)
                .genero(request.getGenero())
                .id_carrera(request.getId_carrera())
                .role(role)
                .build();

        try {
            int userId=iAuthRepository.register(user);
            String token=codeVerificationService.createToken(String.valueOf(userId));

            // Envío de correo electrónico de confirmación
            String subject = "Gracias por registrarte";
            String template = "registration-email";
            String url = urlSite+"/api/auth/verify-email?code=" + token;
            Context context = new Context();
            context.setVariable("nombre", user.getNombre_user());
            context.setVariable("url", url);
            emailService.sendEmail(user.getEmail(), subject, template, context);

            return ApiResponse.builder()
                    .message("Correo de verificación enviado")
                    .build();
        } catch (MessagingException e) {
            throw new AuthenticationException("Error al enviar el correo de verificación");
        }

    }
}
