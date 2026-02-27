package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.InventarioDTO;
import com.distribuidora.distri.dto.ProductoInventarioDTO;
import com.distribuidora.distri.model.Inventario;
import com.distribuidora.distri.model.InventarioId;

import java.time.LocalDate;
import java.util.List;

public interface IInventarioService {
    List<Inventario> getInventarios();

    Inventario findInventario(InventarioId id);

    Inventario saveInventario(InventarioDTO inventarioDTO);

    ProductoInventarioDTO getProductoProximoVto(String codigo, Long idProducto);

    void anularLote(Long idProducto, LocalDate fechaVencimiento);
}
