package com.distribuidora.distri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrdenDeCompra extends Comprobante {

    @ManyToOne(optional = false)
    @JoinColumn(name = "proveedor_id_persona", nullable = false)
    private Proveedor proveedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empleado_id_persona", nullable = false)
    private Empleado empleado;
}
