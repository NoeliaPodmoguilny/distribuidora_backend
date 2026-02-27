package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.TipoTransaccion;
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
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion;
    private String concepto;
    private Double monto;
    private int numeroComprobante;

    //relacion con empleado
    @ManyToOne
    private Empleado empleado;

    //relacion con metodo de pago
    @OneToOne
    private MetodoDePago metodoPago;
}
