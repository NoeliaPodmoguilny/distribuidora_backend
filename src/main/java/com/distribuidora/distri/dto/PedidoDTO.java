package com.distribuidora.distri.dto;

import com.distribuidora.distri.enumm.Estado;
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
public class PedidoDTO {

    //atributos de pedido
    private Long id;
    private LocalDate fechaPedido;
    private Double montoTotalPedido;
    private Long clienteDTO;
    private Estado estado;
    private List<Long> empleadoDTO;
    private List<DetallePedidoDTO> detallePedidoDTO;
}
