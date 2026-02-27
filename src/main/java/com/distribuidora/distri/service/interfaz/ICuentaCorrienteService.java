package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.CuentaCorrienteDTO;

import java.util.List;

public interface ICuentaCorrienteService {
    List<CuentaCorrienteDTO> getCuentaCorrientes();

    CuentaCorrienteDTO findCuentaCorriente(Long id);

    CuentaCorrienteDTO findCuentaCorrienteByIdCliente(Long idCliente);

    CuentaCorrienteDTO updateCuentaCorriente(Long id, CuentaCorrienteDTO cuentaCorrienteDTO);
}
