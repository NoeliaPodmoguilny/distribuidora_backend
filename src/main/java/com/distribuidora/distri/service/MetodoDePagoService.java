package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.model.MetodoDePago;
import com.distribuidora.distri.repository.IMetodoDePagoRepository;
import com.distribuidora.distri.service.interfaz.IMetodoDePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoDePagoService implements IMetodoDePagoService {


    @Autowired
    private IMetodoDePagoRepository metodoDePagoRepository;

    @Override
    public List<MetodoDePago> getMetodoDePagos() {
        return metodoDePagoRepository.findAll();
    }

    @Override
    public MetodoDePago findMetodoDePago(Long id) {
        return metodoDePagoRepository.findById(id).orElse(null);
    }

    @Override
    public MetodoDePago saveMetodoDePago(MetodoDePagoDTO metodoDePagoDTO) {
        return null;
    }

    @Override
    public void deleteMetodoDePago(Long id) {

    }

    @Override
    public MetodoDePago updateMetodoDePago(Long id, MetodoDePagoDTO metodoDePagoDTO) {
        return null;
    }
}
