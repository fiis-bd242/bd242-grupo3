package pe.uni.buenaventurabackend.modules.seguridad.repository;

import pe.uni.buenaventurabackend.models.User;
import pe.uni.buenaventurabackend.modules.seguridad.models.Empleado;
import pe.uni.buenaventurabackend.modules.seguridad.models.IncidenteCibernetico;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public interface IAuthRepository {
    int register(Empleado user);

    ArrayList<Empleado> readAllEmpleados(int offset);

    User findUserByEmail(String email);

    void saveIncidente(IncidenteCibernetico incidente);
    Empleado login(String email, String contraseña);
    void verifyCode(int userId, Date expirationDate);

    void resetPassword(String encode, int userId, Date issuedAt);

    void verifyExistCode(int userId, Date issuedAt);
}
