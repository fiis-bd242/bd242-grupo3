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
public class PedidoCompraService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> obtenerPedidos(){
        String sql = "SELECT\n" +
                "\tpc.id_pedido_compra AS id,\n" +
                "\te.nombre AS empleado,\n" +
                "\ttu.tipo_urgencia AS urgencia,\n" +
                "\tep.nombre_estado_pedido AS estado\n" +
                "FROM pedido_compra pc\n" +
                "INNER JOIN empleado e\n" +
                "ON e.id_empleado = pc.id_empleado\n" +
                "INNER JOIN tipo_urgencia tu\n" +
                "ON pc.id_urgencia = tu.id_urgencia\n" +
                "INNER JOIN estado_pedido ep\n" +
                "ON pc.id_estado_pedido = ep.id_estado_pedido";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> buscarPedidos(String parametro){
        String sql = "SELECT\n" +
                "\tpc.id_pedido_compra AS id,\n" +
                "\te.nombre AS empleado,\n" +
                "    tu.tipo_urgencia AS urgencia,\n" +
                "    ep.nombre_estado_pedido AS estado\n" +
                "FROM pedido_compra pc\n" +
                "INNER JOIN empleado e ON e.id_empleado = pc.id_empleado\n" +
                "INNER JOIN tipo_urgencia tu ON tu.id_urgencia = pc.id_urgencia\n" +
                "INNER JOIN estado_pedido ep ON ep.id_estado_pedido = pc.id_estado_pedido\n" +
                "INNER JOIN tipo_producto tp ON tp.id_tipo_producto = pc.id_tipo_producto\n" +
                "WHERE\n" +
                "\tCAST(pc.id_pedido_compra AS TEXT) ILIKE ? OR\n" +
                "\te.nombre ILIKE ? OR\n" +
                "\ttu.tipo_urgencia ILIKE ? OR\n" +
                "\tep.nombre_estado_pedido ILIKE ? OR\n" +
                "\ttp.nombre_tipo_producto ILIKE ? OR\n" +
                "\tpc.descripción ILIKE ? OR\n" +
                "\ttp.nombre_tipo_producto ILIKE ?";
        String searchParam = "%" + parametro + "%";
        return jdbcTemplate.queryForList(sql, searchParam, searchParam, searchParam, searchParam, searchParam, searchParam, searchParam);
    }

    public Map<String, Object> obtenerDetallePedido(int idPedido){

        Map<String, Object> detallePedido = new HashMap<>();

        String sql = "SELECT\r\n"
                    + "	tp.nombre_tipo_producto AS \"tipo de producto\"\r\n"
                    + "FROM pedido_compra pc\r\n"
                    + "INNER JOIN tipo_producto tp\r\n"
                    + "ON tp.id_tipo_producto = pc.id_tipo_producto\r\n"
                    + "WHERE pc.id_pedido_compra = ?";
        String tipoProducto = jdbcTemplate.queryForObject(sql, String.class, idPedido);
        detallePedido.put("tipoProducto", tipoProducto);

        sql = "SELECT\r\n"
            + "	descripción AS Descripcion\r\n"
            + "FROM pedido_compra\r\n"
            + "WHERE id_pedido_compra = ?";
        String descripcion = jdbcTemplate.queryForObject(sql, String.class, idPedido);
        detallePedido.put("descripcion", descripcion);

        if(tipoProducto.equals("Equipos de Soporte")){
            sql = "SELECT " +
                    "   es.nombre_equipo_soporte AS nombre, " +
                    "   dpc.cantidad AS cantidad, " +
                    "   dpc.precio_unitario AS \"precioUnitario\" " +
                    "FROM detalle_pedido_compra dpc " +
                    "INNER JOIN equipo_de_soporte es ON es.id_equipo_soporte = dpc.id_producto " +
                    "INNER JOIN pedido_compra pc ON pc.id_pedido_compra = dpc.id_pedido_compra " +
                    "WHERE pc.id_tipo_producto = 2 " +
                    "AND dpc.id_pedido_compra = ?";

            List<Map<String, Object>> listaProductos = jdbcTemplate.queryForList(sql, idPedido);
            detallePedido.put("productos", listaProductos);
        }
        else {
            sql = "SELECT " +
                    "   i.nombre AS nombre, " +
                    "   dpc.cantidad AS cantidad, " +
                    "   dpc.precio_unitario AS \"precioUnitario\" " +
                    "FROM detalle_pedido_compra dpc " +
                    "INNER JOIN insumo i ON i.id_insumo = dpc.id_producto " +
                    "INNER JOIN pedido_compra pc ON pc.id_pedido_compra = dpc.id_pedido_compra " +
                    "WHERE pc.id_tipo_producto = 1 " +
                    "AND dpc.id_pedido_compra = ?";

            // Obtener la lista de productos
            List<Map<String, Object>> listaProductos = jdbcTemplate.queryForList(sql, idPedido);
            detallePedido.put("productos", listaProductos);
        }

        return detallePedido;
    }

    public boolean cancelarPedido(int idPedido) {
        // SQL para actualizar el estado del pedido
        String sql = "UPDATE pedido_compra SET id_estado_pedido = 5 WHERE id_pedido_compra = ?";

        // Ejecutar la consulta y verificar si se actualizó algún registro
        int filasAfectadas = jdbcTemplate.update(sql, idPedido);
        return filasAfectadas > 0;
    }

}
