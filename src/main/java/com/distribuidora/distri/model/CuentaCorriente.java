package com.distribuidora.distri.model;

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
@EntityListeners(UpperCaseListener.class)
public class CuentaCorriente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuentaCorriente;
    private Double saldoActual;
    private LocalDate fechaUltimaTransaccion;
    private String condicion;
    @OneToOne
    private Cliente cliente;
}
