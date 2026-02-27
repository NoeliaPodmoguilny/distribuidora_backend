package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.EstadoInventario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Inventario {

    @EmbeddedId
    private InventarioId id; //PK compuesta (idProducto + fechaVencimiento)

    private int stockActual;

    @Enumerated(EnumType.STRING)
    private EstadoInventario estado;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(
            name = "id_producto",
            insertable = false,
            updatable = false
    )
    private Producto producto;
}
