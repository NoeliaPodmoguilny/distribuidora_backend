package com.distribuidora.distri.dto;

import com.distribuidora.distri.enumm.TipoMetodoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetodoDePagoDTO {

    private Long id;
    private String tipoMetodoPago;
    private Double montoParcial;

}
