package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.MarcaDTO;
import com.distribuidora.distri.model.Marca;
import com.distribuidora.distri.service.interfaz.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private IMarcaService marcaService;


    //    traer lista de Marcas
    @GetMapping("/get")
    public List<Marca> getMarcas() {
        return marcaService.getMarcas();
    }

    //    traer un Marca por id (id)
    @GetMapping("/get/{id}")
    public Marca getMarca(@PathVariable Long id) {
        return marcaService.findMarca(id);
    }

    //    traer un Marca por nombreMarca
    @GetMapping("/getBynombreMarca/{nombreMarca}")
    public Marca findMarcaBynombreMarca(@PathVariable String nombreMarca) {
        return marcaService.findMarcaBynombreMarca(nombreMarca);
    }

    // crear Marca
    @PostMapping("/save")
    public ResponseEntity<Marca> createMarca(@RequestBody MarcaDTO marcaDTO) {
        return ResponseEntity.ok(marcaService.saveMarca(marcaDTO));
    }

    //  eliminar Marca
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMarca(@PathVariable Long id) {
        marcaService.deleteMarca(id);
        return ResponseEntity.ok("Marca eliminada");
    }

    // editar Marca
    @PatchMapping("/update/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO) {
        return ResponseEntity.ok(marcaService.updateMarca(id, marcaDTO));
    }
    
}
