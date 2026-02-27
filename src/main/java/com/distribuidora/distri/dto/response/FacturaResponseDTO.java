package com.distribuidora.distri.dto.response;

import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.dto.PedidoDTO;
import com.distribuidora.distri.enumm.EstadoFactura;
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
public class FacturaResponseDTO {

        private Long numeroComprobante;
        private LocalDate fechaComprobante;
        private TipoComprobante tipoComprobante;
        private Double montoTotal;
        private EstadoFactura estado;
        private Letra letra;
        private PedidoDTO pedidoDTO;
        private Long clienteDTO;
        private List<MetodoDePagoDTO> metodosDePagoDTO;


}
