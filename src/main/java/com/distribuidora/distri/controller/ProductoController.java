package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.ProductoDTO;
import com.distribuidora.distri.dto.ProductoInventarioDTO;
import com.distribuidora.distri.service.interfaz.IInventarioService;
import com.distribuidora.distri.service.interfaz.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IInventarioService inventarioService;

    // traer lista de Productos
    @GetMapping("/get")
    public ResponseEntity<List<ProductoDTO>> getProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }

    // traer un Producto por id (id)
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductoDTO> getProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findProducto(id));
    }

    // traer un Producto por nombre
    @GetMapping("/getBynombreProducto/{nombreProducto}")
    public ResponseEntity<ProductoDTO> findProductoBynombreProducto(@PathVariable String nombreProducto) {
        return ResponseEntity.ok(productoService.findProductoBynombreProducto(nombreProducto));
    }

    // traer un Producto por codigo
    @GetMapping("/getByCodigo/{codigo}")
    public ResponseEntity<ProductoDTO> findProductoByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(productoService.findProductoByCodigo(codigo));
    }

    // Guardar nuevo prod
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createProducto(
            @Valid @ModelAttribute ProductoDTO productoDTO,
            @RequestPart(value = "archivo", required = false) MultipartFile imagen) {
        productoService.saveProducto(productoDTO, imagen);
        return ResponseEntity.ok("Producto guardado!");
    }

    // eliminar Producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.ok("Producto eliminado");
    }

    // editar Producto
    @PatchMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductoDTO> updateProducto(
            @PathVariable Long id,
            @Valid @ModelAttribute ProductoDTO productoDTO,
            @RequestPart(value = "archivo", required = false) MultipartFile imagen) {
        return ResponseEntity.ok(productoService.updateProducto(id, productoDTO, imagen));
    }

    // buscar prod por nombre, codigo, marca, categoria
    @GetMapping("/search")
    public List<ProductoDTO> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String categoria) {
        return productoService.buscarProductos(nombre, codigo, marca, categoria);
    }

    // traer un Producto proximo a vencer
    @GetMapping("/{codigo}/{idProducto}")
    public ResponseEntity<ProductoInventarioDTO> getProductoProximoVto(@PathVariable String codigo,
            @PathVariable Long idProducto) {
        return ResponseEntity.ok(inventarioService.getProductoProximoVto(codigo, idProducto));
    }

}
