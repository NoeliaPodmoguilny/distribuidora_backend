package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.ProveedorDTO;
import com.distribuidora.distri.model.Proveedor;

import java.util.List;

public interface IProveedorService {
    List<Proveedor> getProveedores();

    Proveedor findProveedor(Long id);

    Proveedor findProveedorBynombreProveedor(String nombreProveedor);

    Proveedor saveProveedor(ProveedorDTO proveedorDTO);

    void deleteProveedor(Long id);

    Proveedor updateProveedor(Long id, ProveedorDTO proveedorDTO);
}
