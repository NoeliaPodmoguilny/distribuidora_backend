package com.distribuidora.distri.dto.response;

import com.distribuidora.distri.dto.DetalleComprasDTO;
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
public class OrdenDeCompraResponseDTO {

    private Long numeroComprobante;
    private LocalDate fechaComprobante;
    private Double montoTotal;
    private TipoComprobante tipoComprobante;

    private Long proveedorDTO;
    private Long empleadoDTO;

    private List<DetalleComprasDTO> detalleComprasDTOS;

}
