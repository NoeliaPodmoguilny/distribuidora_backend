package com.distribuidora.distri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CajaDTO {
    private Long idCaja;
    private Double saldoInicio;
    private Double saldoCierre;
    private LocalDate fechaCierreDeCaja;
    private LocalDate fechaInicioDeCaja;
}
