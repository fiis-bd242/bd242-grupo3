package pe.uni.buenaventurabackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import pe.uni.buenaventurabackend.models.Role;
import pe.uni.buenaventurabackend.utils.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/planificacion/**", "/api/control/**", "/api/equipos/**", "/api/insumos/**","/api/iperc/**", "/api/seguridad/**", "/api/reportes/**", "/swagger-ui/**", "/v3/api-docs/**","/swagger-ui.html").permitAll()
                                .requestMatchers("/api/pruebas/admin_principal").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/api/pruebas/admins").hasAnyAuthority(Role.MANAGER.name(),Role.ADMIN.name())
                                .requestMatchers("/api/pruebas/user").hasAnyAuthority(Role.USER.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
