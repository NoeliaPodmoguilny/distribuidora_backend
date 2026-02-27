package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.request.RepartoRequestDTO;
import com.distribuidora.distri.dto.response.FacturaResponseDTO;
import com.distribuidora.distri.dto.response.RepartoResponseDTO;
import com.distribuidora.distri.model.Reparto;

import java.util.List;

public interface IRepartoService {
    List<RepartoResponseDTO> getRepartos();

    Reparto findReparto(Long id);

    RepartoResponseDTO saveReparto(RepartoRequestDTO repartoDTO);

    void deleteReparto(Long id);


    List<FacturaResponseDTO> getFacturasParaReparto(Long idRepartidor);

    void cambiarFinalizado(Long id);

}
