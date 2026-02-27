package com.distribuidora.distri.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComboProductoDTO {

    private Long id;
    private Long productoDTO;
    private Long comboDTO;
    private Double porcentajeDescuento;
    private int cantidad;
}
