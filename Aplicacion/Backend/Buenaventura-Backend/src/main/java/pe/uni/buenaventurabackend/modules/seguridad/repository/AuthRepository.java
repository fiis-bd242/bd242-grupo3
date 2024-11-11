package pe.uni.buenaventurabackend.modules.seguridad.repository;

import lombok.RequiredArgsConstructor;
import pe.uni.buenaventurabackend.exception.AuthenticationException;
import pe.uni.buenaventurabackend.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class AuthRepository implements IAuthRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int register(User user) {
        /*
        int userId = -1;
        String img_user="img_user";
        String SQL_SELECT = "SELECT id_empleado FROM empleado WHERE email = ?";
        String SQL_INSERT = "INSERT INTO empleado(nombre_user,email,password,role,id_carrera,genero,img_user,exp_code,enabled) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            Map<String, Object> userMap = jdbcTemplate.queryForMap(SQL_SELECT, user.getEmail());
            Integer existingUserId = (Integer) userMap.get("id_user");
            boolean existingUserEnabled = (boolean) userMap.get("enabled");
            String existingUserExpCode= (String) userMap.get("exp_code");
            if (existingUserId != null) {
                if (existingUserEnabled) {
                    throw new AuthenticationException("El usuario ya existe");
                }
                Date expirationDate = new Date(Long.parseLong(existingUserExpCode) * 1000L);
                if (expirationDate.after(new Date())) {
                    throw new AuthenticationException("El código de verificación no se ha verificado");
                }
                return existingUserId;
            }
        } catch (EmptyResultDataAccessException ignored) {
        } catch (DataAccessException e) {
            throw new AuthenticationException("Error al buscar el usuario existente en la base de datos");
        }
        try {
            Date exp_code = new Date(System.currentTimeMillis()+1000*60*60);
            jdbcTemplate.update(SQL_INSERT,
                    user.getNombre_user(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole().name(),
                    user.getId_carrera(),
                    user.getGenero(),
                    img_user,
                    String.valueOf(exp_code.getTime() / 1000),
                    user.getEnabled());

            Map<String, Object> userMap = jdbcTemplate.queryForMap(SQL_SELECT, user.getEmail());
            userId= (Integer) userMap.get("id_user");
        }catch (DataAccessException e) {
            throw new AuthenticationException("Error en el servidor al registrar el usuario.");
        }

         */
        return 0;

    }

    @Override
    public User findUserByEmail(String email) {
        /*
        String SQL = "SELECT * FROM usuario WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(SQL, new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
            if (!Objects.requireNonNull(user).getEnabled()) {
                throw new AuthenticationException("El email no ha sido confirmado.");
            }
            return user;
        } catch (EmptyResultDataAccessException ex) {
            throw new AuthenticationException("El usuario no existe.");
        }catch (DataAccessException ex) {
            throw new AuthenticationException("Error en el servidor al buscar el usuario por correo electrónico.");
        }
         */
        return null;
    }

    @Override
    public void verifyCode(int userId, Date issuedAt) {
        /*
        String SQL_SELECT= "SELECT exp_code FROM usuario WHERE id_user = ?";
        String SQL_UPDATE = "UPDATE usuario SET enabled = true, exp_code = ? WHERE id_user = ?";

        try {
            String existingDateExpStr = jdbcTemplate.queryForObject(SQL_SELECT, new Object[]{userId}, String.class);
            Date existingDateExp = new Date(Long.parseLong(existingDateExpStr) * 1000L);
            if (existingDateExp.equals(issuedAt)||existingDateExp.before(issuedAt)) {
                throw new AuthenticationException("El código ya ha sido verificado.");
            }
            jdbcTemplate.update(SQL_UPDATE, String.valueOf(issuedAt.getTime() / 1000) , userId);
        } catch (EmptyResultDataAccessException ex) {
            throw new AuthenticationException("No se encontró ningún usuario con el ID proporcionado.");
        } catch (DataAccessException e) {
            throw new AuthenticationException("Error en el servidor al verificar el código.");
        }
         */
    }

    @Override
    public void resetPassword(String newPassword, int userId, Date issuedAt) {
        /*
        String SQL_SELECT = "SELECT exp_code FROM usuario WHERE id_user = ?";
        String updateSQL = "UPDATE usuario SET exp_code=?, password = ? WHERE id_user = ?";
        try {
            String existingDateExpStr = jdbcTemplate.queryForObject(SQL_SELECT, new Object[]{userId}, String.class);
            Date existingDateExp = new Date(Long.parseLong(existingDateExpStr) * 1000L);
            if (existingDateExp.equals(issuedAt)||existingDateExp.after(issuedAt)) {
                throw new AuthenticationException("El código ya ha sido verificado.");
            }
            jdbcTemplate.update(updateSQL,String.valueOf(issuedAt.getTime() / 1000), newPassword, userId);
        } catch (EmptyResultDataAccessException ex) {
            throw new AuthenticationException("El código de verificación no existe.");
        } catch (DataAccessException e) {
            throw new AuthenticationException("Error en el servidor al restablecer la contraseña.");
        }
         */
    }

    @Override
    public void verifyExistCode(int userId, Date issuedAt) {
        /*
        String SQL_SELECT= "SELECT enabled,exp_code FROM usuario WHERE id_user = ?";
        try {
            Map<String, Object> userMap = jdbcTemplate.queryForMap(SQL_SELECT, userId);
            boolean existingUserEnabled = (boolean) userMap.get("enabled");
            String existingUserExpCode= (String) userMap.get("exp_code");

            Date existingDateExp = new Date(Long.parseLong(existingUserExpCode) * 1000L);
            if(!existingUserEnabled){
                throw new AuthenticationException("El email no ha sido verificado.");
            }
            if (existingDateExp.equals(issuedAt)||existingDateExp.after(issuedAt)) {
                throw new AuthenticationException("El código ya ha sido verificado.");
            }
        } catch (EmptyResultDataAccessException ex) {
            throw new AuthenticationException("No se encontró ningún usuario con el ID proporcionado.");
        } catch (DataAccessException e) {
            throw new AuthenticationException("Error en el servidor al verificar el código.");
        }
        */
    }
}

