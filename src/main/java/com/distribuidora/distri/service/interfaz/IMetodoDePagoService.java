package com.distribuidora.distri.service.interfaz;


import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.model.MetodoDePago;

import java.util.List;

public interface IMetodoDePagoService {
    List<MetodoDePago> getMetodoDePagos();

    MetodoDePago findMetodoDePago(Long id);

    MetodoDePago saveMetodoDePago(MetodoDePagoDTO metodoDePagoDTO);

    void deleteMetodoDePago(Long id);

    MetodoDePago updateMetodoDePago(Long id, MetodoDePagoDTO metodoDePagoDTO);
}
