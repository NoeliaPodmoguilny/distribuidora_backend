package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.request.OrdenDeCompraRequestDTO;
import com.distribuidora.distri.dto.response.OrdenDeCompraResponseDTO;
import com.distribuidora.distri.model.OrdenDeCompra;
import com.distribuidora.distri.service.interfaz.IOrdenDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/ordenDeCompra")
public class OrdenDeCompraController {
    
    @Autowired
    private IOrdenDeCompraService ordenDeCompraService;

    //    traer lista de OrdenDeCompras
    @GetMapping("/get")
    public List<OrdenDeCompra> getOrdenDeCompras() {
        return ordenDeCompraService.getOrdenDeCompras();
    }

    //    traer un OrdenDeCompra por id (id)
    @GetMapping("/get/{id}")
    public OrdenDeCompra getOrdenDeCompra(@PathVariable Long id) {
        return ordenDeCompraService.findOrdenDeCompra(id);
    }

    @PostMapping("/generarOrdenDeCompra")
    public ResponseEntity<OrdenDeCompraResponseDTO> emitirOrdenDeCompra(@RequestBody OrdenDeCompraRequestDTO ordenDeCompraDTO) {
        try {
            OrdenDeCompraResponseDTO response = ordenDeCompraService.generarOrdenDeCompra(ordenDeCompraDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Manejo de errores
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //  eliminar OrdenDeCompra
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrdenDeCompra(@PathVariable Long id) {
        ordenDeCompraService.deleteOrdenDeCompra(id);
        return ResponseEntity.ok("Orden De Compra Eliminada");
    }

    // editar OrdenDeCompra
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateOrdenDeCompra(@PathVariable Long id, @RequestBody OrdenDeCompraRequestDTO ordenDeCompraDTO) {
        ordenDeCompraService.updateOrdenDeCompra(id, ordenDeCompraDTO);
        return ResponseEntity.ok("Orden De Compra Actualizada!");
    }
}


