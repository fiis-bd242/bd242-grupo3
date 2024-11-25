package pe.uni.buenaventurabackend.modules.gestion_insumos.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.uni.buenaventurabackend.modules.gestion_insumos.models.Insumo;
import java.util.List;
@Repository
public class Insumo_Repository implements IInsumo_Repository{
    private final JdbcTemplate jdbcTemplate;

    public Insumo_Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Insumo> obtenerInsumosConLimiteYOffset(int limite, int offset) {
        String sql = """
                SELECT i.Id_insumo, i.Nombre, i.Cantidad
                FROM Insumo as i
                LIMIT ? OFFSET ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Insumo insumo = new Insumo();
            insumo.setId_insumo(rs.getInt("Id_insumo"));
            insumo.setNombre(rs.getString("Nombre"));
            insumo.setCantidad(rs.getInt("Cantidad"));
            return insumo;
        }, limite, offset);
    }
    @Override
    public List<Insumo> obtenerInsumosPorNombre(String nombre) {
        String sql = """
                SELECT i.id_insumo, i.nombre, i.cantidad
                FROM Insumo as i
                WHERE i.nombre = ?
                """;

        // Ejecución de la consulta y mapeo de resultados
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Insumo insumo = new Insumo();
            insumo.setId_insumo(rs.getInt("id_insumo"));
            insumo.setNombre(rs.getString("nombre"));
            insumo.setCantidad(rs.getInt("cantidad"));
            return insumo;
        }, nombre);
    }
}
