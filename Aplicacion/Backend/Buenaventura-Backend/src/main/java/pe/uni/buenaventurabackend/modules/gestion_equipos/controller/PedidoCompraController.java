package pe.uni.buenaventurabackend.modules.gestion_equipos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_equipos.service.PedidoCompraService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/equipo_soporte")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class PedidoCompraController {

    @Autowired
    private final PedidoCompraService pedidoCompraService;

    @GetMapping("/obtenerPedidos")
    public ResponseEntity<List<Map<String, Object>>> obtenerPedidos() {
        var resultado = pedidoCompraService.obtenerPedidos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/buscarPedidos/{parametro}")
    public ResponseEntity<List<Map<String, Object>>> buscarPedidos(@PathVariable String parametro) {
        var resultado = pedidoCompraService.buscarPedidos(parametro);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/detallePedido/{id}")
    public ResponseEntity<Map<String, Object>> detallePedido(@PathVariable Integer id) {
        var resultado = pedidoCompraService.obtenerDetallePedido(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    // Endpoint para cancelar un pedido
    @PutMapping("/cancelarPedido/{id}")
    public ResponseEntity<String> cancelarPedido(@PathVariable int id) {
        boolean cancelado = pedidoCompraService.cancelarPedido(id);

        if (cancelado) {
            return ResponseEntity.ok("Pedido cancelado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado.");
        }
    }

}
