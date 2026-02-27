package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.request.FacturaRequestDTO;
import com.distribuidora.distri.dto.response.FacturaResponseDTO;

import java.util.List;

public interface IFacturaService {
    List<FacturaResponseDTO> getFacturas();

    FacturaResponseDTO findFactura(Long id);

    FacturaResponseDTO generarFactura(FacturaRequestDTO facturaDTO);

    List<FacturaResponseDTO> generarFacturaMasiva(List<FacturaRequestDTO> facturasDTO);
}
