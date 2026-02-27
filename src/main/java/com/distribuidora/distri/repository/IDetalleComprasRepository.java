package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.DetalleCompras;
import com.distribuidora.distri.model.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IDetalleComprasRepository extends JpaRepository<DetalleCompras, Long> {
    // Elimina todos los detalles de la orden de compra indicada
    @Modifying //Obligatorio para operaciones de escritura masiva (DELETE/UPDATE)
    @Transactional //Obligatorio para asegurar que se ejecute en una transacción
    void deleteAllByOrdenDeCompraNumeroComprobante(Long orden);

    // Buscar todos los detalle de compra por orden de compra
    List<DetalleCompras> findAllByOrdenDeCompra(OrdenDeCompra ordenDeCompra);
}
