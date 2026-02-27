package com.distribuidora.distri.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class InventarioId implements Serializable {

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventarioId)) return false;
        InventarioId that = (InventarioId) o;
        return Objects.equals(idProducto, that.idProducto) &&
                Objects.equals(fechaVencimiento, that.fechaVencimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, fechaVencimiento);
    }
}

