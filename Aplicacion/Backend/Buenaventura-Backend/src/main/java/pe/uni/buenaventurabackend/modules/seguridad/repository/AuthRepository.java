package pe.uni.buenaventurabackend.modules.seguridad.repository;

import lombok.RequiredArgsConstructor;
import pe.uni.buenaventurabackend.exception.AuthenticationException;
import pe.uni.buenaventurabackend.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.seguridad.models.Empleado;
import pe.uni.buenaventurabackend.modules.seguridad.models.IncidenteCibernetico;

import java.time.LocalDate;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AuthRepository implements IAuthRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int register(Empleado user) {
        String sql = "INSERT INTO Empleado (Id_empleado, Nombre, DNI, Direccion, Estado_civil, Sexo, Fecha_Nacimiento, Fecha_ingreso, Numero_contacto, Email_contacto, Contraseña, Id_cargo, Id_acceso) "
                + "VALUES ((SELECT MAX(id_empleado) FROM Empleado) + 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 3)";

        return jdbcTemplate.update(sql,
                user.getNombre(),
                user.getDni(),
                user.getDireccion(),
                user.getEstadoCivil(),
                user.getSexo(),
                user.getFechaNacimiento(),
                user.getFechaIngreso(),
                user.getNumeroContacto(),
                user.getEmailContacto(),
                user.getContraseña(),
                user.getIdCargo()
        );
    }

    @Override
    public ArrayList<Empleado> readAllEmpleados(int offset) {
        String sql = "SELECT * FROM Empleado LIMIT 7 OFFSET ?";

        List<Empleado> empleados = jdbcTemplate.query(sql, new Object[]{(7 * (offset - 1))}, (rs, rowNum) -> {
            int idEmpleado = rs.getInt("Id_empleado");
            String nombre = rs.getString("Nombre");
            String dni = rs.getString("DNI");
            String direccion = rs.getString("Direccion");
            String estadoCivil = rs.getString("Estado_civil");
            String sexo = rs.getString("Sexo");
            LocalDate fechaNacimiento = rs.getDate("Fecha_Nacimiento") != null ? rs.getDate("Fecha_Nacimiento").toLocalDate() : null;
            LocalDate fechaIngreso = rs.getDate("Fecha_ingreso") != null ? rs.getDate("Fecha_ingreso").toLocalDate() : null;
            int numeroContacto = rs.getInt("Numero_contacto");
            String emailContacto = rs.getString("Email_contacto");
            String contraseña = rs.getString("Contraseña");
            int idCargo = rs.getInt("Id_cargo");
            int idAcceso = rs.getInt("Id_acceso");

            return new Empleado(
                    idEmpleado,
                    nombre,
                    dni,
                    direccion,
                    estadoCivil,
                    sexo,
                    fechaNacimiento,
                    fechaIngreso,
                    numeroContacto,
                    emailContacto,
                    contraseña,
                    idCargo,
                    idAcceso
            );
        });

        return new ArrayList<>(empleados);
    }

    public void saveIncidente(IncidenteCibernetico incidente) {
        String sql = "INSERT INTO Incidente_Cibernetico (descripcion_incidente, fecha, criticidad, usuario_involucrado, medidas_tomadas) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, incidente.getDescripcionIncidente(), incidente.getFecha(),
                incidente.getCriticidad(), incidente.getUsuarioInvolucrado(), incidente.getMedidasTomadas());
    }

    public Empleado login(String email, String contraseña) {
        String sql = "SELECT * FROM empleado WHERE email_contacto = ? AND contraseña = ?";

        Empleado empleado = jdbcTemplate.queryForObject(sql, new Object[]{email, contraseña}, (rs, rowNum) -> {
            Empleado e = new Empleado();
            e.setIdEmpleado(rs.getInt("id_empleado"));
            e.setNombre(rs.getString("nombre"));
            e.setDni(rs.getString("dni"));
            e.setDireccion(rs.getString("direccion"));
            e.setEstadoCivil(rs.getString("estado_civil"));
            e.setSexo(rs.getString("sexo"));
            e.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            e.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
            e.setNumeroContacto(rs.getInt("numero_contacto"));
            e.setEmailContacto(rs.getString("email_contacto"));
            e.setContraseña(rs.getString("contraseña"));
            e.setIdCargo(rs.getInt("id_cargo"));
            e.setIdAcceso(rs.getInt("id_acceso"));
            return e;
        });

        return empleado;
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

