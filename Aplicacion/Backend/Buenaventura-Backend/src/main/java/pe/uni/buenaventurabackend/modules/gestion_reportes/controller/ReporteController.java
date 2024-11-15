package pe.uni.buenaventurabackend.modules.gestion_reportes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.*;
import pe.uni.buenaventurabackend.modules.gestion_reportes.service.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin(origins = "${url.client}")
public class ReporteController {

    private final IReporteService iReporteService;
    private final IResultadoService ResultadoService;
    private final RegistroService registroService;
    private final NotificacionService notificacionService;
    @GetMapping("/allreports")
    public ResponseEntity<List<Reporte>> list() {
        var result = iReporteService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/resultados-dia")
    public ResponseEntity<List<ReporteDia>> obtenerReportes() {
        List<ReporteDia> reportes = iReporteService.obtenerReportes();
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @GetMapping("/resultados")
    public ResponseEntity<List<Resultado>> obtenerResultados(@RequestParam String nombreMarca) {
        List<Resultado> resultados = ResultadoService.obtenerResultados(nombreMarca);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/por-fecha")
    public ResponseEntity<List<ReporteDTO>> obtenerReportesPorFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal) {

        List<ReporteDTO> reportes = iReporteService.obtenerReportesPorFecha(fechaInicial, fechaFinal);
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    private final MantenimientoService mantenimientoService;

    @GetMapping("/historial")
    public ResponseEntity<List<HistorialMantenimientoDTO>> obtenerHistorialMantenimiento(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        List<HistorialMantenimientoDTO> historial = mantenimientoService.obtenerHistorialMantenimiento(fechaInicio, fechaFin);
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }

    @PostMapping("/nuevo-con-incidencias")
    public ResponseEntity<Registro> crearRegistroConIncidencias(@RequestBody RegistroConIncidenciasRequest request) {
        Registro nuevoRegistro = registroService.guardarRegistroConIncidencias(request.getRegistro(), request.getIncidencias());
        return new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED);
    }

    @PostMapping("/crear-reporte")
    public ResponseEntity<Integer> crearReporte(@RequestParam int idJefe) {
        int idReporte = iReporteService.crearReporte(idJefe);
        return new ResponseEntity<>(idReporte, HttpStatus.CREATED);
    }

    @PostMapping("/notificacion-registros")
    public ResponseEntity<Void> crearNotificacion(@RequestBody Map<String, Object> payload) {
        int Id_registro = (int) payload.get("Id_registro");
        String Asunto = (String) payload.get("Asunto");
        String Mensaje = (String) payload.get("Mensaje");
        int Id_remitente = (int) payload.get("Id_remitente");

        notificacionService.crearNotificacion(Id_registro, Asunto, Mensaje, Id_remitente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable("id") int idRegistro) {
        registroService.eliminarRegistro(idRegistro);
        return ResponseEntity.noContent().build();
    }
}


