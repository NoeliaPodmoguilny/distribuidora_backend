package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.DetallePedidoDTO;
import com.distribuidora.distri.dto.PedidoDTO;
import com.distribuidora.distri.enumm.Estado;
import com.distribuidora.distri.enumm.EstadoInventario;
import com.distribuidora.distri.model.*;
import com.distribuidora.distri.model.Pedido;
import com.distribuidora.distri.repository.*;
import com.distribuidora.distri.service.interfaz.IPedidoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PedidoService implements IPedidoService {

    @Autowired private IPedidoRepository pedidoRepository;
    @Autowired private IClienteRepository clienteRepository;
    @Autowired private IEmpleadoRepository empleadoRepository;
    @Autowired private IDetallePedidoRepository detallePedidoRepository;
    @Autowired private IProductoRepository productoRepository;
    @Autowired private IInventarioRepository inventarioRepository;
    
    @Override
    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public PedidoDTO findPedidoById(Long id) {
            Pedido pe = this.findPedido(id);
            PedidoDTO pedDTO = new PedidoDTO();
            return pedDTO;
        }

    public Pedido findPedido(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
    }

    /**
     * MÉTTODO PARA GUARDAR UN PEDIDO
     * - Descuenta de inventario los productos requeridos
     * - Lógica FIFO
     */
    @Override
    @Transactional
    public PedidoDTO savePedido(PedidoDTO pedidoDTO) {

        Pedido pedido = new Pedido();
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());
        pedido.setEstado(pedidoDTO.getEstado());

        // Cliente
        Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteDTO())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        pedido.setCliente(cliente);

        // Empleados
        List<Empleado> empleados = pedidoDTO.getEmpleadoDTO().stream()
                .map(id -> empleadoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado")))
                .toList();
        pedido.setEmpleados(new ArrayList<>(empleados));

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        double montoTotal = 0.0;

        // DETALLES + DESCUENTO FIFO
        for (DetallePedidoDTO dto : pedidoDTO.getDetallePedidoDTO()) {

            Long idProducto = dto.getProductoDTO();
            int cantidadRequerida = dto.getCantidad();

            Producto producto = productoRepository.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            List<Inventario> lotes = inventarioRepository
                    .buscarPorProductoYOrdenarPorVencimiento(idProducto);

            int stockDisponible = lotes.stream()
                    .mapToInt(Inventario::getStockActual)
                    .sum();

            if (stockDisponible < cantidadRequerida) {
                throw new RuntimeException(
                        "Stock insuficiente para producto ID " + idProducto
                );
            }

            int pendiente = cantidadRequerida;

            for (Inventario inv : lotes) {
                if (pendiente <= 0) break;

                int descuento = Math.min(inv.getStockActual(), pendiente);

                if (descuento > 0) {

                    //descontar stock
                    inv.setStockActual(inv.getStockActual() - descuento);
                    recalcularEstadoLote(inv);
                    inventarioRepository.save(inv);

                    //CREAR DETALLE POR LOTE
                    DetallePedido detalle = new DetallePedido();
                    detalle.setPedido(pedidoGuardado);
                    detalle.setProducto(producto);
                    detalle.setInventario(inv);
                    detalle.setCantidad(descuento);
                    detalle.setPorcentajeDescuento(dto.getPorcentajeDescuento());

                    detallePedidoRepository.save(detalle);

                    pendiente -= descuento;
                }
            }

            // Se calcula el monto
            double subtotal = producto.getPrecioUnitario()
                    * cantidadRequerida
                    * (1 - dto.getPorcentajeDescuento() / 100.0);
            montoTotal += subtotal;
        }

        pedidoGuardado.setMontoTotalPedido(montoTotal);
        pedidoRepository.save(pedidoGuardado);

        pedidoDTO.setId(pedidoGuardado.getIdPedido());
        pedidoDTO.setMontoTotalPedido(montoTotal);

        return pedidoDTO;
    }

    /**
     * MÉTTODO PARA ELIMINAR UN PEDIDO:
     * - Regresa los productos eliminados del inventario, devuelve el stock_actual
     * - Lófica LIFO
     */

    @Override
    @Transactional
    public void deletePedido(Long id) {

        // Buscar pedido
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Pedido no encontrado con id: " + id
                ));

        //Obtener detalles del pedido
        List<DetallePedido> detalles =
                detallePedidoRepository.findByPedidoIdPedido(id);

        //Devolver stock al lote exacto usado
        for (DetallePedido detalle : detalles) {
            Inventario inv = detalle.getInventario();

            // no devolver a lotes anulados o vencidos
            if (inv.getEstado() == EstadoInventario.ANULADO
                    || inv.getEstado() == EstadoInventario.VENCIDO) {
                continue;
            }
            inv.setStockActual(inv.getStockActual() + detalle.getCantidad());
            recalcularEstadoLote(inv);
            inventarioRepository.save(inv);
        }

        //Eliminar detalles y pedido
        detallePedidoRepository.deleteAll(detalles);
        pedidoRepository.delete(pedido);
    }


    /**
     * MÉTTODO PARA ACTUALIZAR UN PEDIDO:
     * - Si se suma mas cantidad de un producto a un pedido, se descuenta ese producto del inventario, teniendo en cuenta la fecha de vencimiento para el lote mas proximo
     * - Si se resta cantidad de un producto en el pedido, se suma al inventario nuevamente
     */

    @Override
    @Transactional
    public void updatePedido(Long id, PedidoDTO pedidoDTO) {

        // Buscar pedido existente
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));

        if (pedido.getEstado() == Estado.FACTURADO) {
            throw new RuntimeException("Error: No se puede modificar un pedido que ya está FACTURADO.");
        }
        // Obtener detalles actuales de la BD (para saber cuáles borrar después)
        List<DetallePedido> detallesEnBase = detallePedidoRepository.findByPedidoIdPedido(id);

        // Lista para rastrear qué IDs fueron actualizados/mantenidos
        List<Long> idsDetallesProcesados = new ArrayList<>();

        // Procesar los DTOs
        for (DetallePedidoDTO dto : pedidoDTO.getDetallePedidoDTO()) {

            // Actualizar un detalle existente (viene con ID)
            if (dto.getId() != null) {
                DetallePedido detalle = detallePedidoRepository.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Detalle no encontrado ID: " + dto.getId()));

                // Lógica de Stock (diferencia)
                int cantidadNueva = dto.getCantidad();
                int cantidadVieja = detalle.getCantidad();
                int diferencia = cantidadNueva - cantidadVieja;

                if (diferencia > 0) {
                    // Aumentó cantidad: descontar stock (FIFO)
                    descontarStockFIFO(dto.getProductoDTO(), diferencia);
                } else if (diferencia < 0) {
                    // Disminuyó cantidad: devolver al lote original
                    Inventario inv = detalle.getInventario();
                    inv.setStockActual(inv.getStockActual() + Math.abs(diferencia));
                    recalcularEstadoLote(inv);
                    inventarioRepository.save(inv);
                }

                // Actualizar datos del detalle
                detalle.setCantidad(cantidadNueva);
                detalle.setPorcentajeDescuento(dto.getPorcentajeDescuento());
                detallePedidoRepository.save(detalle);

                // Lo marcamos como procesado para no borrarlo
                idsDetallesProcesados.add(detalle.getIdDetallePedido());

            }
            // Es un producto NUEVO agregado en la edición (ID es null)
            else {
                int cantidad = dto.getCantidad();
                Long idProducto = dto.getProductoDTO();

                List<Inventario> lotes = inventarioRepository.buscarPorProductoYOrdenarPorVencimiento(idProducto);

                int pendiente = cantidad;

                for (Inventario inv : lotes) {
                    if (pendiente <= 0) break;

                    int disponible = inv.getStockActual();
                    int descuento = Math.min(disponible, pendiente);

                    if (descuento > 0) {
                        inv.setStockActual(disponible - descuento);
                        recalcularEstadoLote(inv);
                        inventarioRepository.save(inv);

                        DetallePedido nuevoDetalle = new DetallePedido();
                        nuevoDetalle.setPedido(pedido);
                        nuevoDetalle.setProducto(productoRepository.findById(idProducto).orElseThrow());
                        nuevoDetalle.setCantidad(descuento);
                        nuevoDetalle.setPorcentajeDescuento(dto.getPorcentajeDescuento());
                        nuevoDetalle.setInventario(inv);

                        detallePedidoRepository.save(nuevoDetalle);

                        pendiente -= descuento;
                    }
                }

                if (pendiente > 0) {
                    throw new RuntimeException("Stock insuficiente para el producto nuevo ID: " + idProducto);
                }
            }
        }

        // Eliminar los detalles que estaban en la BD
        for (DetallePedido detalleBD : detallesEnBase) {
            if (!idsDetallesProcesados.contains(detalleBD.getIdDetallePedido())) {
                // Devolver stock
                Inventario inv = detalleBD.getInventario();
                inv.setStockActual(inv.getStockActual() + detalleBD.getCantidad());
                recalcularEstadoLote(inv);
                inventarioRepository.save(inv);

                // Borrar detalle
                detallePedidoRepository.delete(detalleBD);
            }
        }

        // Actualizar cabecera del pedido
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());
        pedido.setEstado(pedidoDTO.getEstado());
        // Actualizamos el total
        if(pedidoDTO.getMontoTotalPedido() != null) {
            pedido.setMontoTotalPedido(pedidoDTO.getMontoTotalPedido());
        }

        pedidoRepository.save(pedido);
    }

