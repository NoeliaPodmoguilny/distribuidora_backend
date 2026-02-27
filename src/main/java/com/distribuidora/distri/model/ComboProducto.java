package com.distribuidora.distri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ComboProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComboProducto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "combo_codigo_combo", nullable = false)
    private Combo combo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id_producto", nullable = false)
    private Producto producto;

    private int cantidad;
    private Double porcentajeDescuento;
}
