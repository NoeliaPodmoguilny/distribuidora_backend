package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedidoIdPedido(Long idPedido);

    List<DetallePedido> findAllByPedidoIdPedido(Long idPedido);

    boolean existsByInventario_Id_IdProductoAndInventario_Id_FechaVencimiento(Long idProducto, LocalDate fechaVencimiento);
}
