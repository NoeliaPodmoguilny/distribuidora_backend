package com.distribuidora.distri.service;

import com.distribuidora.distri.repository.IFacturaMetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaMetodoPagoService {

    @Autowired
    private IFacturaMetodoPagoRepository facturaMetodoPagoRepository;

}
