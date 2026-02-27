package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.TipoComprobante;
import com.distribuidora.distri.helper.UpperCaseListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(UpperCaseListener.class)
public abstract class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroComprobante;
    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;
    private LocalDate fechaComprobante;
    private Double montoTotal;


}
