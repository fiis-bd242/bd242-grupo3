package pe.uni.buenaventurabackend.modules.gestion_equipos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EquipoSoporteService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> obtenerEquipos(){
        List<Map<String, Object>> lista;
        String sql = "SELECT\n" +
                "\tes.id_equipo_soporte AS ID,\n" +
                "\tes.nombre_equipo_soporte AS Nombre,\n" +
                "\ttes.nombre_tipo AS Tipo,\n" +
                "\tees.nombre_estado AS Estado,\n" +
                "\tdes.nombre_disponibilidad AS Disponibilidad\n" +
                "FROM equipo_de_soporte es\n" +
                "INNER JOIN tipo_equipo_soporte tes\n" +
                "ON es.id_tipo = tes.id_tipo\n" +
                "INNER JOIN estado_equipo_soporte ees\n" +
                "ON es.id_estado = ees.id_estado\n" +
                "INNER JOIN disponibilidad_equipo_soporte des\n" +
                "ON es.id_disponibilidad = des.id_disponibilidad";
        lista = jdbcTemplate.queryForList(sql);
        return lista;
    }

    public List<Map<String, Object>> BuscarEquipoSoporte(String parametro){
        String sql = "SELECT " +
                "es.id_equipo_soporte AS id, " +
                "es.nombre_equipo_soporte AS nombre, " +
                "tes.nombre_tipo AS tipo, " +
                "ees.nombre_estado AS estado, " +
                "des.nombre_disponibilidad AS disponibilidad " +
                "FROM equipo_de_soporte es " +
                "INNER JOIN tipo_equipo_soporte tes ON es.id_tipo = tes.id_tipo " +
                "INNER JOIN estado_equipo_soporte ees ON ees.id_estado = es.id_estado " +
                "INNER JOIN disponibilidad_equipo_soporte des ON des.id_disponibilidad = es.id_disponibilidad " +
                "INNER JOIN almacen a ON a.id_almacen = es.id_almacen " +
                "WHERE " +
                "CAST(es.id_equipo_soporte AS TEXT) ILIKE ? OR " +
                "es.nombre_equipo_soporte ILIKE ? OR " +
                "tes.nombre_tipo ILIKE ? OR " +
                "ees.nombre_estado ILIKE ? OR " +
                "des.nombre_disponibilidad ILIKE ? OR " +
                "a.direccion ILIKE ? OR " +
                "CAST(es.horas_uso AS TEXT) ILIKE ?";

        String searchParam = "%" + parametro + "%";

        return jdbcTemplate.queryForList(sql, searchParam, searchParam, searchParam, searchParam, searchParam, searchParam, searchParam);
    }

    public Map<String, Object> obtenerDetalleEquipoSoporte(int idEquipoSoporte) {
        Map<String, Object> detalleEquipo = new HashMap<>();

        // Consulta para obtener los datos básicos (ID, Nombre, Tipo, Estado)
        String sqlDatosBasicos = """
            SELECT
            	es.id_equipo_soporte AS id,
            	es.nombre_equipo_soporte AS nombre,
            	tes.nombre_tipo AS tipo,
            	ees.nombre_estado AS estado
            FROM equipo_de_soporte es
            INNER JOIN tipo_equipo_soporte tes
            ON es.id_tipo = tes.id_tipo
            INNER JOIN estado_equipo_soporte ees
            ON es.id_estado = ees.id_estado
            WHERE es.id_equipo_soporte = ?
        """;

        Map<String, Object> datosBasicos = jdbcTemplate.queryForMap(sqlDatosBasicos, idEquipoSoporte);
        detalleEquipo.putAll(datosBasicos);

        // Consulta para las horas de uso
        String sqlHorasUso = "SELECT horas_uso FROM equipo_de_soporte WHERE id_equipo_soporte = ?";
        Integer horasUso = jdbcTemplate.queryForObject(sqlHorasUso, new Object[]{idEquipoSoporte}, Integer.class);
        detalleEquipo.put("horasUso", horasUso);

        // Consulta para la descripción
        String sqlDescripcion = "SELECT descripcion FROM equipo_de_soporte WHERE id_equipo_soporte = ?";
        String descripcion = jdbcTemplate.queryForObject(sqlDescripcion, new Object[]{idEquipoSoporte}, String.class);
        detalleEquipo.put("descripcion", descripcion);

        // Consulta para el mantenimiento asignado
        String sqlMantto = """
        SELECT m.id_act_mantto
        FROM equipo_de_soporte es
        INNER JOIN equiposxmantenimiento esm ON esm.id_equipo_soporte = es.id_equipo_soporte
        INNER JOIN mantenimiento m ON m.id_act_mantto = esm.id_act_mantto
        WHERE es.id_equipo_soporte = ?
        AND (m.id_estado = 3 OR m.id_estado = 4)
    """;
        try {
            Integer manttoAsignado = jdbcTemplate.queryForObject(sqlMantto, new Object[]{idEquipoSoporte}, Integer.class);
            detalleEquipo.put("mttoAsignado", manttoAsignado);
        } catch (Exception e) {
            detalleEquipo.put("mttoAsignado", "---"); // Si no hay mantenimiento asignado
        }

        return detalleEquipo;
    }
}
