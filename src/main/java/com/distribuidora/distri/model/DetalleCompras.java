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
public class DetalleCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCompras;

    @ManyToOne
    private OrdenDeCompra ordenDeCompra;

    @ManyToOne
    private Producto producto;
    private int cantidad;
}
