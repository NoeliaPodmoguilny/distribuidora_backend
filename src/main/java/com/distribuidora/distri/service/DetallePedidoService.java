package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.DetallePedidoDTO;
import com.distribuidora.distri.model.DetallePedido;
import com.distribuidora.distri.model.Pedido;
import com.distribuidora.distri.model.Producto;
import com.distribuidora.distri.repository.IDetallePedidoRepository;
import com.distribuidora.distri.repository.IPedidoRepository;
import com.distribuidora.distri.repository.IProductoRepository;
import com.distribuidora.distri.service.interfaz.IDetallePedidoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.distribuidora.distri.helper.UpdateHelper.setIf;

@Service
public class DetallePedidoService implements IDetallePedidoService {

    @Autowired private IDetallePedidoRepository detallePedidoRepository;
    @Autowired private IProductoRepository productoRepository;
    @Autowired private IPedidoRepository pedidoRepository;

    @Override
    public List<DetallePedido> getDetallePedidos() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido findDetallePedido(Long id) {
        return detallePedidoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Detalle de pedido no encontrado"));
    }

    @Override
    public DetallePedido saveDetallePedido(DetallePedidoDTO detallePedidoDTO) {
        DetallePedido dp = new DetallePedido();

        // Validaciones simples
        setIf(detallePedidoDTO.getCantidad(), cantidad -> cantidad > 0, dp::setCantidad);
        setIf(detallePedidoDTO.getPorcentajeDescuento(),
                descuento -> descuento >= 0 && descuento <= 100,
                dp::setPorcentajeDescuento);

        // Relación con otras tablas
        Producto pr = productoRepository.findById(detallePedidoDTO.getProductoDTO())
                .orElseThrow(() -> new NoSuchElementException(
                        "Producto con ID " + detallePedidoDTO.getProductoDTO() + " no encontrado"));
        dp.setProducto(pr);

        Pedido ped = pedidoRepository.findById(detallePedidoDTO.getPedidoDTO())
                .orElseThrow(() -> new NoSuchElementException(
                        "Pedido con ID " + detallePedidoDTO.getPedidoDTO() + " no encontrado"));
        dp.setPedido(ped);

        return detallePedidoRepository.save(dp);
    }


    @Override
    public void deleteDetallePedido(Long id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new RuntimeException("Detalle no encontrado con el id: " + id);
        }
        detallePedidoRepository.deleteById(id);
    }

    @Override
    public DetallePedido updateDetallePedido(Long id, DetallePedidoDTO detallePedidoDTO) {
        DetallePedido dp = this.findDetallePedido(id);

        // Validaciones simples
        setIf(detallePedidoDTO.getCantidad(), cantidad -> cantidad > 0, dp::setCantidad);
        setIf(detallePedidoDTO.getPorcentajeDescuento(),
                descuento -> descuento >= 0 && descuento <= 100,
                dp::setPorcentajeDescuento);

        // Relación con otras tablas
        Producto pr = productoRepository.findById(detallePedidoDTO.getProductoDTO())
                .orElseThrow(() -> new NoSuchElementException(
                        "Producto con ID " + detallePedidoDTO.getProductoDTO() + " no encontrado"));
        dp.setProducto(pr);

        Pedido ped = pedidoRepository.findById(detallePedidoDTO.getPedidoDTO())
                .orElseThrow(() -> new NoSuchElementException(
                        "Pedido con ID " + detallePedidoDTO.getPedidoDTO() + " no encontrado"));
        dp.setPedido(ped);
        return detallePedidoRepository.save(dp);
    }

    @Override
    public List<DetallePedido> buscarDetallesPorPedido(Long idPedido) {
        return detallePedidoRepository.findAllByPedidoIdPedido(idPedido);
    }
}
