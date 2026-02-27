package com.distribuidora.distri.controller;


import com.distribuidora.distri.dto.InventarioDTO;
import com.distribuidora.distri.model.Inventario;
import com.distribuidora.distri.model.InventarioId;
import com.distribuidora.distri.service.interfaz.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    
    @Autowired
    private IInventarioService inventarioService;

    //    traer lista de Inventarios
    @GetMapping("/get")
    public List<Inventario> getInventarios() {
        return inventarioService.getInventarios();
    }

    //    traer un Inventario por id (id)
    @GetMapping("/get/{idProducto}/{fechaVencimiento}")
    public Inventario getInventario(@PathVariable Long idProducto, @PathVariable LocalDate fechaVencimiento) {
        InventarioId id = new InventarioId();
        id.setIdProducto(idProducto);
        id.setFechaVencimiento(fechaVencimiento);
        return inventarioService.findInventario(id);
    }

    // crear Inventario
    @PostMapping("/save")
    public ResponseEntity<Inventario> createInventario(@RequestBody InventarioDTO inventarioDTO) {
        return ResponseEntity.ok(inventarioService.saveInventario(inventarioDTO));
    }

    // CAMBIAR ESTADO Inventario
    @PatchMapping("/anular/{idProducto}/{fechaVencimiento}")
    public ResponseEntity<String> anularLote(@PathVariable Long idProducto, @PathVariable LocalDate fechaVencimiento) {
        inventarioService.anularLote(idProducto, fechaVencimiento);
        return ResponseEntity.ok("Lote anulado");
    }
}
