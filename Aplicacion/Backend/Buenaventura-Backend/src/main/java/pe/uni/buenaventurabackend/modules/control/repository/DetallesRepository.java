package pe.uni.buenaventurabackend.modules.control.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.control.models.requests.DetallesRequest;
import pe.uni.buenaventurabackend.modules.planificacion.models.InsumoDTON;
import pe.uni.buenaventurabackend.modules.planificacion.models.requests.DetallePlanRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DetallesRepository implements IDetallesRepository{
    private final JdbcTemplate jdbcTemplate;

    public DetallesRequest detalles(int id_plan){
        String sql = "SELECT CONCAT('PL-', LPAD(p.id_plan::TEXT, 4, '0')) AS id_plan, " +
                "tm.nombre_tipo_mant, " +
                "CONCAT('MQ-', LPAD(m.id_maquina::TEXT, 4, '0')) AS id_maquina, " +
                "creador.nombre AS creador, " +
                "est.estado, " +
                "p.fecha_plan, " +
                "m.fecha_inicio_programado, " +
                "m.fecha_fin_programado, " +
                "responsable.nombre AS responsable, " +
                "crit.nivel AS criticidad, " +
                "p.descripcion, " +
                "CONCAT('OT-', LPAD(o.id_orden::TEXT, 4, '0')) AS id_orden, " +

                "(SELECT string_agg(DISTINCT es.nombre_equipo_soporte, ',') " +
                "FROM EquipoSXMantenimiento esm " +
                "INNER JOIN Equipo_de_Soporte es ON es.id_equipo_soporte = esm.id_equipo_soporte " +
                "WHERE esm.id_act_mantto = m.id_act_mantto) AS listaEquipos, " +

                "(SELECT string_agg(DISTINCT i.nombre || ' (Cantidad: ' || im.cantidad || ')', ', ') " +
                "FROM InsumoXMantenimiento im " +
                "INNER JOIN Insumo i ON i.id_insumo = im.id_insumo " +
                "WHERE im.id_act_mantto = m.id_act_mantto) AS listaInsumos " +
                "FROM Plan_de_mantenimiento p " +
                "INNER JOIN Mantenimiento m ON m.id_plan = p.id_plan " +
                "INNER JOIN Tipo_mantenimiento tm ON tm.id_tipo_mant = m.id_tipo_mant " +
                "INNER JOIN Empleado creador ON creador.id_empleado = p.empleado_asigna " +
                "INNER JOIN Orden_de_trabajo o ON o.id_orden = m.id_orden " +
                "INNER JOIN Actividad_empleado act ON act.id_orden = o.id_orden " +
                "INNER JOIN Empleado responsable ON responsable.id_empleado = act.id_empleado " +
                "INNER JOIN Estado_mantto est ON m.id_estado = est.id_estado " +
                "INNER JOIN Criticidad crit ON crit.id_criticidad = p.id_criticidad " +
                "WHERE act.nombre_actv = 'Responsable' AND p.id_plan = ? AND m.id_estado != 8;";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                DetallesRequest detalles = new DetallesRequest();
                detalles.setId_plan(rs.getString("id_plan"));
                detalles.setNombre_tipo_mant(rs.getString("nombre_tipo_mant"));
                detalles.setId_maquina(rs.getString("id_maquina"));
                detalles.setCreador(rs.getString("creador"));
                detalles.setEstado(rs.getString("estado"));
                detalles.setFecha_plan(rs.getString("fecha_plan"));
                detalles.setFecha_inicio_programado(rs.getString("fecha_inicio_programado"));
                detalles.setFecha_fin_programado(rs.getString("fecha_fin_programado"));
                detalles.setResponsable(rs.getString("responsable"));
                detalles.setCriticidad(rs.getString("criticidad"));
                detalles.setId_orden(rs.getString("id_orden"));

                // Convertir lista de equipos y lista de insumos en arrays separados
                String listaEquipos = rs.getString("listaEquipos");
                if (listaEquipos != null) {
                    detalles.setListaEquipos(List.of(listaEquipos.split(",")));
                }

                String listaInsumos = rs.getString("listaInsumos");
                if (listaInsumos != null) {
                    List<InsumoDTON> insumos = Arrays.stream(listaInsumos.split(","))
                            .map(insumo -> {
                                String[] parts = insumo.split("\\(Cantidad: ");
                                String nombre_insumo = parts[0].trim();
                                int cantidad = Integer.parseInt(parts[1].replace(")", "").trim());
                                return new InsumoDTON(nombre_insumo, cantidad);
                            })
                            .collect(Collectors.toList());
                    detalles.setListaInsumos(insumos);
                }

                return detalles;
            }
            return null;
        }, id_plan);
    }
}
