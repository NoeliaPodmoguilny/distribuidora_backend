package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.request.RepartoRequestDTO;
import com.distribuidora.distri.dto.response.FacturaResponseDTO;
import com.distribuidora.distri.dto.response.RepartoResponseDTO;
import com.distribuidora.distri.model.Reparto;
import com.distribuidora.distri.service.interfaz.IRepartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reparto")
public class RepartoController {

    @Autowired
    private IRepartoService repartoService;

    // traer lista completa de Repartos existentes
    @GetMapping("/get")
    public ResponseEntity<List<RepartoResponseDTO>> getRepartos() {
        List<RepartoResponseDTO> repartos = repartoService.getRepartos();
        if (repartos.isEmpty()) {
            throw new IllegalArgumentException("No existen repartos pendientes");
        }
        return ResponseEntity.ok(repartos);
    }

    // Buscar hoja de reparto por id del reparto
    @GetMapping("/get/{id}")
    public ResponseEntity<Reparto> getReparto(@PathVariable Long id) {
        Reparto reparto = repartoService.findReparto(id);
        if (reparto == null) {
            throw new IllegalArgumentException("No se encontró el reparto con ID " + id);
        }
        return ResponseEntity.ok(reparto);
    }

    // traer facturas GENERADAS con clientes con la misma zona del repartidor
    @GetMapping("/getFacturasPorZonaReparto/{idRepartidor}")
    public ResponseEntity<List<FacturaResponseDTO>> getFacturasParaReparto(@PathVariable Long idRepartidor) {
        return ResponseEntity.ok(repartoService.getFacturasParaReparto(idRepartidor));
    }

    // Generar Hoja de Reparto
    @PostMapping("/generarHojaDeReparto")
    public ResponseEntity<RepartoResponseDTO> createReparto(@RequestBody RepartoRequestDTO repartoDTO) {
        return new ResponseEntity<>(repartoService.saveReparto(repartoDTO), HttpStatus.CREATED);
    }

    // eliminar HOJA DE RUTA Y VOLVER FACTURAS AL ESTADO GENERADA
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReparto(@PathVariable Long id) {
        repartoService.deleteReparto(id);
        return ResponseEntity.ok("Reparto eliminado");
    }

    // cambiar estado FINALIZADO
    @PatchMapping("/finalizado/{id}")
    public void cambiarFinalizado(@PathVariable Long id) {
        repartoService.cambiarFinalizado(id);
    }

}
