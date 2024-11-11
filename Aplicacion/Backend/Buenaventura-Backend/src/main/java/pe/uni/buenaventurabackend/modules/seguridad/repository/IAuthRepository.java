package pe.uni.buenaventurabackend.modules.seguridad.repository;

import pe.uni.buenaventurabackend.models.User;

import java.util.Date;

public interface IAuthRepository {
    int register(User user);

    User findUserByEmail(String email);

    void verifyCode(int userId, Date expirationDate);

    void resetPassword(String encode, int userId, Date issuedAt);

    void verifyExistCode(int userId, Date issuedAt);
}
