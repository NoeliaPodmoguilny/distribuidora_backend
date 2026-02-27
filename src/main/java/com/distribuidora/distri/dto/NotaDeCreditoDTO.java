package com.distribuidora.distri.dto;

import com.distribuidora.distri.enumm.Motivo;
import com.distribuidora.distri.model.Factura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDeCreditoDTO {

    private Long idComprobante;
    private LocalDate fechaComprobante;
    private String numeroComprobante;
    private Double montoTotal;

    private Motivo motivo;
    private Long idfactura;
}
