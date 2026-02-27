package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.CategoriaProductoDTO;
import com.distribuidora.distri.model.CategoriaProducto;

import java.util.List;

public interface ICategoriaService {

    List<CategoriaProducto> getCategoriaProductos();

    CategoriaProducto findCategoriaProducto(Long id);

    CategoriaProducto findCategoriaProductoBynombreCategoriaProducto(String nombreCategoriaProducto);

    CategoriaProducto saveCategoriaProducto(CategoriaProductoDTO categoriaProductoDTO);

    void deleteCategoriaProducto(Long id);

    CategoriaProducto updateCategoriaProducto(Long id, CategoriaProductoDTO categoriaProductoDTO);
}
