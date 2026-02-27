package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.ProveedorDTO;
import com.distribuidora.distri.model.Proveedor;
import com.distribuidora.distri.service.interfaz.IProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;


    //    traer lista de Proveedors
    @GetMapping("/get")
    public ResponseEntity<List<Proveedor>> getProveedores() {
        return ResponseEntity.ok(proveedorService.getProveedores());
    }

    //    traer un Proveedor por id (id)
    @GetMapping("/get/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.findProveedor(id));
    }

    @GetMapping("/getBynombreProveedor/{nombreProveedor}")
    public ResponseEntity<Proveedor> findProveedorBynombreProveedor(@PathVariable String nombreProveedor) {
        return ResponseEntity.ok(proveedorService.findProveedorBynombreProveedor(nombreProveedor));
    }

    // crear Proveedor
    @PostMapping("/save")
    public ResponseEntity<Proveedor> createProveedor(@Valid @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.saveProveedor(proveedorDTO));
    }

    //  eliminar Proveedor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.ok("Proveedor eliminado");
    }

    // editar Proveedor
    @PatchMapping("/update/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @Valid @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.updateProveedor(id, proveedorDTO));

    }
}
