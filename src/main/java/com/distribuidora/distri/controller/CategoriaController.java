package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.CategoriaProductoDTO;
import com.distribuidora.distri.model.CategoriaProducto;
import com.distribuidora.distri.service.interfaz.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;


    //    traer lista de CategoriaProductos
    @GetMapping("/get")
    public List<CategoriaProducto> getCategoriaProductos() {
        return categoriaService.getCategoriaProductos();
    }

    //    traer una CategoriaProducto por id (id)
    @GetMapping("/get/{id}")
    public CategoriaProducto getCategoriaProducto(@PathVariable Long id) {
        return categoriaService.findCategoriaProducto(id);
    }

    @GetMapping("/getBynombreCategoriaProducto/{nombreCategoriaProducto}")
    public CategoriaProducto findCategoriaProductoBynombreCategoriaProducto(@PathVariable String nombreCategoriaProducto) {
        return categoriaService.findCategoriaProductoBynombreCategoriaProducto(nombreCategoriaProducto);
    }

    // crear CategoriaProducto
    @PostMapping("/save")
    public ResponseEntity<CategoriaProducto> createCategoriaProducto(@RequestBody CategoriaProductoDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.saveCategoriaProducto(categoriaDTO));
    }

    //  eliminar CategoriaProducto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoriaProducto(@PathVariable Long id) {
        categoriaService.deleteCategoriaProducto(id);
        return ResponseEntity.ok("Categoria del Producto eliminada");
    }

    // editar CategoriaProducto
    @PatchMapping("/update/{id}")
    public ResponseEntity<CategoriaProducto> updateCategoriaProducto(@PathVariable Long id, @RequestBody CategoriaProductoDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.updateCategoriaProducto(id, categoriaDTO));
    }
}
