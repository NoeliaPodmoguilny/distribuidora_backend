package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.DetalleComprasDTO;
import com.distribuidora.distri.model.DetalleCompras;
import com.distribuidora.distri.model.OrdenDeCompra;
import com.distribuidora.distri.model.Producto;
import com.distribuidora.distri.repository.IDetalleComprasRepository;
import com.distribuidora.distri.repository.IProductoRepository;
import com.distribuidora.distri.service.interfaz.IDetalleComprasService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleComprasService implements IDetalleComprasService {

    @Autowired private IDetalleComprasRepository detalleComprasRepository;
    @Autowired private IProductoRepository productoRepository;

    @Override
    public List<DetalleCompras> getDetalleCompras() {
        return detalleComprasRepository.findAll();
    }

    @Override
    public DetalleCompras findDetalleCompras(Long id) {
        return detalleComprasRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Detalle de compra no encontrado"));
    }

    @Override
    public DetalleCompras saveDetalleCompras(DetalleComprasDTO detalleComprasDTO) {
        DetalleCompras dc = new DetalleCompras();
        dc.setCantidad(detalleComprasDTO.getCantidad());
        OrdenDeCompra oc = new OrdenDeCompra();
        dc.setOrdenDeCompra(oc);
        // buscar producto
        Producto pr = productoRepository.findById(detalleComprasDTO.getProductoDTO()).orElseThrow();
        dc.setProducto(pr);

        return detalleComprasRepository.save(dc);
    }

    @Override
    public void deleteDetalleCompras(Long id) {
        if (!detalleComprasRepository.existsById(id)) {
            throw new RuntimeException("Detalle no encontrado con el id: " + id);
        }
        detalleComprasRepository.deleteById(id);
    }

    @Override
    public DetalleCompras updateDetalleCompras(Long id, DetalleComprasDTO detalleComprasDTO) {
        DetalleCompras dc = this.findDetalleCompras(id);
        dc.setCantidad(detalleComprasDTO.getCantidad());
        OrdenDeCompra oc = new OrdenDeCompra();
        dc.setOrdenDeCompra(oc);
        // buscar producto
        Producto pr = productoRepository.findById(detalleComprasDTO.getProductoDTO()).orElseThrow();
        dc.setProducto(pr);

        return detalleComprasRepository.save(dc);
    }
}
