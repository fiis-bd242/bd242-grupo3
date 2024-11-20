package pe.uni.buenaventurabackend.modules.gestion_reportes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.uni.buenaventurabackend.modules.gestion_reportes.models.*;
import pe.uni.buenaventurabackend.modules.gestion_reportes.service.*;

import java.text.SimpleDateFormat;
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
    private final IncidenciasTagService incidenciasTagService;

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
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal,
            @RequestParam int offset) {

        List<ReporteDTO> reportes = iReporteService.obtenerReportesPorFecha(fechaInicial, fechaFinal, offset);
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
        try {
            Integer Id_registro = (Integer) payload.get("Id_registro");
            String Asunto = (String) payload.get("Asunto");
            String Mensaje = (String) payload.get("Mensaje");
            int Id_remitente = (int) payload.get("Id_remitente");
            String fechaReporteStr = (String) payload.get("fecha_Reporte"); // Fecha en formato "yyyy-MM-dd"
            int id_tipo = (int) payload.get("id_tipo");

            // Parsear la fecha si no es nula
            Date fecha_Reporte = null;
            if (fechaReporteStr != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha_Reporte = sdf.parse(fechaReporteStr);
            }

            // Si la fecha es válida, puedes convertirla a formato string
            String fechaReporteFormatted = null;  // Aquí declaramos la variable correctamente
            if (fecha_Reporte != null) {
                SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
                fechaReporteFormatted = sdfOut.format(fecha_Reporte); // Formato 'yyyy-MM-dd'
            }

            // Llamar al servicio con la fecha, ya sea en formato String o Date
            // Pasamos fechaReporteFormatted, que es el String con formato 'yyyy-MM-dd'
            notificacionService.crearNotificacion(Id_registro, Asunto, Mensaje, Id_remitente, fechaReporteFormatted, id_tipo);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("ID_REMITENTE:" + payload.get("Id_remitente"));
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable("id") int idRegistro) {
        registroService.eliminarRegistro(idRegistro);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/estado")
    public ResponseEntity<String> actualizarEstadoReporte(
            @RequestParam("fechaReporte")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaReporte,
            @RequestParam Integer nuevoEstado) {

        int rowsUpdated = iReporteService.actualizarEstadoReporte(fechaReporte, nuevoEstado);

        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Estado del reporte actualizado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Reporte no encontrado.");
        }
    }

    @PutMapping("/{idRegistro}/observaciones")
    public ResponseEntity<String> actualizarObservaciones(
            @PathVariable Integer idRegistro,
            @RequestParam String observaciones) {

        boolean actualizado = registroService.actualizarObservaciones(idRegistro, observaciones);

        if (actualizado) {
            return ResponseEntity.ok("Observaciones actualizadas correctamente.");
        } else {
            return ResponseEntity.status(404).body("Registro no encontrado.");
        }
    }


    @DeleteMapping("/incidencias/{idRegistro}")
    public ResponseEntity<String> eliminarIncidencia(
            @PathVariable Integer idRegistro,
            @RequestParam String incidencia) {

        boolean eliminado = incidenciasTagService.eliminarIncidencia(idRegistro, incidencia);

        if (eliminado) {
            return ResponseEntity.ok("Incidencia eliminada correctamente.");
        } else {
            return ResponseEntity.status(404).body("No se encontró la incidencia para eliminar.");
        }
    }

    @PostMapping("/incidencias/{idRegistro}")
    public ResponseEntity<String> agregarIncidencia(
            @PathVariable Integer idRegistro,
            @RequestParam String incidencia) {

        boolean agregado = incidenciasTagService.agregarIncidencia(incidencia, idRegistro);

        if (agregado) {
            return ResponseEntity.ok("Incidencia agregada correctamente.");
        } else {
            return ResponseEntity.status(400).body("Error al agregar la incidencia.");
        }
    }
}


