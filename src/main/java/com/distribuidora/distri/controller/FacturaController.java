package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.request.FacturaRequestDTO;
import com.distribuidora.distri.dto.response.FacturaResponseDTO;
import com.distribuidora.distri.service.interfaz.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/factura")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    //    traer lista de Facturas
    @GetMapping("/get")
    public List<FacturaResponseDTO> getFacturas() {
        return facturaService.getFacturas();
    }

    //    traer un Factura por id (id)
    @GetMapping("/get/{id}")
    public FacturaResponseDTO getFactura(@PathVariable Long id) {
        return facturaService.findFactura(id);
    }

    @PostMapping("/generarFactura")
    public ResponseEntity<FacturaResponseDTO> emitirFactura(@RequestBody FacturaRequestDTO facturaDTO) {
        try {
            FacturaResponseDTO response = facturaService.generarFactura(facturaDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Manejo de errores
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/generarFacturaMasiva")
    public ResponseEntity<List<FacturaResponseDTO>> emitirFacturaMasiva(@RequestBody List<FacturaRequestDTO> facturasDTO) {
        try {
            List<FacturaResponseDTO> response = facturaService.generarFacturaMasiva(facturasDTO); // Este método debe existir en el Service
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
