package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.ProductoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> getProductos();

    ProductoDTO findProducto(Long id);

    ProductoDTO findProductoBynombreProducto(String nombreProducto);

    ProductoDTO findProductoByCodigo(String codigo);

    String saveProducto(ProductoDTO productoDTO, MultipartFile imagen);

    void deleteProducto(Long id);

    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO, MultipartFile imagen);

    List<ProductoDTO> buscarProductos(String nombre, String codigo, String marca, String categoria);
}
