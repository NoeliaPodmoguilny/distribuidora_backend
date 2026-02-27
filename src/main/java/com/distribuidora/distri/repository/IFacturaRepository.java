package com.distribuidora.distri.repository;

import com.distribuidora.distri.enumm.EstadoFactura;
import com.distribuidora.distri.enumm.ZonaReparto;
import com.distribuidora.distri.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Long> {
    Factura findFacturaByNumeroComprobante(Long numeroComprobante);


    @Query("""
    SELECT f
    FROM Factura f
    JOIN FETCH f.pedido p
    JOIN FETCH p.cliente c
    WHERE c.zona = :zona
      AND f.estado = :estado
""")
    List<Factura> getFacturasByZonaAndEstado(
            @Param("zona") ZonaReparto zona,
            @Param("estado") EstadoFactura estado);

}
