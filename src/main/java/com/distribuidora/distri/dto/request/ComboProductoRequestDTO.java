package com.distribuidora.distri.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComboProductoRequestDTO {

    private Long productoDTO;
    private Long comboDTO;
    private Double porcentajeDescuento;
    private int cantidad;
}
