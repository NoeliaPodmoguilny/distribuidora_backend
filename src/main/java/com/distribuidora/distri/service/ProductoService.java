package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.ProductoDTO;
import com.distribuidora.distri.model.*;
import com.distribuidora.distri.repository.*;
import com.distribuidora.distri.service.interfaz.IProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    //imagenes
    private final Path root = Paths.get("uploads/productos");

    @Autowired private IProductoRepository productoRepository;
    @Autowired private ICategoriaRepository categoriaRepository;
    @Autowired private IMarcaRepository marcaRepository;
    @Autowired private IInventarioRepository inventarioRepository;

    @Override
    public List<ProductoDTO> getProductos() {
        return productoRepository.findAll()
                .stream()
                .map(prod -> new ProductoDTO(
                        prod.getIdProducto(),
                        prod.getCodigo(),
                        prod.getNombreProducto(),
                        prod.getPrecioUnitario(),
                        prod.getPrecioBase(),
                        prod.getPorcentajeDeGanacia(),
                        prod.getIva(),
                        prod.getStockMinimo(),
                        prod.getCategoriaProducto().getIdCategoriaProducto(),
                        prod.getMarca().getIdMarca(),
                        prod.getImagen()
                ))
                .toList();
    }

    @Override
    public ProductoDTO findProducto(Long id) {
        Producto prod = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return new ProductoDTO(
                        prod.getIdProducto(),
                        prod.getCodigo(),
                        prod.getNombreProducto(),
                        prod.getPrecioUnitario(),
                        prod.getPrecioBase(),
                        prod.getPorcentajeDeGanacia(),
                        prod.getIva(),
                        prod.getStockMinimo(),
                        prod.getCategoriaProducto().getIdCategoriaProducto(),
                        prod.getMarca().getIdMarca(),
                        prod.getImagen());
    }

    @Override
    public ProductoDTO findProductoBynombreProducto(String nombreProducto) {
        Producto prod = productoRepository.findBynombreProducto(nombreProducto);
        return new ProductoDTO(
                prod.getIdProducto(),
                prod.getCodigo(),
                prod.getNombreProducto(),
                prod.getPrecioUnitario(),
                prod.getPrecioBase(),
                prod.getPorcentajeDeGanacia(),
                prod.getIva(),
                prod.getStockMinimo(),
                prod.getCategoriaProducto().getIdCategoriaProducto(),
                prod.getMarca().getIdMarca(),
                prod.getImagen());
    }

    @Override
    public ProductoDTO findProductoByCodigo(String codigo) {
        Producto prod = productoRepository.findByCodigo(codigo);
        return new ProductoDTO(
                prod.getIdProducto(),
                prod.getCodigo(),
                prod.getNombreProducto(),
                prod.getPrecioUnitario(),
                prod.getPrecioBase(),
                prod.getPorcentajeDeGanacia(),
                prod.getIva(),
                prod.getStockMinimo(),
                prod.getCategoriaProducto().getIdCategoriaProducto(),
                prod.getMarca().getIdMarca(),
                prod.getImagen());
    }

    @Override
    public String saveProducto(@Valid ProductoDTO productoDTO, MultipartFile imagen) {

        Producto p = new Producto();
        p.setNombreProducto(productoDTO.getNombreProducto());
        p.setCodigo(productoDTO.getCodigo());
        p.setPorcentajeDeGanacia(productoDTO.getPorcentajeDeGanacia());
        p.setIva(productoDTO.getIva());
        p.setPrecioUnitario(productoDTO.getPrecioUnitario());
        p.setPrecioBase(productoDTO.getPrecioBase());
        p.setStockMinimo(productoDTO.getStockMinimo());
        //guardamos la imagen
        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = guardarImagen(imagen);
            p.setImagen(nombreArchivo);
        }
        // buscar marca
        Marca mar = marcaRepository.findById(productoDTO.getMarca()).orElseThrow();
        p.setMarca(mar);
        // buscar categoria
        CategoriaProducto categoriaProd = categoriaRepository.findById(productoDTO.getCategoriaProducto()).orElseThrow();
        p.setCategoriaProducto(categoriaProd);
        // guardo el producto
        Producto productoGuardado = productoRepository.save(p);
        return "Producto guardado!";
    }

    // FUNCIÓN AUXILIAR PARA GUARDAR LA IMAGEN
    private String guardarImagen(MultipartFile archivo) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            String extension = Objects.requireNonNull(
                    archivo.getOriginalFilename()
            ).substring(archivo.getOriginalFilename().lastIndexOf("."));
            String nombreUnico = UUID.randomUUID() + extension;
            Files.copy(
                    archivo.getInputStream(),
                    root.resolve(nombreUnico),
                    StandardCopyOption.REPLACE_EXISTING
            );
            return nombreUnico;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar imagen", e);
        }
    }

    @Override
    public void deleteProducto(Long idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new RuntimeException("Producto no encontrado con id: " + idProducto);
        }
        productoRepository.deleteById(idProducto);

    }

    @Override
    public ProductoDTO updateProducto(Long id, @Valid ProductoDTO productoDTO, MultipartFile imagen) {
//      Busco el producto por su ID en la BD
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        //Seteo los valores
        p.setNombreProducto(productoDTO.getNombreProducto());
        p.setCodigo(productoDTO.getCodigo());
        p.setPorcentajeDeGanacia(productoDTO.getPorcentajeDeGanacia());
        p.setIva(productoDTO.getIva());
        p.setPrecioUnitario(productoDTO.getPrecioUnitario());
        p.setPrecioBase(productoDTO.getPrecioBase());
        p.setStockMinimo(productoDTO.getStockMinimo());

        // buscar marca
        Marca mar = marcaRepository.findById(productoDTO.getMarca()).orElseThrow();
        p.setMarca(mar);
        // buscar categoria
        CategoriaProducto categoriaProd = categoriaRepository.findById(productoDTO.getCategoriaProducto()).orElseThrow();
        p.setCategoriaProducto(categoriaProd);
        //guardamos la imagen
        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = guardarImagen(imagen);
            p.setImagen(nombreArchivo);
        }
        //guardo el producto
        productoRepository.save(p);
        return new ProductoDTO(
                p.getIdProducto(),
                p.getCodigo(),
                p.getNombreProducto(),
                p.getPrecioUnitario(),
                p.getPrecioBase(),
                p.getPorcentajeDeGanacia(),
                p.getIva(),
                p.getStockMinimo(),
                p.getCategoriaProducto().getIdCategoriaProducto(),
                p.getMarca().getIdMarca(),
                p.getImagen());
    }

    @Override
    public List<ProductoDTO> buscarProductos(String nombre, String codigo, String marca, String categoria) {
        return productoRepository.buscarProductos(nombre, codigo, marca, categoria)
                .stream()
                .map(prod -> new ProductoDTO(
                        prod.getIdProducto(),
                        prod.getCodigo(),
                        prod.getNombreProducto(),
                        prod.getPrecioUnitario(),
                        prod.getPrecioBase(),
                        prod.getPorcentajeDeGanacia(),
                        prod.getIva(),
                        prod.getStockMinimo(),
                        prod.getCategoriaProducto().getIdCategoriaProducto(),
                        prod.getMarca().getIdMarca(),
                        prod.getImagen()
                ))
                .toList();
    }
}

