package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.request.OrdenDeCompraRequestDTO;
import com.distribuidora.distri.dto.response.OrdenDeCompraResponseDTO;
import com.distribuidora.distri.model.OrdenDeCompra;

import java.util.List;

public interface IOrdenDeCompraService {
    List<OrdenDeCompra> getOrdenDeCompras();

    OrdenDeCompra findOrdenDeCompra(Long id);

    OrdenDeCompraResponseDTO generarOrdenDeCompra(OrdenDeCompraRequestDTO ordenDeCompraDTO);

    void deleteOrdenDeCompra(Long id);

    void updateOrdenDeCompra(Long id, OrdenDeCompraRequestDTO ordenDeCompraDTO);
}
