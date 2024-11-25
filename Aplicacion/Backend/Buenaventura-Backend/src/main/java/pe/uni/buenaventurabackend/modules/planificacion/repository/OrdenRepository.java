package pe.uni.buenaventurabackend.modules.planificacion.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.planificacion.models.Actividad_empleado;
import pe.uni.buenaventurabackend.modules.planificacion.models.EmpleadoDTO;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoDTON;
import pe.uni.buenaventurabackend.modules.planificacion.models.Orden_de_trabajo;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetalleOrdenRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrdenRepository implements IOrdenRepository {
    private final JdbcTemplate jdbcTemplate;
    private final IActividadRepository iActividadRepository;

    @Override
    public List<String> findAll() {
        String SQL = "SELECT CONCAT('OT-', LPAD(id_orden::TEXT, 4, '0')) " +
                "FROM Orden_de_trabajo;";
        return jdbcTemplate.queryForList(SQL, String.class);
    }

    @Override
    public List<Map<String,Object>> findX(int limit, int offset){
        String sql = "SELECT LPAD(o.id_orden::TEXT, 4, '0') AS id_orden," +
                " CONCAT('MQ-',LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina," +
                " tm.nombre_tipo_mant," +
                " m.fecha_inicio_programado " +
                "FROM orden_de_trabajo o " +
                "INNER JOIN Mantenimiento m " +
                "ON m.id_plan = o.id_orden " +
                "INNER JOIN Tipo_mantenimiento tm " +
                "ON tm.id_tipo_mant = m.id_tipo_mant " +
                "WHERE o.visible = 'Y' " +
                "LIMIT ? " +
                "OFFSET ?-1;";
        return jdbcTemplate.queryForList(sql,limit,offset);
    }

    @Override
    public int conteoOrden(){
        String sql = "SELECT COUNT(*) FROM Orden_de_Trabajo;";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public void nuevaOrden(Orden_de_trabajo orden){
        String sql = "INSERT INTO Orden_de_trabajo (id_orden,descripcion,fecha_orden, visible) " +
                "VALUES (?,?,current_date,?);";
        jdbcTemplate.update(sql,orden.getId_orden(),orden.getDescripcion(), orden.getVisible());
    }

    @Override
    public void nuevaOrdenMant(int id_orden, int id_plan){
        String sql = "UPDATE Mantenimiento " +
                "SET id_orden = ? " +
                "WHERE id_plan = ?;";
        jdbcTemplate.update(sql,id_orden,id_plan);
    }

    @Override
    public void nuevaOrdenActv(Actividad_empleado act, boolean responsable, int id_plan){
        System.out.println("Info del act");
        System.out.println(act.getId_actvempleado());
        System.out.println(act.getId_empleado());
        System.out.println(act.getId_orden());
        System.out.println(id_plan);
        String sql = "";
        if(responsable){
            sql = "INSERT INTO Actividad_empleado (id_actvempleado, nombre_actv, descripcion_actv, fecha_inicio, fecha_fin, id_equipo_soporte, id_empleado, id_estado, id_orden) " +
                    "(SELECT " +
                    "?, " +
                    "'Responsable', " +
                    "'Es responsable de la ejecución completa del mantenimiento y su supervisión', " +
                    "m.fecha_inicio_programado, " +
                    "m.fecha_fin_programado, " +
                    "null, " +
                    "?, " +
                    "1, " +
                    "? " +
                    "FROM Mantenimiento m " +
                    "WHERE id_plan = ? LIMIT 1);";
            jdbcTemplate.update(sql, act.getId_actvempleado(), act.getId_empleado(), act.getId_orden(), id_plan);
        }
        else {
            sql = "INSERT INTO Actividad_empleado (id_actvempleado, nombre_actv, descripcion_actv, fecha_inicio, fecha_fin, id_equipo_soporte, id_empleado, id_estado, id_orden) " +
                    "VALUES (?, null, null, null, null, null, ?, 1, ?);";
            jdbcTemplate.update(sql, act.getId_actvempleado(), act.getId_empleado(), act.getId_orden());
        }
    }

    @Override
    public List<String> findAllVisible(){
        String sql = "SELECT CONCAT('OT-', LPAD(id_orden::TEXT, 4, '0')) " +
                "FROM Orden_de_trabajo " +
                "WHERE visible = 'Y';";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    public DetalleOrdenRequest detalleOrden(int id_orden) {
        String sql = "SELECT " +
                "CONCAT('OT-', LPAD(o.id_orden::TEXT, 4, '0')) AS id_orden, " +
                "o.descripcion, " +
                "est.estado, " +
                "CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')) AS maquina, " +
                "o.fecha_orden, " +
                "tm.nombre_tipo_mant, " +
                "m.fecha_inicio_programado, " +
                "m.fecha_fin_programado, " +
                "responsable.nombre AS responsable, " +
                "CONCAT('PL-', LPAD(p.id_plan::TEXT, 4, '0')) AS id_plan, " +
                "string_agg(DISTINCT CONCAT(emp.id_empleado, '-', emp.nombre), ', ') AS lista_empleados " +
                "FROM Orden_de_Trabajo o " +
                "INNER JOIN Actividad_empleado act ON act.id_orden = o.id_orden " +
                "INNER JOIN Empleado emp ON emp.id_empleado = act.id_empleado " +
                "INNER JOIN Mantenimiento m ON m.id_plan = o.id_orden " +
                "INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Plan_de_mantenimiento p ON p.id_plan = m.id_plan " +
                "INNER JOIN Empleado responsable ON p.empleado_asigna = responsable.id_empleado " +
                "INNER JOIN Estado_mantto est ON est.id_estado = m.id_estado " +
                "WHERE o.id_orden = ? " +
                "GROUP BY " +
                "o.id_orden, o.descripcion, est.estado, m.id_maquina, o.fecha_orden, tm.nombre_tipo_mant, " +
                "m.fecha_inicio_programado, m.fecha_fin_programado, responsable.nombre, p.id_plan " +
                "ORDER BY o.id_orden;";

        return jdbcTemplate.query(sql, new Object[]{id_orden}, rs -> {
            if (rs.next()) {
                DetalleOrdenRequest detalle = new DetalleOrdenRequest();
                detalle.setId_orden(rs.getString("id_orden"));
                detalle.setDescripcion(rs.getString("descripcion"));
                detalle.setEstado(rs.getString("estado"));
                detalle.setMaquina(rs.getString("maquina"));
                detalle.setFecha_orden(rs.getString("fecha_orden"));
                detalle.setNombre_tipo_mant(rs.getString("nombre_tipo_mant"));
                detalle.setFecha_inicio_programado(rs.getString("fecha_inicio_programado"));
                detalle.setFecha_fin_programado(rs.getString("fecha_fin_programado"));
                detalle.setResponsable(rs.getString("responsable"));
                detalle.setId_plan(rs.getString("id_plan"));

                // Procesar lista de empleados
                String listaEmpleados = rs.getString("lista_empleados");
                if (listaEmpleados != null) {
                    List<EmpleadoDTO> empleados = Arrays.stream(listaEmpleados.split(","))
                            .map(empleado -> {
                                String[] parts = empleado.split("-");
                                EmpleadoDTO emp = new EmpleadoDTO();
                                emp.setId_empleado(Integer.parseInt(parts[0].trim()));
                                emp.setNombre(parts[1].trim());
                                return emp;
                            })
                            .collect(Collectors.toList());
                    detalle.setLista_empleados(empleados);
                }

                return detalle;
            }
            return null;
        });
    }


    @Override
    public void guardarOrden(int id_orden, String descripcion, int id_plan){
        String sql = "UPDATE Orden_de_Trabajo " +
                "SET descripcion = ? " +
                "WHERE id_orden = ?; " +

                "UPDATE Mantenimiento " +
                "SET id_plan = ? " +
                "WHERE id_orden = ?;";
        jdbcTemplate.update(sql, descripcion, id_orden, id_plan, id_orden);

        sql = "DELETE FROM Actividad_Empleado " +
                "WHERE id_orden = ?;";
        jdbcTemplate.update(sql, id_orden);
    }

    @Override
    public void borrarOrden(int id_orden){
        String sql = "UPDATE Orden_de_trabajo " +
                "SET visible = 'N' " +
                "WHERE id_orden = ?;";
        jdbcTemplate.update(sql, id_orden);
    }

    @Override
    public List<EmpleadoDTO> listaEmpleados() {
        String sql = "SELECT id_empleado, nombre FROM Empleado";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EmpleadoDTO detalle = new EmpleadoDTO();
            detalle.setId_empleado(rs.getInt("id_empleado"));
            detalle.setNombre(rs.getString("nombre"));
            return detalle;
        });
    }

}
