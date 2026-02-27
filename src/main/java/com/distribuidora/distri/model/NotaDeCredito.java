package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.Motivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class NotaDeCredito extends Comprobante {

    private Motivo motivo;
    @OneToOne
    private Factura factura;

}
