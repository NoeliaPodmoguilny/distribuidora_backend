package com.distribuidora.distri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "factura_metodo_pago")
public class FacturaMetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "idMetodoDePago")
    private MetodoDePago metodoDePago;

    private Double montoParcial;
}
