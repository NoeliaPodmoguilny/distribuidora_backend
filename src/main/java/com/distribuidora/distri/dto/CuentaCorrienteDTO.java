package com.distribuidora.distri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuentaCorrienteDTO {

    private Long idCuentaCorriente;
    private Double saldoActual;
    private LocalDate fechaUltimaTransaccion;
    private String condicion;

    private Long idCliente;
}
