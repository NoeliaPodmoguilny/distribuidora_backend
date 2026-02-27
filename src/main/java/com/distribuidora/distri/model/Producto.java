package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.Iva;
import com.distribuidora.distri.helper.UpperCaseListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(UpperCaseListener.class)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column(unique = true, nullable = false)
    private String codigo;
    private String nombreProducto;
    private Double precioUnitario; // precio total
    private Double precioBase; // precio de costo
    private Double porcentajeDeGanacia;
    @Enumerated(EnumType.STRING)
    private Iva iva;
    private int stockMinimo;

    //relacion con categoria
    @ManyToOne
    private CategoriaProducto categoriaProducto;

    //relacion con marca
    @ManyToOne
    private Marca marca;

    // Guardar imagen
    @Column(length = 255)
    private String imagen;

}
