package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.DetallePedidoDTO;
import com.distribuidora.distri.model.DetallePedido;

import java.util.List;

public interface IDetallePedidoService {

    List<DetallePedido> getDetallePedidos();

    DetallePedido findDetallePedido(Long id);

    DetallePedido saveDetallePedido(DetallePedidoDTO detallePedidoDTO);

    void deleteDetallePedido(Long id);

    DetallePedido updateDetallePedido(Long id, DetallePedidoDTO detallePedidoDTO);

    List<DetallePedido> buscarDetallesPorPedido(Long idPedido);
}
