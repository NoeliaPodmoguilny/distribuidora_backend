package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.DetalleComprasDTO;
import com.distribuidora.distri.dto.request.OrdenDeCompraRequestDTO;
import com.distribuidora.distri.dto.response.OrdenDeCompraResponseDTO;
import com.distribuidora.distri.model.*;
import com.distribuidora.distri.repository.IDetalleComprasRepository;
import com.distribuidora.distri.repository.IEmpleadoRepository;
import com.distribuidora.distri.repository.IOrdenDeCompraRepository;
import com.distribuidora.distri.repository.IProductoRepository;
import com.distribuidora.distri.service.interfaz.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenDeCompraService implements IOrdenDeCompraService {


    @Autowired private IOrdenDeCompraRepository ordenDeCompraRepository;
    @Autowired private IEmpleadoRepository empleadoRepository;
    @Autowired private IProveedorService proveedorService;
    @Autowired private IProductoService productoService;
    @Autowired private IProductoRepository productoRepository;
    @Autowired private IDetalleComprasRepository detalleComprasRepository;

    @Override
    public List<OrdenDeCompra> getOrdenDeCompras() {
        return ordenDeCompraRepository.findAll();
    }

    @Override
    public OrdenDeCompra findOrdenDeCompra(Long id) {
        return ordenDeCompraRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Orden de compra no encontrada"));
    }

    @Override
    public OrdenDeCompraResponseDTO generarOrdenDeCompra(OrdenDeCompraRequestDTO dto) {

        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoDTO()).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        Proveedor proveedor = proveedorService.findProveedor(dto.getProveedorDTO());

        OrdenDeCompra orden = new OrdenDeCompra();
        orden.setFechaComprobante(LocalDate.now());
        orden.setTipoComprobante(dto.getTipoComprobante());
        orden.setEmpleado(empleado);
        orden.setProveedor(proveedor);
        orden.setMontoTotal(0.0);
        orden = ordenDeCompraRepository.save(orden);

        double montoTotal = 0.0;
        List<DetalleCompras> detalles = new ArrayList<>();

        for (DetalleComprasDTO detDTO : dto.getDetalleComprasDTOS()) {
            Producto producto = productoRepository.findById(detDTO.getProductoDTO())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + detDTO.getProductoDTO()));
            if (producto == null) throw new IllegalArgumentException("Producto no encontrado (ID: " + detDTO.getProductoDTO() + ")");

            DetalleCompras detalle = new DetalleCompras();
            detalle.setProducto(producto);
            detalle.setCantidad(detDTO.getCantidad());
            detalle.setOrdenDeCompra(orden);

            detalles.add(detalle);
            detalleComprasRepository.save(detalle);
            montoTotal += producto.getPrecioBase() * detDTO.getCantidad();
        }

        orden.setMontoTotal(montoTotal);
        ordenDeCompraRepository.save(orden);

        // ACTUALIZAR INVENTARIO

        OrdenDeCompraResponseDTO resp = new OrdenDeCompraResponseDTO();
        resp.setMontoTotal(orden.getMontoTotal());
        resp.setTipoComprobante(orden.getTipoComprobante());
        resp.setFechaComprobante(orden.getFechaComprobante());
        resp.setEmpleadoDTO(orden.getEmpleado().getIdPersona());
        resp.setProveedorDTO(orden.getProveedor().getIdPersona());
        resp.setNumeroComprobante(orden.getNumeroComprobante());
        // Mapear cada detalleDTO para devolverlo con su ID generado y número de comprobante asociado
        List<DetalleComprasDTO> detallesResp = new ArrayList<>();
        for (DetalleCompras detalleGuardado : detalles) {
            DetalleComprasDTO detalleDTO = new DetalleComprasDTO();
            detalleDTO.setId(detalleGuardado.getIdDetalleCompras());
            detalleDTO.setOrdenDeCompraDTO(orden.getNumeroComprobante());
            detalleDTO.setProductoDTO(detalleGuardado.getProducto().getIdProducto());
            detalleDTO.setCantidad(detalleGuardado.getCantidad());
            detallesResp.add(detalleDTO);
        }
        resp.setDetalleComprasDTOS(detallesResp);
        return resp;
    }

    @Override
    public void deleteOrdenDeCompra(Long id) {
        OrdenDeCompra orden = ordenDeCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada con id: " + id));
        //Primero se elimina detalle de compra
        detalleComprasRepository.deleteAllByOrdenDeCompraNumeroComprobante(orden.getNumeroComprobante());
        // Elimina OrdenDeCompra
        ordenDeCompraRepository.deleteById(orden.getNumeroComprobante());
    }

    @Override
    public void updateOrdenDeCompra(Long id, OrdenDeCompraRequestDTO ordenDeCompraDTO) {
        //  Buscamos la orden PRINCIPAL
        OrdenDeCompra ordenDeCompra = this.findOrdenDeCompra(id);

        // Actualizamos datos de cabecera
        Empleado empleadoOC = empleadoRepository.findById(ordenDeCompraDTO.getEmpleadoDTO())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        ordenDeCompra.setEmpleado(empleadoOC);

        Proveedor proveedorOC = proveedorService.findProveedor(ordenDeCompraDTO.getProveedorDTO());
        ordenDeCompra.setProveedor(proveedorOC);

        ordenDeCompra.setMontoTotal(ordenDeCompraDTO.getMontoTotal());
        ordenDeCompra.setTipoComprobante(ordenDeCompraDTO.getTipoComprobante());
        ordenDeCompra.setFechaComprobante(ordenDeCompraDTO.getFechaComprobante());

        List<DetalleComprasDTO> detallesDTO = ordenDeCompraDTO.getDetalleComprasDTOS();

        if (detallesDTO != null) {
            for(DetalleComprasDTO dto : detallesDTO) {
                DetalleCompras detalle;

                // Si el detalle YA EXISTE (Tiene ID), se actualiza
                if (dto.getId() != null) {
                    detalle = detalleComprasRepository.findById(dto.getId())
                            .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
                }
                // Si es un producto NUEVO agregado en la edición (ID null), se lo crea
                else {
                    detalle = new DetalleCompras();
                    detalle.setOrdenDeCompra(ordenDeCompra);
                }

                // Actualizamos datos comunes (cantidad y producto)
                detalle.setCantidad(dto.getCantidad());

                Producto prod = productoRepository.findById(dto.getProductoDTO())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.getProductoDTO()));
                detalle.setProducto(prod);
                detalle.setOrdenDeCompra(ordenDeCompra);
                detalleComprasRepository.save(detalle);
            }
        }
        ordenDeCompraRepository.save(ordenDeCompra);
    }
}
