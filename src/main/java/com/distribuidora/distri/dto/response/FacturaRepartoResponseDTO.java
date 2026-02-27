package com.distribuidora.distri.dto.response;

import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.enumm.Letra;
import com.distribuidora.distri.enumm.TipoComprobante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacturaRepartoResponseDTO {

    //Datos de facturas
    private Long numeroComprobante;
    private LocalDate fechaComprobante;
    private TipoComprobante tipoComprobante;
    private Letra letra;
    private Double montoTotal;

    //Datos del cliente
    private String nombreCliente;
    private String apellidoCliente;
    private String calleCliente;
    private String alturaCliente;
}
