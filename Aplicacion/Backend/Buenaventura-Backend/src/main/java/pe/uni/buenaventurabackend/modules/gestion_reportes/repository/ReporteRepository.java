package pe.uni.buenaventurabackend.modules.gestion_reportes.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.Reporte;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDTO;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.ReporteDia;

import java.util.Date;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class ReporteRepository implements IReporteRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Reporte> findAll() {
        String SQL = "SELECT * FROM reportes";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Reporte.class));
    }

    @Override
    public List<ReporteDia> obtenerReportes() {
        String sql = "SELECT " +
                "TO_CHAR(r.Fecha_registro, 'HH24:MI') AS Hora_registro, " +
                "'M00' || r.Id_Act_mantto AS Codigo_actividad, " +
                "nombre_tipo AS Nombre_equipo, " +
                "e.Nombre AS Tecnico_responsable, " +
                "tm.nombre_tipo_mant AS Tipo_mantenimiento, " +
                "TO_CHAR(r.Fecha_registro, 'YYYY-MM-DD') AS Fecha_dia " +
                "FROM Registro r " +
                "INNER JOIN Mantenimiento m ON r.Id_Act_mantto = m.Id_Act_mantto " +
                "INNER JOIN Plan_de_mantenimiento pm ON m.Id_plan = pm.Id_plan " +
                "INNER JOIN Empleado e ON r.Id_empleado = e.Id_empleado " +
                "INNER JOIN Maquina ma ON m.Id_maquina = ma.Id_maquina " +
                "INNER JOIN " +
                "tipo_maquina tma ON ma.id_tipo_maquina = tma.id_tipo_maquina "+
                "INNER JOIN Tipo_mantenimiento tm ON m.id_tipo_mant = tm.id_tipo_mant " +
                "WHERE DATE(r.Fecha_registro) = DATE(CURRENT_DATE);";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReporteDia.class));
    }

    @Override
    public int insertarReporte(int idJefe) {
        String sql = "INSERT INTO Reportes (Fecha_reporte, Id_estado_reporte, id_jefe) VALUES (CURRENT_TIMESTAMP, 1, ?) RETURNING id_reporte;";
        return jdbcTemplate.queryForObject(sql, new Object[]{idJefe}, Integer.class);
    }

    @Override
    public List<ReporteDTO> obtenerReportesPorFecha(Date fechaInicial, Date fechaFinal, Integer offset) {
        String sql = "SELECT " +
                "'R' || TO_CHAR(rep.Fecha_reporte, 'YYYYMMDD') AS Codigo_Registro, " +
                "TO_CHAR(rep.Fecha_reporte, 'YYYY-MM-DD') AS Fecha_del_Dia, " +
                "registros_por_dia.Numero_Registros_Dia, " +
                "rep.Fecha_reporte, " +
                "est.nombre_estado AS Estado_Reporte, " +
                "nombre as jefe " +
                "FROM " +
                "Reportes AS rep " +
                "JOIN " +
                "( " +
                "SELECT " +
                "TO_CHAR(Fecha_registro, 'YYYY-MM-DD') AS Fecha_del_Dia, " +
                "COUNT(Id_registro) AS Numero_Registros_Dia " +
                "FROM " +
                "Registro " +
                "WHERE " +
                "Fecha_registro >= ? " +
                "AND Fecha_registro <= ? " +
                "GROUP BY " +
                "TO_CHAR(Fecha_registro, 'YYYY-MM-DD') " +
                ") AS registros_por_dia " +
                "ON " +
                "TO_CHAR(rep.Fecha_reporte, 'YYYY-MM-DD') = registros_por_dia.Fecha_del_Dia " +
                "JOIN " +
                "Estado_Reporte AS est ON rep.id_estado_reporte = est.id_estado_reporte " +
                "INNER JOIN " +
                "empleado ON empleado.id_empleado = rep.id_jefe "+
                "ORDER BY " +
                "Fecha_del_Dia "+
                "LIMIT 7 OFFSET (7 * (? - 1))";

        // Ejecutar la consulta con parámetros y mapear el resultado
        return jdbcTemplate.query(sql, new Object[]{fechaInicial, fechaFinal, offset}, (rs, rowNum) -> {
            ReporteDTO reporte = new ReporteDTO();
            reporte.setCodigoRegistro(rs.getString("Codigo_Registro"));
            reporte.setFechaDelDia(rs.getString("Fecha_del_Dia"));
            reporte.setNumeroRegistrosDia(rs.getInt("Numero_Registros_Dia"));
            reporte.setFechaReporte(rs.getTimestamp("Fecha_reporte"));
            reporte.setEstadoReporte(rs.getString("Estado_Reporte"));
            reporte.setJefe(rs.getString("jefe"));
            return reporte;
        });
    }

    @Override
    public int actualizarEstadoReporte(Date fechaReporte, Integer nuevoEstado) {
        String sql = "UPDATE Reportes SET id_estado_reporte = ? WHERE fecha_reporte = ?";
        return jdbcTemplate.update(sql, nuevoEstado, fechaReporte);
    }
}
