package com.distribuidora.distri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetallePedidoDTO {

    private Long id;
    private Long pedidoDTO;
    private Long productoDTO;

    private int cantidad;
    private Double porcentajeDescuento;
}
