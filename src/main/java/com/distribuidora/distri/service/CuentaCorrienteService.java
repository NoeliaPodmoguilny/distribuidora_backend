package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.CuentaCorrienteDTO;
import com.distribuidora.distri.model.CuentaCorriente;
import com.distribuidora.distri.repository.ICuentaCorrienteRepository;
import com.distribuidora.distri.service.interfaz.ICuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaCorrienteService implements ICuentaCorrienteService {

    @Autowired private ICuentaCorrienteRepository cuentaCorrienteRepository;

    @Override
    public List<CuentaCorrienteDTO> getCuentaCorrientes() {
        return cuentaCorrienteRepository.findAll()
                .stream()
                .map(cc -> new CuentaCorrienteDTO(
                        cc.getIdCuentaCorriente(),
                        cc.getSaldoActual(),
                        cc.getFechaUltimaTransaccion(),
                        cc.getCondicion(),
                        cc.getCliente().getIdPersona()
                ))
                .toList();
    }

    @Override
    public CuentaCorrienteDTO findCuentaCorriente(Long id) {
        CuentaCorriente cc = cuentaCorrienteRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No existe la cuenta corriente con id: "+ id));
        return new CuentaCorrienteDTO(
                cc.getIdCuentaCorriente(),
                cc.getSaldoActual(),
                cc.getFechaUltimaTransaccion(),
                cc.getCondicion(),
                cc.getCliente().getIdPersona()
        );
    }

    @Override
    public CuentaCorrienteDTO findCuentaCorrienteByIdCliente(Long idCliente) {
        CuentaCorriente cc = cuentaCorrienteRepository.findByClienteIdPersona(idCliente).orElseThrow(() ->
                new RuntimeException("No existe la cuenta corriente para el cliente con id: "+ idCliente));
        return new CuentaCorrienteDTO(
                cc.getIdCuentaCorriente(),
                cc.getSaldoActual(),
                cc.getFechaUltimaTransaccion(),
                cc.getCondicion(),
                cc.getCliente().getIdPersona()
        );
    }

    @Override
    public CuentaCorrienteDTO updateCuentaCorriente(Long id, CuentaCorrienteDTO cuentaCorrienteDTO) {
        return null;
    }
}