// FUNCIONES AUXILIARES
private void descontarStockFIFO(Long idProducto, int cantidad) {
    List<Inventario> lotes = inventarioRepository.buscarPorProductoYOrdenarPorVencimiento(idProducto);

    int pendiente = cantidad;

    for (Inventario inv : lotes) {
        if (pendiente <= 0) break;

        int disponible = inv.getStockActual();
        int descuento = Math.min(disponible, pendiente);

        if (descuento > 0) {
            inv.setStockActual(disponible - descuento);
            recalcularEstadoLote(inv);
            inventarioRepository.save(inv);
            pendiente -= descuento;
        }
    }
    if (pendiente > 0) {
        throw new RuntimeException("Stock insuficiente");
    }
}

    /**
     *
     * MÉTTODO AUXILIAR PARA EL ESTADO DE UN LOTE
     *
     */
    private void recalcularEstadoLote(Inventario inv) {
        if (inv.getEstado() == EstadoInventario.ANULADO) {
            return;
        }
        if (inv.getEstado() == EstadoInventario.VENCIDO) {
            return;
        }
        // Estado según stock
        if (inv.getStockActual() == 0) {
            inv.setEstado(EstadoInventario.AGOTADO);
        } else {
            inv.setEstado(EstadoInventario.DISPONIBLE);
        }
    }

}
