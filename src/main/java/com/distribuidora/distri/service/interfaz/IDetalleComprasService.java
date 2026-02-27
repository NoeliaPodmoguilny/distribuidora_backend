package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.DetalleComprasDTO;
import com.distribuidora.distri.model.DetalleCompras;

import java.util.List;

public interface IDetalleComprasService {

    List<DetalleCompras> getDetalleCompras();

    DetalleCompras findDetalleCompras(Long id);

    DetalleCompras saveDetalleCompras(DetalleComprasDTO detalleComprasDTO);

    void deleteDetalleCompras(Long id);

    DetalleCompras updateDetalleCompras(Long id, DetalleComprasDTO detalleComprasDTO);
}
