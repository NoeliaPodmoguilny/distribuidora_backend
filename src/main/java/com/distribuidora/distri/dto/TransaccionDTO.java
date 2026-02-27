package com.distribuidora.distri.dto;

import com.distribuidora.distri.dto.request.EmpleadoRequestDTO;
import com.distribuidora.distri.enumm.TipoTransaccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransaccionDTO {

    private Long idTransaccion;
    private TipoTransaccion tipoTransaccion;
    private String concepto;
    private Double monto;
    private int numeroComprobante;
    private EmpleadoRequestDTO empleadoDTO;
    private MetodoDePagoDTO metodoPagoDTO;


}
