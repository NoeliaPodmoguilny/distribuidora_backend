package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.InventarioDTO;
import com.distribuidora.distri.dto.ProductoInventarioDTO;
import com.distribuidora.distri.enumm.EstadoInventario;
import com.distribuidora.distri.model.Inventario;
import com.distribuidora.distri.model.InventarioId;
import com.distribuidora.distri.model.Producto;
import com.distribuidora.distri.repository.IDetallePedidoRepository;
import com.distribuidora.distri.repository.IInventarioRepository;
import com.distribuidora.distri.repository.IProductoRepository;
import com.distribuidora.distri.service.interfaz.IInventarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InventarioService implements IInventarioService {

    @Autowired private IInventarioRepository inventarioRepository;
    @Autowired private IProductoRepository productoRepository;
    @Autowired private IDetallePedidoRepository detallePedidoRepository;

    @Override
    public List<Inventario> getInventarios() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario findInventario(InventarioId id) {
        return inventarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado"));
    }

    @Override
    @Transactional
    public Inventario saveInventario(InventarioDTO inventarioDTO) {
        Producto producto = productoRepository.findById(inventarioDTO.getIdProducto())
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con el ID: " + inventarioDTO.getIdProducto()));

        // Se crea la clave compuesta (InventarioId)
        InventarioId inventarioId = new InventarioId();
        inventarioId.setIdProducto(inventarioDTO.getIdProducto());
        inventarioId.setFechaVencimiento(inventarioDTO.getFechaVencimiento());

        // Se crea y mapea el objeto Inventario
        Inventario inventario = new Inventario();
        inventario.setId(inventarioId); // Se asigna la clave compuesta
        inventario.setStockActual(inventarioDTO.getStockActual());
        inventario.setEstado(EstadoInventario.DISPONIBLE);
        inventario.setProducto(producto); // Se asigna producto

        return inventarioRepository.save(inventario);
    }


    @Override
    public ProductoInventarioDTO getProductoProximoVto(String codigo, Long idProducto) {
        Integer stockTotal = inventarioRepository.findStockTotalByProducto(codigo,idProducto);
        if (stockTotal == null) stockTotal = 0;
        Inventario inventarioProximo = inventarioRepository
                .findInventarioProximoPorCodigo(idProducto)
                .orElse(null);
        return new ProductoInventarioDTO( stockTotal, inventarioProximo);
    }


    @Override
    @Transactional
    public void anularLote(Long idProducto, LocalDate fechaVencimiento) {
        if (detallePedidoRepository
                .existsByInventario_Id_IdProductoAndInventario_Id_FechaVencimiento(
                        idProducto, fechaVencimiento)) {

            throw new IllegalStateException(
                    "No se puede anular el inventario: ya fue usado en un pedido"
            );
        }
        InventarioId invId = new InventarioId(idProducto, fechaVencimiento);
        Inventario inv = findInventario(invId);
        inv.setEstado(EstadoInventario.ANULADO);
        inv.setStockActual(0);
    }


    // Programado para cambiar de estado a lotes vencidos todos los dias a las 00.00
//    @Scheduled(cron = "0 0 0 * * ?") para produccion
    @Scheduled(fixedRate = 60000) // cada 60 segundos
    @Transactional
    public void marcarLotesVencidos() {
        LocalDate hoy = LocalDate.now();
        List<Inventario> vencidos =
                inventarioRepository.findLotesDisponiblesVencidos(hoy);
        for (Inventario inv : vencidos) {
            inv.setEstado(EstadoInventario.VENCIDO);
        }
        inventarioRepository.saveAll(vencidos);
    }
}




