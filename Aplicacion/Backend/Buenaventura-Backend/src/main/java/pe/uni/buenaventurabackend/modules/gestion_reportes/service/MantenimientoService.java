package pe.uni.buenaventurabackend.modules.gestion_reportes.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.HistorialMantenimientoDTO;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MantenimientoService {

    private final JdbcTemplate jdbcTemplate;

    public List<HistorialMantenimientoDTO> obtenerHistorialMantenimiento(Date fechaInicio, Date fechaFin) {
        String sql = "SELECT " +
                "nombre_tipo AS \"Nombre de la Máquina\", " +
                "e.Nombre AS \"Técnico Responsable\", " +
                "tm.nombre_tipo_mant AS \"Tipo de Mantenimiento\", " +
                "r.Fecha_inicial AS \"Fecha Inicio\", " +
                "r.Fecha_registro AS \"Fecha Final\", " +
                "r.Calificacion, " +
                "r.Observaciones " +
                "FROM " +
                "Registro r " +
                "INNER JOIN " +
                "Mantenimiento m ON r.Id_Act_mantto = m.Id_Act_mantto " +
                "INNER JOIN " +
                "Empleado e ON r.Id_empleado = e.Id_empleado " +
                "INNER JOIN " +
                "Tipo_mantenimiento tm ON m.id_tipo_mant = tm.id_tipo_mant " +
                "INNER JOIN " +
                "Maquina m2 ON m.id_maquina = m2.id_maquina " +
                "INNER JOIN " +
                "tipo_maquina ON tipo_maquina.id_tipo_maquina = m2.id_tipo_maquina " +
                "WHERE " +
                "r.Fecha_inicial BETWEEN ? AND ? " + // Filtro de fechas
                "ORDER BY " +
                "r.Fecha_inicial";

        return jdbcTemplate.query(sql, new Object[]{fechaInicio, fechaFin}, (rs, rowNum) -> {
            HistorialMantenimientoDTO historial = new HistorialMantenimientoDTO();
            historial.setNombreMaquina(rs.getString("Nombre de la Máquina"));
            historial.setTecnicoResponsable(rs.getString("Técnico Responsable"));
            historial.setTipoMantenimiento(rs.getString("Tipo de Mantenimiento"));
            historial.setFechaInicio(rs.getTimestamp("Fecha Inicio"));
            historial.setFechaFinal(rs.getTimestamp("Fecha Final"));
            historial.setCalificacion(rs.getInt("Calificacion"));
            historial.setObservaciones(rs.getString("Observaciones"));
            return historial;
        });
    }
}
