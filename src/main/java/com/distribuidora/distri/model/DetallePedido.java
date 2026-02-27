package com.distribuidora.distri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DetallePedido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idDetallePedido;

        // relacion con pedido
        @ManyToOne
        private Pedido pedido;

        //relacion con producto
        @ManyToOne
        private Producto producto;

        private int cantidad;
        private Double porcentajeDescuento;

        // Relacion con cada lote de inventario
        @ManyToOne(optional = false)
        @JoinColumns({
                @JoinColumn(name = "id_producto", nullable = false),
                @JoinColumn(name = "fecha_vencimiento", nullable = false)
        })
        private Inventario inventario;




}
