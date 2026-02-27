package com.distribuidora.distri.dto.request;

import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.dto.PedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacturaRequestDTO {

    private Long pedidoDTO;
    private List<MetodoDePagoDTO> metodosDePagoDTO;
}
