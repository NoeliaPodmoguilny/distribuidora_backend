package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.EstadoFactura;
import com.distribuidora.distri.enumm.Letra;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Factura extends Comprobante {

    @Enumerated(EnumType.STRING)
    private Letra letra;

    @OneToOne
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;

    //relacion con metodos de pago
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacturaMetodoPago> pagosRealizados;

}
