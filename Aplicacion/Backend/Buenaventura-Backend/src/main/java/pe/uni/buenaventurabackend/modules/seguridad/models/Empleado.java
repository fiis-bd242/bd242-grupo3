package pe.uni.buenaventurabackend.modules.seguridad.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    private int idEmpleado;
    private String nombre;
    private String dni;
    private String direccion;
    private String estadoCivil;
    private String sexo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private int numeroContacto;
    private String emailContacto;
    private String contraseña;
    private int idCargo;
    private int idAcceso;

    public Empleado(String nombre, String dni, String direccion, String estadoCivil, String sexo,
                    LocalDate fechaNacimiento, LocalDate fechaIngreso, int numeroContacto,
                    String emailContacto, String contraseña, int idCargo) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.numeroContacto = numeroContacto;
        this.emailContacto = emailContacto;
        this.contraseña = contraseña;
        this.idCargo = idCargo;
    }

    public Empleado(String nombre, String dni, String direccion, String estadoCivil, String sexo, LocalDate fechaNacimiento, LocalDate fechaIngreso, int numeroContacto, String emailContacto, String contraseña, int idCargo, int idAcceso) {
    }
}
