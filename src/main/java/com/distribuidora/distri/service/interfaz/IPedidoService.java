package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.PedidoDTO;
import com.distribuidora.distri.model.Pedido;

import java.util.List;

public interface IPedidoService {

    List<Pedido> getPedidos();

    PedidoDTO findPedidoById(Long id);
    Pedido findPedido(Long id);

    PedidoDTO savePedido(PedidoDTO pedidoDTO);

    void deletePedido(Long id);

    void updatePedido(Long id, PedidoDTO pedidoDTO);
}
