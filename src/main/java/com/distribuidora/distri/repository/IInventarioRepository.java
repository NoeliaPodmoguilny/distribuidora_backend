package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.Inventario;
import com.distribuidora.distri.model.InventarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, InventarioId> {

    @Query(value = "SELECT * FROM inventario i " +
            "WHERE i.id_producto = :idProducto " +
            "AND i.stock_actual > 0 " +
            "ORDER BY i.fecha_vencimiento ASC " +
            "LIMIT 1", nativeQuery = true)
    Optional<Inventario> findInventarioProximoPorCodigo(@Param("idProducto") Long idProducto);

    @Query("SELECT SUM(i.stockActual) FROM Inventario i JOIN i.producto p " +
            "WHERE p.codigo = :codigo AND i.estado = 'DISPONIBLE' " +
            "AND i.id.idProducto = :idProducto")
    Integer findStockTotalByProducto(@Param("codigo") String codigo,
                                     @Param("idProducto") Long idProducto);

    // Bsucar lotes de un producto y ordenar por fecha de vencimiento mas proxima FIFO
    @Query("SELECT i FROM Inventario i WHERE i.id.idProducto = :idProducto AND i.estado = 'DISPONIBLE' ORDER BY i.id.fechaVencimiento ASC")
    List<Inventario> buscarPorProductoYOrdenarPorVencimiento(@Param("idProducto") Long idProducto);

    // Buscar lotes de un producto y ordenar por fecha de vencimiento mas lejana LIFO
    @Query("SELECT i FROM Inventario i WHERE i.id.idProducto = :idProducto AND i.estado = 'DISPONIBLE' ORDER BY i.id.fechaVencimiento DESC")
    List<Inventario> buscarPorProductoYOrdenarPorVencimientoDesc(@Param("idProducto") Long idProducto);

    // Buscar lotes disponibles que ya hayan vencido
    @Query("""
    SELECT i
    FROM Inventario i
    WHERE i.id.fechaVencimiento < :hoy
      AND i.estado = 'DISPONIBLE'
""")
    List<Inventario> findLotesDisponiblesVencidos(LocalDate hoy);

}
