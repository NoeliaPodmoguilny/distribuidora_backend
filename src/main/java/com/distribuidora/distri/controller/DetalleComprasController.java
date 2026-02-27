package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.DetalleComprasDTO;
import com.distribuidora.distri.model.DetalleCompras;
import com.distribuidora.distri.service.interfaz.IDetalleComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleCompras")
public class DetalleComprasController {

    @Autowired
    private IDetalleComprasService detalleComprasService;


    //    traer lista de DetalleComprass
    @GetMapping("/get")
    public List<DetalleCompras> getDetalleComprass() {
        return detalleComprasService.getDetalleCompras();
    }

    //    traer un DetalleCompras por id (id)
    @GetMapping("/get/{id}")
    public DetalleCompras getDetalleCompras(@PathVariable Long id) {
        return detalleComprasService.findDetalleCompras(id);
    }

    // crear DetalleCompras
    @PostMapping("/save")
    public ResponseEntity<DetalleCompras> createDetalleCompras(@RequestBody DetalleComprasDTO detalleComprasDTO) {
        return ResponseEntity.ok(detalleComprasService.saveDetalleCompras(detalleComprasDTO));
    }

    //  eliminar DetalleCompras
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDetalleCompras(@PathVariable Long id) {
        detalleComprasService.deleteDetalleCompras(id);
        return ResponseEntity.ok("Detalle de Compras eliminado");
    }

    // editar DetalleCompras
    @PatchMapping("/update/{id}")
    public ResponseEntity<DetalleCompras> updateDetalleCompras(@PathVariable Long id, @RequestBody DetalleComprasDTO detalleComprasDTO) {
        return ResponseEntity.ok(detalleComprasService.updateDetalleCompras(id, detalleComprasDTO));

    }
}
